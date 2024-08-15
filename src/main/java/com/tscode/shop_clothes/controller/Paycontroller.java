package com.tscode.shop_clothes.controller;


import com.tscode.shop_clothes.Repository.CartRepository;
import com.tscode.shop_clothes.Repository.OderSuccessfullyRepository;
import com.tscode.shop_clothes.Repository.ProductRepository;
import com.tscode.shop_clothes.Repository.UserRepository;
import com.tscode.shop_clothes.configuration.CustomUserDetails;
import com.tscode.shop_clothes.entity.Cart;
import com.tscode.shop_clothes.entity.OrderSuccessfully;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.entity.User;
import com.tscode.shop_clothes.sevice.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/pay")
public class Paycontroller {

    @Autowired
    private VNPayService vnPayService;

    @Autowired
    private CartRepository CartRepository;
    @Autowired
    private OderSuccessfullyRepository OrderSuccess;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public String home() {
        return "pay/index";
    }

    @PostMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        return "redirect:" + vnpayUrl;
    }

    @GetMapping("/vnpay-payment")
    @Transactional // Ensure the transaction management
    public String GetMapping(HttpServletRequest request, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {

        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Cart> carts = CartRepository.findByCustomerId(userDetails.getId());
        for (Cart cart : carts) {
            OrderSuccessfully orderSuccessfully = new OrderSuccessfully();
            orderSuccessfully.setColor(cart.getColor());
            orderSuccessfully.setSize(cart.getSize());
            orderSuccessfully.setQty(cart.getQuantity());
            // Lấy đối tượng Products từ Optional
            Products product = productRepository.findById(cart.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            product.setQuantity(product.getQuantity() - cart.getQuantity());
            product.setQtySold(product.getQtySold() + cart.getQuantity());
            orderSuccessfully.setProduct(product);
            orderSuccessfully.setTotal(cart.getPrice() * cart.getQuantity());
            orderSuccessfully.setCreatedAt(LocalDateTime.now());
            orderSuccessfully.setUser(user);
            orderSuccessfully.setStatus("Đặt Hàng Thành Công");
            orderSuccessfully.setAdCheck(false);
            OrderSuccess.save(orderSuccessfully);
            productRepository.save(product);
        }


        CartRepository.deleteByCustomerId(userDetails.getId());
        int paymentStatus = vnPayService.orderReturn(request);
        System.out.println(paymentStatus);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "pay/ordersuccess" : "/pay/orderfail";
    }
}

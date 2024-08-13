package com.tscode.shop_clothes.controller;


import com.tscode.shop_clothes.Repository.CartRepository;
import com.tscode.shop_clothes.Repository.ProductRepository;
import com.tscode.shop_clothes.Repository.UserRepository;
import com.tscode.shop_clothes.configuration.CustomUserDetails;
import com.tscode.shop_clothes.entity.Cart;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.model.dto.CheckoutDto;
import com.tscode.shop_clothes.model.dto.OrderDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public Products getProductById(@PathVariable("id") Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping
    public String cart() {
        return "componnent/Cart";
    }

    @GetMapping("/checkout")
    public String checkout(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        CheckoutDto checkoutDto = new CheckoutDto();
        checkoutDto.setUser(userDetails);
        List<Cart> cartList = cartRepository.findByCustomerId(userDetails.getId());
        double total = cartList.stream().mapToDouble(cartItem -> cartItem.getPrice() * cartItem.getQuantity()).sum();
        checkoutDto.setTotal((long) total);
        model.addAttribute("checkoutDto", checkoutDto);
        System.out.println(userDetails.getAddress());
        return "componnent/Checkout";
    }

    @PostMapping("/addQuantity")
    public String addQuantity(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return "componnent/AddQuantity";
    }

    @PostMapping("/delete")
    public String deleteCartItem(@RequestParam("id") int cartId, RedirectAttributes redirectAttributes) {
        try {
            cartRepository.deleteById(cartId);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công mục cart.");
        } catch (EmptyResultDataAccessException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy mục cart với ID: " + cartId);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa mục cart vì nó đang được tham chiếu bởi dữ liệu khác.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi xóa mục cart. Vui lòng thử lại sau.");
        }
        return "redirect:/cart";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> addCard(@RequestParam("size") String size, @RequestParam("color") String color, @RequestParam("quantity") int quantity, @RequestParam("id") Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // Lấy tất cả các mục trong giỏ hàng của người dùng hiện tại
        List<Cart> cartItems = cartRepository.findByCustomerId(userDetails.getId());

        // Tìm mục sản phẩm trong giỏ hàng hiện tại
        Optional<Cart> existingCartItem = cartItems.stream().filter(cartItem -> cartItem.getProductId() == id && cartItem.getSize().equals(size) && cartItem.getColor().equals(color)).findFirst();

        if (existingCartItem.isPresent()) {
            // Nếu sản phẩm đã tồn tại, kiểm tra giá và cập nhật số lượng nếu giá trùng khớp
            Cart cartItem = existingCartItem.get();
            double currentPrice = productRepository.findById(id).orElseThrow().getPrice();
            if (cartItem.getPrice() == currentPrice) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartRepository.save(cartItem);
                return ResponseEntity.ok("Số lượng sản phẩm đã được cập nhật thành công!");
            } else {
                return ResponseEntity.badRequest().body("Giá sản phẩm đã thay đổi, không thể cập nhật số lượng!");
            }
        } else {
            // Nếu sản phẩm chưa tồn tại trong giỏ hàng, tạo mới
            double currentPrice = productRepository.findById(id).orElseThrow().getPrice();
            Cart newCartItem = new Cart();
            newCartItem.setProductId(id);
            newCartItem.setCustomerId(userDetails.getId());
            newCartItem.setPrice(currentPrice);
            newCartItem.setCreatedAt(LocalDateTime.now());
            newCartItem.setQuantity(quantity);
            newCartItem.setSize(size);
            newCartItem.setColor(color);
            cartRepository.save(newCartItem);
            return ResponseEntity.ok("Thêm vào giỏ hàng thành công!");
        }
    }


}

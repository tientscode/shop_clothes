// function viewcart() {
//     var infoPanel = document.querySelector('.cart');
//     if (infoPanel.style.display === 'none' || infoPanel.style.display === '') {
//         infoPanel.style.display = 'block'; /* Hiển thị phần tử */
//     } else {
//         infoPanel.style.display = 'none'; /* Ẩn phần tử */
//     }
// }

function showCart() {
    document.getElementById('cart').style.display = 'block';
}

function hideCart() {
    document.getElementById('cart').style.display = 'none';
}



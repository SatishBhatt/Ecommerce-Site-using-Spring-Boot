$(document).ready(function() {
  $("#buttonAdd2Cart").on("click", function(e) {
    e.preventDefault();
    addToCart();
  });

  function addToCart() {
    var quantity = $("#quantity" + productId).val();
    var url = contextPath + "/cart/add/" + productId + "/" + quantity;

    $.ajax({
      type: "POST",
      url: url,
      beforeSend: function(xhr) {
        xhr.setRequestHeader(crsfHeaderName, csrfValue);
      }
    }).done(function() {
      alert("Successfully added to cart!");
    });
  }
});

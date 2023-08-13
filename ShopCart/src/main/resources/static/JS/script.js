$(document).ready(function() {
	$(".minusButton").on("click", function(evt) {
		evt.preventDefault();
		var productId = $(this).attr("th:id").substring(8);
		var qtyInput = $("#quantity" + productId);

		var newQty = parseInt(qtyInput.val()) - 1;
		if (newQty > 0) {
			qtyInput.val(newQty);
		}
	});

	$(".plusButton").on("click", function(evt) {
		evt.preventDefault();
		var productId = $(this).attr("th:id").substring(8);
		var qtyInput = $("#quantity" + productId);

		var newQty = parseInt(qtyInput.val()) + 1;
		if (newQty < 10) {
			qtyInput.val(newQty);
		}
	});
});

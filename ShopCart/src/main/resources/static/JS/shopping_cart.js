 $(document).ready(function() {
        updateTotal();
    });

    function updateTotal() {
        var total = 0.0;
        $(".productSubtotal").each(function(index, element) {
            total += parseFloat(element.innerHTML);
        });
        $("#totalAmount").text("Amount Payable: RS " + total);
    }
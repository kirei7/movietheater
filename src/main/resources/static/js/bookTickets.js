const eventId = $(".seats-container").attr("data-event");

$(document).ready(function(){
    $(".seat-item").click(function (e) {
        changeStatus($(e.target));
    });
    $(".book-tickets").click(function (e) {
        sendBookingRequest();
    });
    $(".price-tickets").click(function (e) {
        console.log("sd");
        sendPriceRequest();
    });

});

function changeStatus(target) {
    if (!target.hasClass("seat-item"))
        target = target.parent();
    if (target.hasClass("seat-booked"))
        return;
    target.toggleClass("seat-targeted");
    sendPriceRequest();
}

function sendBookingRequest() {
    var seatsData = collectSeatsData();
    if (seatsData.length <= 0)
        return;
    $.ajax({
        url: "/booking/event/" + eventId,
        type: 'POST',
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(seatsData),
        success: function (data) {
            $("#exampleModalLabel").text("Bought tickets for total: $" + data.sum);
            $('#exampleModal').modal('show');
            $('#exampleModal').on('hidden.bs.modal', function (e) {
              location.reload();
            })
        },
        error: function (msg) {
            $("#exampleModalLabel").text(msg.responseText);
            $('#exampleModal').modal('show');
        }
    });
}

function sendPriceRequest() {
    $(".preview-price-tickets").empty();
    $(".preview-price-total").text("");
    var seatsData = collectSeatsData();
    if (seatsData.length <= 0)
        return;
    $.ajax({
        url: "/booking/event/" + eventId + "/price",
        type: 'POST',
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(seatsData),
        success: function (data) {
            //alert("Total sum for tickets: " + data.sum);
            data.bookedTickets = JSON.parse(data.bookedTickets);
            renderData(data);
        }
    });
}

function collectSeatsData() {
    var seatsData = [];
    $(".seat-targeted").each(function () {
        let item = $(this).attr("data-id");
        if (item === undefined)
            return;
        seatsData.push(parseInt(item));
    });
    return seatsData;
    }

    function renderData(data) {
        console.log(data);
        let tickets = data.bookedTickets;
        for(let i = 0; i < tickets.length; i++) {
            let listItem = $("#cloneable ul li").clone();
            listItem.find(".preview-ticket-body").text("Seat: " + tickets[i].seat + ", price: " + tickets[i].price);
            if (tickets[i].discount.type != null) {
                listItem
                    .find(".preview-ticket-discount")
                    .text("(" + tickets[i].discount.amount + "% discount - " + tickets[i].discount.type +")")
            }
            $(".preview-price-tickets").append(listItem);
        }
        $(".preview-price-total").text(data.sum);
    }
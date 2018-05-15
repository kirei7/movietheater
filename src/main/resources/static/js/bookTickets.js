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
            alert("Purchased tickets for total sum: " + data.sum);
            location.reload();
        }
    });
}

function sendPriceRequest() {
    var seatsData = collectSeatsData();
    if (seatsData.length <= 0)
        return;
    $.ajax({
        url: "/booking/event/" + eventId + "/price",
        type: 'Post',
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(seatsData),
        success: function (data) {
            alert("Total sum for tickets: " + data.sum);
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
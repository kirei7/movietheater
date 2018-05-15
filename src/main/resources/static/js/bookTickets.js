const eventId = $(".seats-container").attr("data-event");

$(document).ready(function(){
    $(".seat-item").click(function (e) {
        changeStatus($(e.target));
    });
    $(".book-tickets").click(function (e) {
        sendBookingRequest();
    });

});

function changeStatus(target) {
    if (target.hasClass("seat-booked"))
        return;
    target.toggleClass("seat-targeted");
}

function sendBookingRequest() {
    var seatsData = [];
    $(".seat-targeted").each(function () {
        let item = $(this).attr("data-id");
        if (item === undefined)
            return;
        seatsData.push(parseInt(item));
    });
    console.log(seatsData);
    $.ajax({
        url: "/booking/event/" + eventId,
        type: 'POST',
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(seatsData),
        success: function () {
            //location.reload();
        }
    });
}
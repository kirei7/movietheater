var eventId;

$(document).ready(function(){
    $(".event-remove").click(function (e) {
        eventId = parseInt($(e.target).attr("data-id"));
        showModal();
    });
    $(".delete-do").click(function (e) {
        console.log("delete " + eventId);
        $.ajax({
                url: "/api/events/" + eventId,
                type: 'DELETE',
                success: function (data) {
                    $("#event"+eventId).remove();
                    $('#myModal').modal('hide')
                }
            });
    });
    $('#myModal').on('hidden.bs.modal', function (e) {
      eventId = undefined;
    })
});

function showModal() {
    $('#myModal').modal('show')
}
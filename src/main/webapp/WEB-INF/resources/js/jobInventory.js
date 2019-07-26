
function acceptByEmployer(jobID,candidateID,acceptance, modal) {
    var acc = new Boolean(acceptance);
    var targetUrl = "/eJobFinder/employer/acceptByEmployer";
     $.ajax({
              type: "POST",
              url: targetUrl,
              data: jQuery.param({
                jobID:jobID,
                  candidateID: candidateID,
                       isAccepted: acc
              }),
              timeout: 1000,
              success: function(data) {
                     $("#"+modal).modal('hide');
                    $('#modalA').modal('show');

              },
              error: function(e) {
                  console.log('error while post');
              }
          });

      }

$(document).ready(function() {
$("#modalA").on('hidden.bs.modal', function (e) {
  location.reload();
});

});
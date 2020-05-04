function acceptByCandidate(jobID,candidateID,acceptance,modal) {
    var acc = Boolean(acceptance);
    var targetUrl = "/eJobFinder/candidate/acceptByCandidate";
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
$("#modalList").on('hidden.bs.modal', function (e) {
  location.reload();
});


});
$(document).ready(function() {

$("#txtEditor").Editor();
$("input:submit").click(function(){
$('#txtEditorContent').text($('#txtEditor').Editor("getText"));});

$("#txtEditor2").Editor();
$("input:submit").click(function(){
$('#txtEditorContent2').text($('#txtEditor2').Editor("getText"));});

$("#txtEditor3").Editor();
$("input:submit").click(function(){
$('#txtEditorContent3').text($('#txtEditor3').Editor("getText"));});

$("#txtEditor4").Editor();
$("input:submit").click(function(){
$('#txtEditorContent4').text($('#txtEditor4').Editor("getText"));});

$("#txtEditor5").Editor();
$("input:submit").click(function(){
$('#txtEditorContent5').text($('#txtEditor5').Editor("getText"));});

$('#jobForm').on('keyup keypress', function(e) {
  var keyCode = e.keyCode || e.which;
  if (keyCode === 13) {
    e.preventDefault();
    return false;
  }
});


var fp = flatpickr(document.querySelector('#expirationDate'), {
  altFormat: "j F Y",
  altInput: true,
  defaultDate: new Date().fp_incr(14) ,
  dateFormat: "d-M-Y",
  locale: {
                         "firstDayOfWeek": 1 // start week on Monday
                     },
  //inline: true,
  //enableTime: true,
  //mode: "multiple",
  onChange: function(selectedDates, dateStr, instance) {
    console.log('date: ', dateStr);
  }
});

    $('form input').on('keypress', function(e) {
        return e.which !== 13;
    });
});
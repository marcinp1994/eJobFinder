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
$('form input').on('keypress', function(e) {
    return e.which !== 13;
});
});
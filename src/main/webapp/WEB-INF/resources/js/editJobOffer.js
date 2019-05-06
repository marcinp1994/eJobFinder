$(document).ready(function() {

$("#txtEditor").Editor();
$("input:submit").click(function(){
$('#txtEditorContent').text($('#txtEditor').Editor("getText"));});

$("#txtEditor2").Editor();
$("#txtEditor2").Editor("setText", "Hello");
$("input:submit").click(function(){
$('#txtEditorContent2').text($('#txtEditor2').Editor("getText"));});

$("#txtEditor3").Editor();
$("#txtEditor3").Editor("setText", "Hello");
$("input:submit").click(function(){
$('#txtEditorContent3').text($('#txtEditor3').Editor("getText"));});

$("#txtEditor4").Editor();
$("#txtEditor4").Editor("setText", "Hello");
$("input:submit").click(function(){
$('#txtEditorContent4').text($('#txtEditor4').Editor("getText"));});

$("#txtEditor5").Editor();
$("#txtEditor5").Editor("setText", "Hello");
$("input:submit").click(function(){
$('#txtEditorContent5').text($('#txtEditor5').Editor("getText"));});

});
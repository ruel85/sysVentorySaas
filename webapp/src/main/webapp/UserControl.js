$(document).ready(function() {
// Function to get input value.
$('#save').click(function() {
var email = $("#email").val();
alert(email);
});
$('#text_reset').click(function() {
$("#text").val('');
});
});
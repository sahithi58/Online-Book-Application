$(function() {
    $('#forgotPassword').submit(function() {
        // show a hidden div to indicate progression
        $('#someHiddenDiv').show();
        return false;
    });
});

$('#header nav li').click(function() {
  $('#header nav li').removeClass('active');    
  $(this).addClass('active');
});

$('.leftCol ul li').click(function() {
  $('#header nav li').removeClass('highlighted');    
  $(this).addClass('highlighted');
}); 

$('.pagination li').click(function() {
  $('.pagination li').removeClass('currentPage');    
  $(this).addClass('currentPage').css('color : #fff');
}); 
$(".leftCol").css({'height':($("#mainContent").height()+'px')});

// $(document).ready(function (){
//     $('#example-multiple-optgroups').multiselect();
// });



 $(document).ready(function (){
  $(".dropdown-menu li a").click(function(){
  //console.log("ovooo");
  var selText = $(this).text();
  $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
});
});


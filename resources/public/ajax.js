// $('div#submituser').click(function(){
//             var data = $('input#iduser').val();
//             $.ajax({
//                 url: 'ajaxcall',
//                 type: 'POST',
//                 data: {iduser: data},
//                 beforeSend: function(){
//                    $('div#loaderdiv').show();
//                 },
//                 success: function(result){
//                 $('div#loaderdiv').hide();
//                 $('#div1').html(result);
//                 }});
//     });


$(".dropdown-menu li a").click(function(){
  var selText = $(this).text();
  $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
});

$("#search").click(function(){
   var data = $(".btn-select").text();

});

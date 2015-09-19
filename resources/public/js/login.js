 $(function(){
   $(".login").click(function (){
      alert("ovde");
    var UserName=$('.UserName').val();
        var Pass= $('.Pass').val();
        $.ajax({
                    url:"/login",
                    type:"POST",
                    data:{
                      UserName:UserName,
                      Pass:Pass
                    },
                    cache:!1,
                    success:function(){

                            $("#success").html("<div class='alert alert-success'>"),
                            $("#success > .alert-success").html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;").append("</button>"),

                            $("#success > .alert-success").append("<strong>Your added new user! </strong>"),
                            $("#success > .alert-success").append("</div>"),$("#Blog").trigger("reset")
                    },
                    error:function(){

                          $("#success").html("<div class='alert alert-danger'>"),
                          $("#success > .alert-danger").html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;").append("</button>"),
                          $("#success > .alert-danger").append("<strong>Sorry ,something went wrong. Please try again!"),
                          $("#success > .alert-danger").append("</div>"),$("#Blog").trigger("reset")
                    }//end error
              })//end ajax
  })
   });

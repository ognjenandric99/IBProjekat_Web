$(document).ready(function(){
  localStorage['loginToken'] = null;
  $(".toggleDivs").on('click',function(){
    if($("#delovi").get(0).classList.contains("displayLogin")){
      $("#delovi").get(0).className="displayReg";
    }
    else{
      $("#delovi").get(0).className="displayLogin";
    }
  });


  $("#login").on('click',function(){
    let email = $("#login_mail").val();
    let pass = $("#login_pass").val();
    $.ajax({
        type: "POST",
        url: "http://localhost:9144/users/login/"+email+"/"+pass,
        dataType: "text",
        async: false,
        data: null,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            localStorage['loginToken'] = data;
            window.location.href="korisnici.html";
        },
        error: function (textStatus, errorThrown) {
            alert("Bad credentials");
        }
    });
  })

  $("#register").on('click',function(){
    let email = $("#reg_mail").val();
    let pass1 = $("#reg_pass").val();
    let pass2 = $("#reg_pass2").val();
    if(pass1!=pass2){
      return;
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:9144/users/register/"+email+"/"+pass1,
        dataType: "text",
        async: false,
        data: null,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
          alert("Wait for admins to approve you.");
        },
        error: function (textStatus, errorThrown) {
            alert("Bad credentials");
        }
    });
  });
});

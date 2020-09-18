$.ajax({
    type: "POST",
    url: "http://localhost:9144/users/isSessionValidAndAdmin/"+localStorage['loginToken'],
    dataType: "text",
    async: false,
    data: null,
    contentType: "application/json; charset=utf-8",
    success: function (data) {
        // localStorage['loginToken'] = data;
        // window.location.href="korisnici.html";
    },
    error: function (textStatus, errorThrown) {
        localStorage['loginToken'] = null;
        window.location.href="login.html";
    }
});

$(document).ready(function(){
  $.post('http://localhost:9144/users/getInactive',function(data){
    for(i=0;i<data.length;i++){
      let korisnik = data[i];
      let red = document.createElement('tr');
      red.innerHTML = "<td>"+korisnik['email']+"</td><td><button data-email='"+korisnik['email']+"' class='activateUser'>Activate</button></td>"
      document.getElementById('admintable').appendChild(red);
    }
    $(".activateUser").on('click',function(e){
      $.post('http://localhost:9144/users/activate/'+e.target.getAttribute('data-email'),function(data){
        alert(data);
        window.location.href = "admin.html";
      })
    })
  });
})

let spisakKorisnika = [];

$.ajax({
    type: "POST",
    url: "http://localhost:9144/users/isSessionValid/"+localStorage['loginToken'],
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
  $.get('http://localhost:9144/users/all',function(data){
    spisakKorisnika = data;
    for(i=0;i<data.length;i++){
      let korisnik = data[i];
      let red = document.createElement('tr');
      red.innerHTML = "<td>"+korisnik['email']+"</td><td><button id='"+korisnik['id']+"'>Open</button></td>"
      document.getElementById('korisniciLista').appendChild(red);
    }
  })
})

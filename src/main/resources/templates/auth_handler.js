function enrichHeaderWithAuth(xhttp){
var token = localStorage.getItem(USER_TOKEN_KEY);
if (token == null) {
    window.location.href="login.html";
    localStorage().clear();
}
xhttp.setRequestHeader('Authorization', 'Bearer ' + token);
xhttp.setRequestHeader('Accept', 'application/json');
}
function saveUser() {
    var phoneNumber = document.getElementById("pn_id").value;
    var password = document.getElementById("psw_id").value;
console.log("1");
    var xhttp = new XMLHttpRequest();
                        xhttp.open("POST", url + "register");
                        xhttp.setRequestHeader("Content-Type", "application/json");
                        xhttp.send(JSON.stringify({
                        'phoneNumber': phoneNumber,
                        'password': password
                        }));
}

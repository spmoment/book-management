
function saveUser() {
                    var regPhoneNumber = document.getElementById("reg_pn").value;
                    var regPassword = document.getElementById("reg_pas").value;

                    var xhttp = new XMLHttpRequest();
                    xhttp.open("POST", url + "register");
                    xhttp.setRequestHeader("Content-Type", "application/json");
                    xhttp.send(JSON.stringify({
                    'phoneNumber': regPhoneNumber,
                    'password': regPassword}));

                    xhttp.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            var resp = JSON.parse(this.responseText);
                                if(resp != null) {
                                    alert("You successfully registered");
                                }
                        }
                    };
}

function activate() {
                    var regPhoneNumber = document.getElementById("aut_pn").value;
                    var regPassword = document.getElementById("aut_pas").value;

                    var xhttp = new XMLHttpRequest();
                    xhttp.open("POST", url + "auth");
                    xhttp.setRequestHeader("Content-Type", "application/json");
                    xhttp.send(JSON.stringify({
                    'phoneNumber': regPhoneNumber,
                    'password': regPassword}));


                    xhttp.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            var token = JSON.parse(this.responseText);
                            localStorage.setItem(USER_TOKEN_KEY, token.token);
                            localStorage.setItem(PHONE_NUMBER, regPhoneNumber);
                            if (token.role == "ADMIN") {
                                window.location.href="admin_page.html";
                            }
                            else {
                                window.location.href="books_costumer.html";
                            }
                        }
                    }
}
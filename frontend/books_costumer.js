
function getBook() {
                var xhttp = new XMLHttpRequest();
                xhttp.open("GET", url + "api/findAllBooks", true);
                xhttp.send();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        var books = JSON.parse(this.responseText);
                        var html = '';
                        for (var i = 0; i < books.length; i++) {
                            var book = books[i];
                            html = html +       '<div class="large-3 small-6 columns">\n' +
                                                    '<img src="http://placehold.it/1500x1000&text=Book">' +
                                                    '<div class="panel">' +
                                                        '<h5>'+ book.title +'</h5>' +
                                                        '<h5>'+ book.authorsDtos[0].firstName + " " + book.authorsDtos[0].lastName +'</h5>' +
                                                        '<h6 class="subheader">'+ book.price + "₽" +'</h6>' +
                                                        '<button style="height:45px;width:180px" onclick="buy();" type="button">Add to cart</button>' +
                                                    '</div>' +
                                                '</div>';
                        }
                        document.getElementById("bookListOrder").innerHTML = html;
                    }
                }
            }
            getBook();

function logout() {
                window.location.href="login.html";
                localStorage().clear();
}
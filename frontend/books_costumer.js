function openCart() {
    $('#modalCrop').foundation('reveal', 'open');
    drawTable();
}

function drawTable() {
    var html = '<tr>\n' +
        '        <th style="text-align:center">Author</th>\n' +
        '        <th style="text-align:center">Title</th>\n' +
        '        <th style="text-align:center">Price</th>\n' +
        '        <th style="text-align:center">Delete</th>\n' +
        '    </tr>';
    var books = JSON.parse(localStorage.getItem("books"));
    for (var i = 0; i < books.length; i++) {
        var book1 = books[i];
        html = html + '<tr>\n' +
            '        <td style="width:300px;text-align:center">' + book1.author + '</td>\n' +
            '        <td style="width:300px;text-align:center">' + book1.title + '</td>\n' +
            '        <td style="width:100px;text-align:center">' + book1.price + '</td>\n' +
            '        <td><button style="height:50px;width:120px;text-align: center; margin: auto" onclick="deleteOrderBook(' + i + ')">Delete</button></td></tr>';

    }
    document.getElementById("orderList").innerHTML = html;
}

function deleteOrderBook(index) {
    let books = [];
    books = JSON.parse(localStorage.getItem("books"));
    books.splice(index, 1);
    localStorage.removeItem("books");
    localStorage.setItem("books", JSON.stringify(books));
    $('#element').foundation('close');
    drawTable();
}

function buy(bookId, bookAuthor, bookTitle, bookPrice) {
    var book = {'id': bookId, 'author': bookAuthor, 'title': bookTitle, 'price': bookPrice};
    let books = [];
    if (localStorage.getItem("books") == null) {
        books.push(book);
        localStorage.setItem("books", JSON.stringify(books))
    } else {
        books = JSON.parse(localStorage.getItem("books"));
        books.push(book);
        localStorage.setItem("books", JSON.stringify(books))
    }
}


function finishPurchase() {
    let books = JSON.parse(localStorage.getItem("books"));
    var phoneNumber = localStorage.getItem(PHONE_NUMBER);
    var address = document.getElementById("address").value;

    if(address == "") {
        alert("Enter address")
    }
    else {
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", url + "saveOrder");
        enrichHeaderWithAuth(xhttp);
        xhttp.setRequestHeader("Content-Type", "application/json");
        let booksList = [];
        for (var i = 0; i < books.length; i++) {
            booksList.push(books[i].id)
        }
            xhttp.send(JSON.stringify({
            'bookIds': booksList,
            'phoneNumber': phoneNumber,
            'address': address
        }));
        localStorage.removeItem("books");
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                alert("Your order has been created");
                document.getElementById("orderList").innerHTML = "";
            }
        };
    }
}

function getBook() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url + "costumer/findAllBooks", true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var books = JSON.parse(this.responseText);
            var html = '';
            for (var i = 0; i < books.length; i++) {
                var book = books[i];
                html = html + '<div class="large-3 small-6 columns">\n' +
                    '<img src="http://placehold.it/1500x1000&text=Book">' +
                    '<div class="panel">' +
                    '<h5>' + book.authorsDtos[0].firstName + " " + book.authorsDtos[0].lastName + '</h5>' +
                    '<h5>' + book.title + '</h5>' +
                    '<h6 class="subheader">' + book.price + "₽" + '</h6>' +
                    '<button style="height:45px;width:180px" onclick="buy(\'' + book.id + '\',\'' +
                    book.authorsDtos[0].firstName + " " +
                    book.authorsDtos[0].lastName + '\',\'' +
                    book.title + '\',\'' +
                    book.price + '\')" type="button">Add to cart</button>' +
                    '</div>' +
                    '</div>';
            }
            document.getElementById("bookListOrder").innerHTML = html;
        }
    };
}

getBook();



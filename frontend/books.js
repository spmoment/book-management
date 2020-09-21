

            function deleteBook(bookId) {
                var xhttp = new XMLHttpRequest();
                xhttp.open("DELETE", url + "api/deleteBook/" + bookId, true);
                xhttp.send();
                xhttp.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            loadBooks();
                        }
                };
            }

            function loadBooks() {
                var xhttp = new XMLHttpRequest();
                xhttp.open("GET", url + "api/findAllBooks", true);
                xhttp.send();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        var books = JSON.parse(this.responseText);
                        var html = '<tr>\n' +
                        '        <th>Book ID</th>\n' +
                        '        <th>Title</th>\n' +
                        '        <th>Year Publishing</th>\n' +
                        '        <th>Annotation</th>\n' +
                        '        <th>Delete</th>\n' +
                        '    </tr>';
                        for (var i = 0; i < books.length; i++) {
                            var book = books[i];
                            html = html + '<tr><td>' + book.id + '</td>\n' +
                                '        <td>' + book.title + '</td>\n' +
                                '        <td>' + book.yearPublishing + '</td>\n' +
                                '        <td>' + book.annotation + '</td>' +
                                '        <td><button onclick="deleteBook(' + book.id + ')">Delete</button></td></tr>';
                        }
                        document.getElementById("booksList").innerHTML = html;
                    }
                }
            }
            loadBooks();

            function searchById() {
                    var test = document.getElementById("search_field_book").value;
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (this.readyState == 4 && this.status == 200) {
                            var book = JSON.parse(this.responseText);
                            var html = '<tr>\n' +
                                '        <th>Book ID</th>\n' +
                                '        <th>Title</th>\n' +
                                '        <th>Year Publishing</th>\n' +
                                '        <th>Annotation</th>\n' +
                                '        <th>Delete</th>\n' +
                                '    </tr>';
                            html = html + '<tr><td>' + book.id + '</td>\n' +
                                '        <td>' + book.title + '</td>\n' +
                                '        <td>' + book.yearPublishing + '</td>\n' +
                                '        <td>' + book.annotation + '</td>' +
                                '        <td><button onclick="deleteBook(' + book.id + ')">Delete</button></td></tr>';
                            document.getElementById("booksList").innerHTML = html;
                        }
                    };
                    xhttp.open("GET", url + "api/findBookById?id=" + test, true);
                    xhttp.send();
            }


            function createBook() {
                    var bookTitle = document.getElementById("book_title").value;
                    var bookYearPub = document.getElementById("book_year_pub").value;
                    var bookAn = document.getElementById("book_an").value;
                    var authorId = document.getElementById("author_id").value;

                    var xhttp = new XMLHttpRequest();
                    xhttp.open("POST", url + "api/saveBook");
                    xhttp.setRequestHeader("Content-Type", "application/json");
                    xhttp.send(JSON.stringify({
                    'title': bookTitle,
                    'yearPublishing': bookYearPub,
                    'annotation': bookAn,
                    'authorsDtos': [{'id': authorId}]}));

                    xhttp.onreadystatechange = function() {
                            if (this.readyState == 4 && this.status == 200) {
                                    loadBooks();
                            }
                    };
            }

            document.getElementById("booksList").onclick = (event) => {
              let cell = event.target;
              if (cell.tagName.toLowerCase() != 'td')
                return;
              let i = cell.parentNode.rowIndex;
              document.getElementById("book_id").value = booksList.rows[i].cells[0].innerHTML;
              document.getElementById("book_title").value = booksList.rows[i].cells[1].innerHTML;
              document.getElementById("book_year_pub").value = booksList.rows[i].cells[2].innerHTML;
              document.getElementById("book_an").value = booksList.rows[i].cells[3].innerHTML;

            }

            function editBook() {
                    var bookId = document.getElementById("book_id").value;
                    var bookTitle = document.getElementById("book_title").value;
                    var bookYearPub = document.getElementById("book_year_pub").value;
                    var bookAn = document.getElementById("book_an").value;
                    var authorId = document.getElementById("author_id").value;

                    var xhttp = new XMLHttpRequest();
                    xhttp.open("PUT", url + "api/updateBook");
                    xhttp.setRequestHeader("Content-Type", "application/json");
                    xhttp.send(JSON.stringify({
                    'id': bookId,
                    'title': bookTitle,
                    'yearPublishing': bookYearPub,
                    'annotation': bookAn
                    }));

                    xhttp.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            loadBooks();
                        }
                    };
            }


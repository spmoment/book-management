

            function deleteAuthor(authorId) {
                var xhttp = new XMLHttpRequest();
                xhttp.open("DELETE", url + "admin/deleteAuthor/" + authorId, true);
                enrichHeaderWithAuth(xhttp);
                xhttp.send();
                xhttp.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            loadAuthors();
                        }
                };
            }

            function loadAuthors() {
                var xhttp = new XMLHttpRequest();
                xhttp.open("GET", url + "costumer/findAllAuthors", true);
                xhttp.send();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        var authors = JSON.parse(this.responseText);
                        var html = '<tr>\n' +
                        '        <th>Author ID</th>\n' +
                        '        <th>First name</th>\n' +
                        '        <th>Last Name</th>\n' +
                        '        <th>Year of birth</th>\n' +
                        '        <th>Delete</th>\n' +
                        '    </tr>';
                        for (var i = 0; i < authors.length; i++) {
                            var author = authors[i];
                            html = html + '<tr><td>' + author.id + '</td>\n' +
                                '        <td>' + author.firstName + '</td>\n' +
                                '        <td>' + author.lastName + '</td>\n' +
                                '        <td>' + author.yearOfBirth + '</td>' +
                                '        <td><button onclick="deleteAuthor(' + author.id + ')">Delete</button></td></tr>';
                        }
                        document.getElementById("authorsList").innerHTML = html;
                    }
                }
            }
            loadAuthors();

            function searchByLastName() {
                    var test = document.getElementById("search_field_author").value;
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (this.readyState == 4 && this.status == 200) {
                            var author = JSON.parse(this.responseText);
                            var html = '<tr>\n' +
                                '        <th>Author ID</th>\n' +
                                '        <th>First name</th>\n' +
                                '        <th>Last Name</th>\n' +
                                '        <th>Year of birth</th>\n' +
                                '        <th>Delete</th>\n' +
                                '    </tr>';
                            html = html + '<tr><td>' + author.id + '</td>\n' +
                                '        <td>' + author.firstName + '</td>\n' +
                                '        <td>' + author.lastName + '</td>\n' +
                                '        <td>' + author.yearOfBirth + '</td>' +
                                '        <td><button onclick="deleteAuthor(' + author.id + ')">Delete</button></td></tr>';
                            document.getElementById("authorsList").innerHTML = html;
                        }
                    };
                    xhttp.open("GET", url + "costumer/findByLastName?lastName=" + test, true);
                    xhttp.send();
            }

            function createAuthor() {
                                var authorFirstName = document.getElementById("author_first_name").value;
                                var authorLastName = document.getElementById("author_last_name").value;
                                var authorYearOfBirth = document.getElementById("author_year_of_birth").value;

                                var xhttp = new XMLHttpRequest();
                                xhttp.open("POST", url + "admin/saveAuthor");
                                enrichHeaderWithAuth(xhttp);
                                xhttp.setRequestHeader("Content-Type", "application/json");
                                xhttp.send(JSON.stringify({
                                'firstName': authorFirstName,
                                'lastName': authorLastName,
                                'yearOfBirth': authorYearOfBirth}));

                                xhttp.onreadystatechange = function() {
                                        if (this.readyState == 4 && this.status == 200) {
                                            loadAuthors();
                                        }
                                };
            }

            document.getElementById("authorsList").onclick = (event) => {
              let cell = event.target;
              if (cell.tagName.toLowerCase() != 'td')
                return;
              let i = cell.parentNode.rowIndex;
              document.getElementById("aut_id").value = authorsList.rows[i].cells[0].innerHTML;
              document.getElementById("author_first_name").value = authorsList.rows[i].cells[1].innerHTML;
              document.getElementById("author_last_name").value = authorsList.rows[i].cells[2].innerHTML;
              document.getElementById("author_year_of_birth").value = authorsList.rows[i].cells[3].innerHTML;
            }

            function editAuthor() {
                    var autId = document.getElementById("aut_id").value;
                    var author_fn = document.getElementById("author_first_name").value;
                    var author_ln = document.getElementById("author_last_name").value;
                    var author_yob = document.getElementById("author_year_of_birth").value;

                    var xhttp = new XMLHttpRequest();
                    xhttp.open("PUT", url + "admin/updateAuthor");
                    enrichHeaderWithAuth(xhttp);
                    xhttp.setRequestHeader("Content-Type", "application/json");
                    xhttp.send(JSON.stringify({
                    'id': autId,
                    'firstName': author_fn,
                    'lastName': author_ln,
                    'yearOfBirth': author_yob
                    }));

                    xhttp.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            loadAuthors();
                        }
                    };
            }




function listBook() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/books",
        dataType: "json",
        success: function (book) {
            console.log(book);
            let content = "";
            for (let i = 0; i < book.length; i++) {
                content += '<tr>\n' +
                    '        <td>' + book[i].code + '</td>\n' +
                    '        <td>' + book[i].name + '</td>\n' +
                    '        <td>' + book[i].author + '</td>\n' +
                    '        <td>' + book[i].price + '</td>\n' +
                    '        <td><button type="submit" onclick="deleteBook(' + book[i].id + ')">DELETE</button></td>\n' +
                    '        <td><button onclick="updateBook(' + book[i].id + ')">UPDATE</button></td>\n' +
                    '    </tr>';
            }
            document.getElementById("content").innerHTML = content;
        }
    })
}
listBook()

function getTotalPrice() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/books/totalPrice",
        success: function(totalPrice) {
            document.getElementById("totalPrice").innerHTML = totalPrice;
        }
    })
}
getTotalPrice();

function addNewBook(){
    let code = document.getElementById("code").value;
    let name = document.getElementById("name").value;
    let author = document.getElementById("author").value;
    let price = document.getElementById("price").value;
    let newBook = {
        code: code,
        name: name,
        author: author,
        price: price
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type:"POST",
        data: JSON.stringify(newBook),
        url: "http://localhost:8080/books/create",
        success: listBook
    })
}

function deleteBook(id){
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/books/" + id,
        dataType: "json",
        success: listBook
    })
}

function updateBook(id) {
    let code = document.getElementById("code").value;
    let name = document.getElementById("name").value;
    let author = document.getElementById("author").value;
    let price = document.getElementById("price").value;
    let newBook = {
        code: code,
        name: name,
        author: author,
        price: price
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(newBook),
        url: "http://localhost:8080/books/" + id,
        success: listBook
    })
}

function searchBooks() {
    let name = document.getElementById("nameInput").value;
    let author = document.getElementById("authorInput").value;
    let minPrice = document.getElementById("minPriceInput").value;
    let maxPrice = document.getElementById("maxPriceInput").value;
    let result = {
        name: name,
        author: author,
        minPrice: minPrice,
        maxPrice: maxPrice
    }
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/books/search/",
        data: JSON.stringify(result),
        success: function (book) {
            let content = "";
            for (let i = 0; i < book.length; i++) {
                content += '<tr>\n' +
                    '        <td>' + book[i].code + '</td>\n' +
                    '        <td>' + book[i].name + '</td>\n' +
                    '        <td>' + book[i].author + '</td>\n' +
                    '        <td>' + book[i].price + '</td>\n' +
                    '        <td><button type="submit" onclick="deleteBook(' + book[i].id + ')">DELETE</button></td>\n' +
                    '        <td><button onclick="updateBook(' + book[i].id + ')">UPDATE</button></td>\n' +
                    '    </tr>';
                document.getElementById("content").innerHTML = content;
            }
        }
    })
}

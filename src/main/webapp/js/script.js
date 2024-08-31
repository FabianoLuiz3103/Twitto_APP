/**
 * 
 */
function getUsers() {
    fetch('/UserServlet')
        .then(response => response.text())
        .then(data => {
            document.getElementById('userList').innerHTML = data;
        });
}


setInterval(getUsers, 5000);

getUsers(); // Chame a função inicialmente

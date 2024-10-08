<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>JSP - Hello World</title>
    <style>
        body {
            background-color: #f8f9fa; /* Light background for the body */
        }
        h1 {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">My Application</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <span> <a class="nav-link" href="productServlet?action=list">Quản lý sản phẩm </a> </span>

            </li>
            <li class="nav-item active">
                <span> <a class="nav-link" href="accountServlet?action=list">Quản lý người dùng </a></span>>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Another Link</a>
            </li>
            <!-- Add more links as needed -->
        </ul>
    </div>
</nav>

<h1><%= "Hello World!" %></h1>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        img {
            width: 100px;
            height: 100px;
        }
        .bt1 {
            background-color: red;
            color: white; /* Make the text white for better visibility */
            padding: 5px 10px; /* Add some padding */
            text-decoration: none; /* Remove underline */
        }
        .bt2 {
            background-color: aqua;
            color: black; /* Make the text black for contrast */
            padding: 5px 10px; /* Add some padding */
            text-decoration: none; /* Remove underline */
        }
        .button-container {
            display: flex; /* Use flexbox layout */
            justify-content: space-between; /* Space between search and sort */
            align-items: center; /* Center align vertically */
            margin-bottom: 20px; /* Space below the button container */
        }
        .search-container input[type="text"] {
            padding: 5px;
            width: 300px; /* Fixed width for the search input */
        }
        .search-container button {
            padding: 5px 10px; /* Button padding */
        }
        .sort-container select {
            padding: 5px; /* Padding for select dropdown */
            margin-left: 10px; /* Space between select and the previous element */
        }
    </style>
</head>
<body>

<!-- Search and Sort Form Container -->
<div class="button-container">
    <!-- Search Form -->
    <div class="search-container">
        <form action="<%=request.getContextPath()%>/userServlet" method="get">
            <input type="text" name="search" placeholder="Tìm kiếm sản phẩm..." value="${param.search}">
            <input type="hidden" name="action" value="search">
            <button type="submit">Tìm kiếm</button>
        </form>
    </div>

    <!-- Sort Form -->
    <div class="sort-container">
        <form action="${pageContext.request.contextPath}/userServlet" method="get">
            <input type="hidden" name="action" value="sort" />
            <select name="sortOrder" onchange="this.form.submit()">
                <option value="asc" <c:if test="${param.sortOrder == 'asc'}">selected</c:if>>Tên (Tăng dần)</option>
                <option value="desc" <c:if test="${param.sortOrder == 'desc'}">selected</c:if>>Tên (Giảm dần)</option>
            </select>
        </form>
    </div>
</div>

<p><a href="<%=request.getContextPath()%>/userServlet?action=add">Thêm mới người dùng</a></p>

<h1>Danh sách người dùng</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Tên người dùng</th>
        <th>Email</th>
        <th>Quốc gia</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.country}</td>
            <td><a href="<%=request.getContextPath()%>/userServlet?action=edit&id=${user.id}" class="bt1">Edit</a></td>
            <td><a href="<%=request.getContextPath()%>/userServlet?action=delete&id=${user.id}" class="bt2" onclick="return confirm('Bạn có chắc xóa không?')">Delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
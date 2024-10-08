<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Danh sách người dùng</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f8f9fa;
      margin: 0;
      padding: 20px;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }
    th {
      background-color: #007bff; /* Header background color */
      color: white; /* Header text color */
    }
    tr:nth-child(even) {
      background-color: #f2f2f2; /* Light gray for even rows */
    }
    tr:hover {
      background-color: #d1e7fd; /* Light blue on hover */
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
      border-radius: 4px; /* Rounded corners */
    }
    .bt1:hover {
      background-color: #c82333; /* Darker red on hover */
    }
    .bt2 {
      background-color: aqua;
      color: black; /* Make the text black for contrast */
      padding: 5px 10px; /* Add some padding */
      text-decoration: none; /* Remove underline */
      border-radius: 4px; /* Rounded corners */
    }
    .bt2:hover {
      background-color: #7ec6db; /* Darker aqua on hover */
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
      background-color: #007bff; /* Button background color */
      color: white; /* Button text color */
      border: none; /* Remove border */
      border-radius: 4px; /* Rounded corners */
      cursor: pointer; /* Pointer cursor on hover */
    }
    .search-container button:hover {
      background-color: #0056b3; /* Darker blue on hover */
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
    <form action="<%=request.getContextPath()%>/accountServlet" method="get">
      <input type="text" name="search" placeholder="Tìm kiếm sản phẩm..." value="${param.search}">
      <input type="hidden" name="action" value="search">
      <button type="submit">Tìm kiếm</button>
    </form>
  </div>

  <!-- Sort Form -->
  <div class="sort-container">
    <form action="${pageContext.request.contextPath}/accountServlet" method="get">
      <input type="hidden" name="action" value="sort" />
      <select name="sortOrder" onchange="this.form.submit()">
        <option value="asc" <c:if test="${param.sortOrder == 'asc'}">selected</c:if>>Tên (Tăng dần)</option>
        <option value="desc" <c:if test="${param.sortOrder == 'desc'}">selected</c:if>>Tên (Giảm dần)</option>
      </select>
    </form>
  </div>
</div>

<p><a href="<%=request.getContextPath()%>/accountServlet?action=add" class="bt1">Thêm mới người dùng</a></p>

<h1>Danh sách sản phẩm</h1>
<table>
  <tr>
    <th>ID</th>
    <th>Tên người dùng</th>
    <th>Mật khẩu người dùng</th>
    <th>Quyền hạn</th>
    <th>Trạng thái người dùng</th>
    <th colspan="2">Action</th>
  </tr>
  <c:forEach items="${list}" var="account">
    <tr>
      <td>${account.accId}</td>
      <td>${account.userName}</td>
      <td>${account.password}</td>
      <td style="color: ${account.permission ? 'blue' : 'red'}; background-color: ${account.permission ? '#cce5ff' : '#f8d7da'}; border: 1px solid #007bff; padding: 8px;">${account.permission ? 'Admin' : 'User'}</td>
      <td style="color: ${account.accStatus ? 'blue' : 'red'}; background-color: ${account.accStatus ? '#cce5ff' : '#f8d7da'}; border: 1px solid #007bff; padding: 8px;">${account.accStatus ? 'Active' : 'Inactive'}</td>
      <td><a href="<%=request.getContextPath()%>/accountServlet?action=edit&id=${account.accId}" class="bt1">Edit</a></td>
      <td><a href="<%=request.getContextPath()%>/accountServlet?action=delete&id=${account.accId}" class="bt2" onclick="return confirm('Bạn có chắc xóa không?')">Delete</a></td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
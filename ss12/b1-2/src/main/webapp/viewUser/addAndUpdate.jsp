<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${user != null ? "Cập nhật người dùng" : "Thêm mới người dùng"}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        h1 {
            margin-bottom: 20px;
            color: #343a40;
        }
        .form-control {
            border-radius: 0.25rem;
        }
        .table {
            border: 1px solid #dee2e6;
            border-radius: 0.25rem;
        }
        .table th {
            background-color: #007bff;
            color: white;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <form action="<%=request.getContextPath()%>/userServlet" method="post">
        <h1>${user != null ? "Cập nhật người dùng" : "Thêm mới người dùng"}</h1>

        <table class="table table-bordered">
            <tr>
                <th>Tên người dùng</th>
                <td><input type="text" name="name" class="form-control" value="${user != null ? user.name : ''}" required></td>
            </tr>
            <tr>
                <th>Email</th>
                <td><input type="text" name="email" class="form-control" value="${user != null ? user.email : ''}" required></td>
            </tr>
            <tr>
                <th>Quốc gia</th>
                <td><input type="text" name="country" class="form-control" value="${user != null ? user.country : ''}" required></td>
            </tr>
            <c:if test="${user != null}">
                <input type="hidden" name="id" value="${user.id}">
            </c:if>
            <!-- Submit Button for Add/Edit -->
            <tr>
                <td colspan="2">
                    <input type="submit" name="action" value="${user != null ? 'edit' : 'add'}" class="btn btn-primary">
                </td>
            </tr>
        </table>

    </form>
</div>

</body>
</html>
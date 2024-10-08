<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${account != null ? "Cập nhật tài khoản" : "Thêm mới tài khoản"}</title>
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


    </style>
</head>
<body>

<div class="container mt-5">
    <form action="<%=request.getContextPath()%>/accountServlet" method="post">
        <h1>${account != null ? "Cập nhật tài khoản" : "Thêm mới tài khoản"}</h1>

        <table class="table table-bordered">
            <tr>
                <th>Tên đăng nhập</th>
                <td>
                    <input type="text" name="userName" class="form-control" value="${account != null ? account.userName : ''}" required>
                </td>
            </tr>
            <tr>
                <th>Mật khẩu</th>
                <td>
                    <input type="text" name="password" class="form-control" value="${account != null ? account.password : ''}" required>
                </td>
            </tr>
            <tr>
                <th>Quyền</th>
                <td>
                    <input type="radio" checked name="permission" value="true"
                           class="permission-true" ${account != null && account.permission ? 'checked' : ''} required> Admin
                    <input type="radio" name="permission" value="false"
                           class="permission-false"  ${account != null && !account.permission ? 'checked' : ''} required> User
                </td>
            </tr>
            <tr>
                <th>Trạng thái tài khoản</th>
                <td>
                    <input type="radio" checked name="accStatus" value="true" ${account != null && account.accStatus ? 'checked' : ''} required> Hoạt động
                    <input type="radio" name="accStatus" value="false" ${account != null && !account.accStatus ? 'checked' : ''} required> Đã khóa
                </td>
            </tr>
            <!-- Hidden input for accId when updating -->
            <c:if test="${account != null}">
                <input type="hidden" name="accId" value="${account.accId}">
            </c:if>

            <!-- Submit Button -->
            <tr>
                <td colspan="2" class="text-center">
                    <input type="submit" name="action" value="${account != null ? 'edit' : 'add'}" class="btn btn-primary">
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
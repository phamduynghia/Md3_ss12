<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>${product != null ? "Cập nhật sản phẩm" : "Thêm mới sản phẩm"}</title>
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
  <form action="<%=request.getContextPath()%>/productServlet" method="post">
    <h1>${product != null ? "Cập nhật sản phẩm" : "Thêm mới sản phẩm"}</h1>

    <table class="table table-bordered">
      <tr>
        <th>Tên sản phẩm</th>
        <td>
          <input type="text" name="productName" class="form-control" value="${product != null ? product.productName : ''}" required>
        </td>
      </tr>
      <tr>
        <th>Nhà sản xuất</th>
        <td>
          <input type="text" name="manufacturer" class="form-control" value="${product != null ? product.manufacturer : ''}" required>
        </td>
      </tr>
      <c:if test="${product == null}">
        <tr>
          <th>Ngày tạo</th>
          <td>
              <%--                    <c:if  test="${product !=null}">--%>
              <%--                    <input  type="date" name="created" class="form-control" value="${product != null ? product.created != null ? product.created.format('yyyy-MM-dd') : '' : ''}" required>--%>
              <%--                    </c:if>--%>

            <input type="date" name="created" class="form-control" value="" required>

          </td>
        </tr>
      </c:if>
      <tr>
        <th>Loại lô hàng</th>
        <td>
          <input type="number" name="batch" class="form-control" value="${product != null ? product.batch : ''}" required>
        </td>
      </tr>
      <tr>
        <th>Số lượng</th>
        <td>
          <input type="number" name="quantity" class="form-control" value="${product != null ? product.quantity : ''}" required>
        </td>
      </tr>
      <tr>
        <th>Trạng thái sản phẩm</th>
        <td>
          <select name="productStatus" class="form-control" required>
            <option value="true" ${product != null && product.productStatus ? 'selected' : ''}>Còn hàng</option>
            <option value="false" ${product != null && !product.productStatus ? 'selected' : ''}>Hết hàng</option>
          </select>
        </td>
      </tr>
      <!-- Hidden input để truyền ID khi cập nhật -->
      <c:if test="${product != null}">
        <input type="hidden" name="productId" value="${product.productId}">
      </c:if>

      <!-- Nút Submit -->
      <tr>
        <td colspan="2" class="text-center">
          <input type="submit" name="action" value="${product != null ? 'edit' : 'add'}" class="btn btn-primary">
        </td>
      </tr>
    </table>
  </form>
</div>

</body>
</html>
package com.ra.controller;

import com.ra.model.User; // Nhập mô hình User
import com.ra.service.IGenericDesign; // Nhập giao diện IGenericDesign
import com.ra.service.unit.UserImpl; // Nhập lớp UserImpl

import javax.servlet.ServletException; // Nhập các ngoại lệ Servlet
import javax.servlet.annotation.WebServlet; // Nhập chú thích Servlet
import javax.servlet.http.HttpServlet; // Nhập lớp HttpServlet
import javax.servlet.http.HttpServletRequest; // Nhập lớp HttpServletRequest
import javax.servlet.http.HttpServletResponse; // Nhập lớp HttpServletResponse
import java.io.IOException; // Nhập ngoại lệ IOException
import java.util.Comparator; // Nhập lớp Comparator
import java.util.List; // Nhập lớp List
import java.util.stream.Collectors; // Nhập các phương thức Stream Collectors

@WebServlet(name ="userServlet", value = "/userServlet") // Chú thích để định nghĩa servlet
public class UserServlet extends HttpServlet {
    // Khai báo một đối tượng IGenericDesign cho User
    private static final IGenericDesign<User,Integer> userImpl=new UserImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); // Lấy tham số action từ yêu cầu
        if (action != null) {
            switch (action) {
                case "sort": { // Trường hợp sắp xếp
                    // Lấy danh sách người dùng
                    List<User> userList = userImpl.getAll();
                    String sortOrder = req.getParameter("sortOrder"); // Lấy thứ tự sắp xếp từ yêu cầu

                    // Kiểm tra nếu sortOrder được cung cấp và sắp xếp danh sách người dùng tương ứng
                    if ("asc".equalsIgnoreCase(sortOrder)) {
                        // Sắp xếp theo tên theo thứ tự tăng dần (không phân biệt chữ hoa chữ thường)
                        userList = userList.stream()
                                .sorted(Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER))
                                .collect(Collectors.toList());
                    } else if ("desc".equalsIgnoreCase(sortOrder)) {
                        // Sắp xếp theo tên theo thứ tự giảm dần (không phân biệt chữ hoa chữ thường)
                        userList = userList.stream()
                                .sorted(Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER).reversed())
                                .collect(Collectors.toList());
                    } else {
                        // Nếu sortOrder không hợp lệ, có thể giữ nguyên thứ tự ban đầu hoặc xử lý theo cách khác
                        System.out.println("Tham số sortOrder không hợp lệ: " + sortOrder);
                    }

                    // Đặt danh sách người dùng đã sắp xếp làm thuộc tính yêu cầu
                    req.setAttribute("list", userList);
                    // Chuyển tiếp yêu cầu đến trang JSP để hiển thị danh sách đã sắp xếp
                    req.getRequestDispatcher("ViewUser/list.jsp").forward(req, resp);
                    break;
                }

                case "search": { // Trường hợp tìm kiếm
                    String search = req.getParameter("search"); // Lấy từ tìm kiếm từ yêu cầu
                    if (search != null && !search.isBlank()) { // Kiểm tra xem từ tìm kiếm có hợp lệ không
                        search = search.toLowerCase(); // Chuyển thành chữ thường
                        String finalSearch = search; // Biến cuối để sử dụng trong lambda
                        // Lọc danh sách người dùng theo quốc gia
                        List<User> matchingSearch = userImpl.getAll().stream()
                                .filter(user -> user.getCountry().toLowerCase().contains(finalSearch)).toList();
                        req.setAttribute("list", matchingSearch); // Đặt danh sách tìm thấy làm thuộc tính yêu cầu
                    } else {
                        req.setAttribute("list", userImpl.getAll()); // Đặt tất cả người dùng nếu không có từ tìm kiếm
                    }
                    req.getRequestDispatcher("ViewUser/list.jsp").forward(req, resp); // Chuyển tiếp đến JSP
                    break;
                }

                case "list": { // Trường hợp hiển thị danh sách
                    req.setAttribute("list", userImpl.getAll()); // Đặt tất cả người dùng làm thuộc tính yêu cầu
                    req.getRequestDispatcher("/ViewUser/list.jsp").forward(req, resp); // Chuyển tiếp đến JSP
                    break;
                }

                case "delete": { // Trường hợp xóa
                    Integer id = Integer.parseInt(req.getParameter("id")); // Lấy ID từ yêu cầu
                    userImpl.remove(id); // Xóa người dùng theo ID
                    resp.sendRedirect(req.getContextPath() + "/userServlet?action=list"); // Chuyển hướng về danh sách người dùng
                    break;
                }

                case "add": { // Trường hợp thêm người dùng
                    req.getRequestDispatcher("/ViewUser/addAndUpdate.jsp").forward(req, resp); // Chuyển tiếp đến trang thêm hoặc cập nhật
                    break;
                }

                case "edit": { // Trường hợp chỉnh sửa người dùng
                    Integer editId = Integer.parseInt(req.getParameter("id")); // Lấy ID để chỉnh sửa
                    User user = userImpl.getAll().stream()
                            .filter(c -> c.getId().equals(editId)) // Tìm người dùng theo ID
                            .findFirst()
                            .orElse(null); // Nếu không tìm thấy thì trả về null
                    if (user != null) {
                        req.setAttribute("user", user); // Đặt người dùng cần chỉnh sửa làm thuộc tính yêu cầu
                        req.getRequestDispatcher("/ViewUser/addAndUpdate.jsp").forward(req, resp); // Chuyển tiếp đến trang thêm hoặc cập nhật} else {
                    }else {
                        resp.sendRedirect(req.getContextPath() + "/userServlet?action=list");
                    }

                    break;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // Đặt mã hóa ký tự cho yêu cầu
        resp.setCharacterEncoding("UTF-8"); // Đặt mã hóa ký tự cho phản hồi
        String action = req.getParameter("action"); // Lấy tham số action từ yêu cầu
        switch (action) {
            case "add": { // Trường hợp thêm người dùng
                String name = req.getParameter("name"); // Lấy tên từ yêu cầu
                String email = req.getParameter("email"); // Lấy email từ yêu cầu
                String country = req.getParameter("country"); // Lấy quốc gia từ yêu cầu
                User user = new User(null, name, email, country); // Tạo đối tượng User mới
                userImpl.addAndUpdate(user); // Thêm hoặc cập nhật người dùng
                resp.sendRedirect(req.getContextPath() + "/userServlet?action=list"); // Chuyển hướng về danh sách người dùng
                break;
            }
            case "edit": { // Trường hợp chỉnh sửa người dùng
                Integer id = Integer.parseInt(req.getParameter("id")); // Lấy ID từ yêu cầu
                String editName = req.getParameter("name"); // Lấy tên đã chỉnh sửa từ yêu cầu
                String editEmail = req.getParameter("email"); // Lấy email đã chỉnh sửa từ yêu cầu
                String editCountry = req.getParameter("country"); // Lấy quốc gia đã chỉnh sửa từ yêu cầu
                User editUser = new User(id, editName, editEmail, editCountry); // Tạo đối tượng User đã chỉnh sửa
                userImpl.addAndUpdate(editUser); // Thêm hoặc cập nhật người dùng
                resp.sendRedirect(req.getContextPath() + "/userServlet?action=list"); // Chuyển hướng về danh sách người dùng
                break;
            }
            default: { // Trường hợp mặc định
                resp.sendRedirect(req.getContextPath() + "/userServlet?action=list"); // Chuyển hướng về danh sách người dùng
                break;
            }
        }
    }
}
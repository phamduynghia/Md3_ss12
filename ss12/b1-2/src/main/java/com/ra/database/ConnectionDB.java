package com.ra.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    // Thông tin cấu hình kết nối cơ sở dữ liệu
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Trình điều khiển JDBC
    private static final String URL = "jdbc:mysql://localhost:3306/s12b1"; // Địa chỉ URL của cơ sở dữ liệu
    private static final String USERNAME = "root"; // Tên người dùng
    private static final String PASSWORD = "duong123"; // Mật khẩu người dùng

    /**
     * Phương thức để lấy kết nối đến cơ sở dữ liệu.
     *
     * @return Kết nối đến cơ sở dữ liệu.
     */
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER); // Tải trình điều khiển
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Thiết lập kết nối
            return conn; // Trả về kết nối
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e); // Xử lý ngoại lệ nếu có lỗi xảy ra
        }
    }

    /**
     * Phương thức để đóng kết nối đến cơ sở dữ liệu.
     *
     * @param conn Kết nối cần đóng.
     */
    public static void closeConnection(Connection conn) {
        try {
            if (!conn.isClosed()) { // Kiểm tra xem kết nối có đang mở không
                conn.close(); // Đóng kết nối
            }
        } catch (SQLException e) {
            throw new RuntimeException(); // Xử lý ngoại lệ nếu có lỗi xảy ra
        }
    }
}
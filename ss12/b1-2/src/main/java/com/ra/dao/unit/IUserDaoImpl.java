package com.ra.dao.unit;

import com.ra.dao.IUserDao;
import com.ra.database.ConnectionDB;
import com.ra.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IUserDaoImpl implements IUserDao<User, Integer> {

    /**
     * Trả về danh sách tất cả người dùng từ cơ sở dữ liệu.
     *
     * @return Danh sách người dùng.
     */
    @Override
    public List<User> getAll() {
        Connection conn = ConnectionDB.getConnection(); // Kết nối đến cơ sở dữ liệu
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users"); // Chuẩn bị truy vấn
            ResultSet rs = ps.executeQuery(); // Thực thi truy vấn và lấy kết quả
            while (rs.next()) {
                User user = new User(); // Tạo đối tượng User
                user.setId(rs.getInt("id")); // Thiết lập ID
                user.setName(rs.getString("name")); // Thiết lập tên
                user.setEmail(rs.getString("email")); // Thiết lập email
                user.setCountry(rs.getString("country")); // Thiết lập quốc gia
                list.add(user); // Thêm người dùng vào danh sách
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Xử lý ngoại lệ nếu có lỗi xảy ra
        } finally {
            ConnectionDB.closeConnection(conn); // Đóng kết nối
        }
        return list; // Trả về danh sách người dùng
    }

    /**
     * Thêm mới hoặc cập nhật người dùng trong cơ sở dữ liệu.
     *
     * @param user Đối tượng User cần thêm hoặc cập nhật.
     */
    @Override
    public void addAndUpdate(User user) {
        Connection conn = ConnectionDB.getConnection(); // Kết nối đến cơ sở dữ liệu
        PreparedStatement ps = null;
        try {
            // Nếu ID của người dùng là null, thực hiện thêm mới
            if (user.getId() == null) {
                ps = conn.prepareStatement("insert into users(name,email,country) values(?,?,?)");
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getCountry());
            } else {
                // Nếu không, thực hiện cập nhật
                ps = conn.prepareStatement("update users set name=?,email=?,country=? where id=?");
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getCountry());
                ps.setInt(4, user.getId());
            }
            ps.executeUpdate(); // Thực thi câu lệnh thêm hoặc cập nhật
        } catch (SQLException e) {
            throw new RuntimeException(e); // Xử lý ngoại lệ nếu có lỗi xảy ra
        } finally {
            ConnectionDB.closeConnection(conn); // Đóng kết nối
        }
    }

    /**
     * Xóa người dùng khỏi cơ sở dữ liệu theo ID.
     *
     * @param id ID của người dùng cần xóa.
     */
    @Override
    public void delete(Integer id) {
        Connection conn = ConnectionDB.getConnection(); // Kết nối đến cơ sở dữ liệu
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("delete from users where id=?"); // Chuẩn bị câu lệnh xóa
            ps.setInt(1, id); // Thiết lập ID
            ps.executeUpdate(); // Thực thi câu lệnh xóa
        } catch (SQLException e) {
            throw new RuntimeException(e); // Xử lý ngoại lệ nếu có lỗi xảy ra
        } finally {
            ConnectionDB.closeConnection(conn); // Đóng kết nối
        }
    }

    /**
     * Lấy ID mới cho người dùng dựa trên ID lớn nhất hiện có trong cơ sở dữ liệu.
     *
     * @param id Đối tượng User, ID sẽ được bỏ qua.
     * @return ID mới cho người dùng.
     */
    @Override
    public Integer getNewId(User id) {
        Connection conn = ConnectionDB.getConnection(); // Kết nối đến cơ sở dữ liệu
        try {
            PreparedStatement ps = conn.prepareStatement("select max(id) as max_id from users");
            ResultSet rs = ps.executeQuery(); // Thực thi truy vấn để lấy ID lớn nhất
            if (rs.next()) {
                return rs.getInt("max_id") + 1; // Trả về ID lớn nhất cộng thêm 1
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Xử lý ngoại lệ nếu có lỗi xảy ra
        } finally {
            ConnectionDB.closeConnection(conn); // Đóng kết nối
        }
        return 1; // Trả về 1 nếu không có ID nào
    }

    /**
     * Tìm chỉ số của người dùng theo ID trong cơ sở dữ liệu.
     *
     * @param id ID của người dùng cần tìm.
     * @return Chỉ số của người dùng trong danh sách hoặc -1 nếu không tìm thấy.
     */
    @Override
    public int findIndexById(Integer id) {
        Connection conn = ConnectionDB.getConnection(); // Kết nối đến cơ sở dữ liệu
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM users ORDER BY id"); // Chuẩn bị truy vấn
            ResultSet rs = ps.executeQuery(); // Thực thi truy vấn

            List<Integer> idList = new ArrayList<>(); // Tạo danh sách ID
            while (rs.next()) {
                idList.add(rs.getInt("id")); // Thêm ID vào danh sách
            }

            return idList.indexOf(id); // Tìm chỉ số của ID
        } catch (SQLException e) {
            throw new RuntimeException(e); // Xử lý ngoại lệ nếu có lỗi xảy ra
        } finally {
            ConnectionDB.closeConnection(conn); // Đóng kết nối
        }
    }
}
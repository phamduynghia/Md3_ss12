package com.ra.dao.unit;

import com.ra.database.ConnectionDB;
import com.ra.model.Account;
import com.ra.dao.IGenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements IGenericDao<Account, Integer> {



    @Override
    public void andAndUpdate(Account account) {
        Connection conn = null; // Khai báo đối tượng kết nối để xử lý kết nối cơ sở dữ liệu.
        CallableStatement statement = null; // Khai báo CallableStatement để thực thi thủ tục lưu trữ.

        try {
            conn = ConnectionDB.getConnection(); // Lấy kết nối đến cơ sở dữ liệu.

            // Kiểm tra xem ID tài khoản có null hoặc bằng 0 để xác định nếu đây là tài khoản mới hay đã tồn tại.
            if (account.getAccId() == null || account.getAccId() == 0) {
                // Nếu tài khoản mới, chuẩn bị CallableStatement để gọi thủ tục lưu trữ insert_Account.
                statement = conn.prepareCall("{call insert_Account(?, ?, ?, ?)}");

                // Thiết lập các tham số cho thủ tục insert.
                statement.setString(1, account.getUserName()); // Thiết lập tên người dùng.
                statement.setString(2, account.getPassword()); // Thiết lập mật khẩu.
                statement.setBoolean(3, account.isPermission()); // Thiết lập quyền hạn (có hay không).
                statement.setBoolean(4, account.isAccStatus()); // Thiết lập trạng thái tài khoản (hoạt động hay không).
            } else {
                // Nếu tài khoản đã tồn tại, chuẩn bị CallableStatement để gọi thủ tục update_Account.
                statement = conn.prepareCall("{call update_Account(?, ?, ?, ?, ?)}");

                // Thiết lập các tham số cho thủ tục update.
                statement.setInt(1, account.getAccId()); // Thiết lập ID tài khoản.
                statement.setString(2, account.getUserName()); // Thiết lập tên người dùng.
                statement.setString(3, account.getPassword()); // Thiết lập mật khẩu.
                statement.setBoolean(4, account.isPermission()); // Thiết lập quyền hạn (có hay không).
                statement.setBoolean(5, account.isAccStatus()); // Thiết lập trạng thái tài khoản (hoạt động hay không).
            }

            // Thực thi thủ tục lưu trữ để thêm hoặc cập nhật tài khoản.
            statement.executeUpdate(); // Thực hiện thao tác chèn hoặc cập nhật vào cơ sở dữ liệu.

        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có ngoại lệ SQL.
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi cho các ngoại lệ khác.
        } finally {
            // Đảm bảo rằng CallableStatement và Connection được đóng để giải phóng tài nguyên.
            if (statement != null) {
                try {
                    statement.close(); // Đóng CallableStatement.
                } catch (SQLException e) {
                    e.printStackTrace(); // In ra lỗi khi đóng CallableStatement.
                }
            }
            ConnectionDB.closeConnection(conn); // Đảm bảo rằng kết nối cơ sở dữ liệu được đóng.
        }
    }

    // Phương thức xóa tài khoản dựa trên ID
    @Override
    public void remove(Integer id) {
        Connection conn = null; // Khai báo đối tượng kết nối.
        CallableStatement statement = null; // Khai báo statement.

        try {
            conn = ConnectionDB.getConnection(); // Lấy kết nối đến cơ sở dữ liệu.
            statement = conn.prepareCall("{call delete_Account(?)}"); // Chuẩn bị CallableStatement để gọi thủ tục xóa.
            statement.setInt(1, id); // Thiết lập ID tài khoản cần xóa.
            statement.executeUpdate(); // Thực hiện thao tác xóa trong cơ sở dữ liệu.
        } catch (SQLException e) {
            throw new RuntimeException(e); // Ném ngoại lệ SQL nếu có lỗi xảy ra.
        } finally {
            if (statement != null) {
                try {
                    statement.close(); // Đóng CallableStatement.
                } catch (SQLException e) {
                    e.printStackTrace(); // In ra lỗi khi đóng CallableStatement.
                }
            }
            ConnectionDB.closeConnection(conn); // Đảm bảo rằng kết nối cơ sở dữ liệu được đóng.
        }
    }

    // Phương thức tìm chỉ số của tài khoản dựa trên ID
    @Override
    public int findIndexByID(Integer id) {
        Connection conn = null; // Khai báo đối tượng kết nối.
        List<Integer> list = new ArrayList<>(); // Danh sách để chứa các ID tài khoản.

        try {
            conn = ConnectionDB.getConnection(); // Lấy kết nối đến cơ sở dữ liệu.
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM account"); // Chuẩn bị truy vấn để lấy tất cả tài khoản.
            ResultSet rs = ps.executeQuery(); // Thực thi truy vấn và nhận kết quả.

            // Lặp qua các kết quả trả về và thêm ID vào danh sách.
            while (rs.next()) {
                list.add(rs.getInt("accId")); // Thêm mỗi ID tài khoản vào danh sách.
            }
            return list.indexOf(id); // Trả về chỉ số của ID được chỉ định trong danh sách.

        } catch (SQLException e) {
            throw new RuntimeException(e); // Ném ngoại lệ SQL nếu có lỗi xảy ra.
        } finally {
            ConnectionDB.closeConnection(conn); // Đảm bảo rằng kết nối cơ sở dữ liệu được đóng.
        }
    }

    // Phương thức lấy tất cả tài khoản
    @Override
    public List<Account> findAll() {
        Connection conn = null; // Khai báo đối tượng kết nối.
        List<Account> list = new ArrayList<>(); // Danh sách để chứa các đối tượng Account.

        try {
            conn = ConnectionDB.getConnection(); // Lấy kết nối đến cơ sở dữ liệu.
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM account"); // Chuẩn bị truy vấn để lấy tất cả tài khoản.
            ResultSet rs = ps.executeQuery(); // Thực thi truy vấn và nhận kết quả.

            // Lặp qua các kết quả trả về và tạo đối tượng Account cho mỗi tài khoản.
            while (rs.next()) {
                Account account = new Account(); // Tạo một đối tượng Account mới.
                account.setAccId(rs.getInt("accId")); // Thiết lập ID tài khoản.
                account.setUserName(rs.getString("userName")); // Thiết lập tên người dùng.
                account.setPassword(rs.getString("password")); // Thiết lập mật khẩu.
                account.setPermission(rs.getBoolean("permission")); // Thiết lập quyền hạn.
                account.setAccStatus(rs.getBoolean("accStatus")); // Thiết lập trạng thái tài khoản.
                list.add(account); // Thêm tài khoản vào danh sách.
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Ném ngoại lệ SQL nếu có lỗi xảy ra.
        } finally {
            ConnectionDB.closeConnection(conn); // Đảm bảo rằng kết nối cơ sở dữ liệu được đóng.
        }
        return list; // Trả về danh sách các tài khoản.
    }

    // Phương thức lấy ID mới cho tài khoản
    @Override
    public Integer getNewId(Account account) {
        Connection conn = null; // Khai báo đối tượng kết nối.

        try {
            conn = ConnectionDB.getConnection(); // Lấy kết nối đến cơ sở dữ liệu.
            PreparedStatement ps = conn.prepareStatement("SELECT MAX(accId) AS Max_id FROM account"); // Chuẩn bị truy vấn để lấy ID lớn nhất.
            ResultSet rs = ps.executeQuery(); // Thực thi truy vấn và nhận kết quả.

            if (rs.next()) {
                return rs.getInt("Max_id") + 1; // Trả về ID tiếp theo có sẵn.
            }

        } catch (SQLException e) {
            throw new RuntimeException(e); // Ném ngoại lệ SQL nếu có lỗi xảy ra.
        } finally {
            ConnectionDB.closeConnection(conn); // Đảm bảo rằng kết nối cơ sở dữ liệu được đóng.
        }
        return 1; // Trả về 1 nếu không có ID nào tồn tại.
    }
}
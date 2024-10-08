package com.ra.dao.unit;

import com.ra.model.Product;
import com.ra.dao.IGenericDao;
import com.ra.database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements IGenericDao<Product, Integer> {

    @Override
    public void andAndUpdate(Product product) {
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = null;
        try {
            if (product.getProductId() == null) {
                // thêm mới
                ps = conn.prepareStatement("insert into product (productName,manufacturer,created,batch,quantity,productStatus) values(?,?,?,?,?,?)");
                ps.setString(1, product.getProductName());
                ps.setString(2, product.getManufacturer());
                ps.setDate(3, new java.sql.Date(product.getCreated().getTime()));
                ps.setByte(4, product.getBatch());
                ps.setInt(5, product.getQuantity());
                ps.setBoolean(6, product.isProductStatus());

            } else {
                ps = conn.prepareStatement("Update product set productName=?,manufacturer=?,batch=?,quantity=?,productStatus=? where productId=?");
                // cập nhật thông tin nếu tồn tại ID
                ps.setString(1, product.getProductName());
                ps.setString(2, product.getManufacturer());
//                ps.setDate(3, new java.sql.Date(product.getCreated().getTime()));
                ps.setByte(3, product.getBatch());
                ps.setInt(4, product.getQuantity());
                ps.setBoolean(5, product.isProductStatus());
                ps.setInt(6, product.getProductId());

            }
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public void remove(Integer id) {
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("delete from product where productId=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }

    }

    @Override
    public int findIndexByID(Integer id) {
        Connection conn = ConnectionDB.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from product");
            ResultSet rs = ps.executeQuery();
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("productId"));
            }
            return list.indexOf(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public List<Product> findAll() {
        Connection conn = ConnectionDB.getConnection();
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from product");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setManufacturer(rs.getString("manufacturer"));
                product.setCreated(new java.sql.Date(rs.getDate("created").getTime()));
                product.setQuantity(rs.getInt("quantity"));
                product.setBatch(rs.getByte("batch"));
                product.setProductStatus(rs.getBoolean("productStatus"));
                list.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public Integer getNewId(Product product) {
        Connection conn = ConnectionDB.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select max(productId) as max_ID from product");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max_ID") + 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return 1;
    }
}
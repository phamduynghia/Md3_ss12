package com.ra.controller;

import com.ra.model.Product;
import com.ra.service.IGenericService;
import com.ra.service.unit.ProductImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "productServlet", value = "/productServlet")
public class ProductController extends HttpServlet {
    private static final IGenericService<Product,Integer> productImpl=new ProductImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    req.getRequestDispatcher("/ViewProduct/addAndUpdate.jsp").forward(req, resp);
                    break;
                case "list":
                    req.setAttribute("list",productImpl.findAll());
                    req.getRequestDispatcher("/ViewProduct/listProduct.jsp").forward(req, resp);
                    break;
                case "delete":
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    productImpl.delete(id);
                    resp.sendRedirect(req.getContextPath()+"/productServlet?action=list");
                    break;
                case "edit":
                    Integer editId = Integer.parseInt(req.getParameter("id"));
                    Product product=productImpl.findAll().stream().
                            filter(product1 -> product1.getProductId().equals(editId)).findFirst().orElse(null);
                    if (product != null) {
                        req.setAttribute("product", product);
                        req.getRequestDispatcher("/ViewProduct/addAndUpdate.jsp").forward(req, resp);
                    } else {
                        resp.sendRedirect(req.getContextPath()+"/productServlet?action=list");
                    }
                    break;

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action=req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    String productName = req.getParameter("productName");
                    String manufacturer = req.getParameter("manufacturer");
                    Byte batch=Byte.parseByte(req.getParameter("batch"));
                    String createdStr=req.getParameter("created");
                    Date createdDate = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if (createdStr != null && !createdStr.trim().isEmpty()) {
                        try {
                            createdDate = sdf.parse(createdStr);
                        } catch (ParseException e) {
                            req.setAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
                            req.getRequestDispatcher("/error.jsp").forward(req, resp);
                            return; // Dừng xử lý nếu có lỗi
                        }
                    } else {
                        req.setAttribute("errorMessage", "Created date cannot be empty.");
                        req.getRequestDispatcher("/error.jsp").forward(req, resp);
                        return; // Dừng xử lý nếu có lỗi
                    }


                    Integer quantity=Integer.parseInt(req.getParameter("quantity"));
                    Boolean productStatus=Boolean.parseBoolean(req.getParameter("productStatus"));
                    Product newProduct =new Product();
                    newProduct.setProductId(null);
                    newProduct.setProductName(productName);
                    newProduct.setManufacturer(manufacturer);
                    newProduct.setBatch(batch);
                    newProduct.setCreated(createdDate);
                    newProduct.setQuantity(quantity);
                    newProduct.setProductStatus(productStatus);
                    productImpl.addAndUpdate(newProduct);
                    resp.sendRedirect(req.getContextPath()+"/productServlet?action=list");

                    break;
                case "edit":
                    Integer editId = Integer.parseInt(req.getParameter("productId"));
                    String editName = req.getParameter("productName");
                    String editManufacturer = req.getParameter("manufacturer");
                    Byte editBatch=Byte.parseByte(req.getParameter("batch"));
                    Integer editQuantity=Integer.parseInt(req.getParameter("quantity"));
                    Boolean editProductStatus=Boolean.parseBoolean(req.getParameter("productStatus"));
                    Product editProduct=new Product(editId,editName,editManufacturer,editBatch,editQuantity,editProductStatus);
                    productImpl.addAndUpdate(editProduct);
                    resp.sendRedirect(req.getContextPath()+"/productServlet?action=list");
                    break;
            }
        }
    }
}
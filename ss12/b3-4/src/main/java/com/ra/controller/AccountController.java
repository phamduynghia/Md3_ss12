package com.ra.controller;

import com.ra.model.Account;
import com.ra.model.Product;
import com.ra.service.IGenericService;
import com.ra.service.unit.AccountImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "accountServlet",value = "/accountServlet")

public class AccountController extends HttpServlet {
    private static final IGenericService<Account,Integer> accountImpl=new AccountImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "list":
                    req.setAttribute("list",accountImpl.findAll());
                    req.getRequestDispatcher("/ViewAccount/listAccount.jsp").forward(req,resp);
                    break;
                case "add":
                    resp.sendRedirect(req.getContextPath()+"/ViewAccount/addAndUpdate.jsp");
                    break;
                case "delete":
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    accountImpl.delete(id);
                    resp.sendRedirect(req.getContextPath()+"/accountServlet?action=list");
                    break;
                case "edit":
                    Integer editID=Integer.parseInt(req.getParameter("id"));
                    Account account=accountImpl.findAll().stream().
                            filter(account1 -> account1.getAccId().equals(editID)).findFirst().orElse(null);
                    if (account != null) {
                        req.setAttribute("account", account);
                        req.getRequestDispatcher("/ViewAccount/addAndUpdate.jsp").forward(req,resp);
                    }else {
                        resp.sendRedirect(req.getContextPath()+"/accountServlet?action=list");
                    }
                    break;





            }
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action=req.getParameter("action");
        switch (action){
            case "add":
                String name = req.getParameter("userName");
                String password = req.getParameter("password");
                Boolean permission = Boolean.parseBoolean(req.getParameter("permission"));
                Boolean status = Boolean.parseBoolean(req.getParameter("accStatus"));
                Account account =new Account();
                account.setAccId(null);
                account.setUserName(name);
                account.setPassword(password);
                account.setPermission(permission);
                account.setAccStatus(status);
                accountImpl.addAndUpdate(account);
                resp.sendRedirect(req.getContextPath()+"/accountServlet?action=list");
                break;
            case "edit":
                Integer editId=Integer.parseInt(req.getParameter("accId"));
                String editName = req.getParameter("userName");
                String editPassword = req.getParameter("password");
                boolean editPermission = Boolean.parseBoolean(req.getParameter("permission"));
                boolean editStatus = Boolean.parseBoolean(req.getParameter("accStatus"));
                Account editAccount =new Account();
                editAccount.setAccId(editId);
                editAccount.setUserName(editName);
                editAccount.setPassword(editPassword);
                editAccount.setPermission(editPermission);
                editAccount.setAccStatus(editStatus);
                accountImpl.addAndUpdate(editAccount);
                resp.sendRedirect(req.getContextPath()+"/accountServlet?action=list");
                break;

        }
    }

}
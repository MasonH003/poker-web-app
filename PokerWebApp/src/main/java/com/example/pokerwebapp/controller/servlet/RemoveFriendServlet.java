package com.example.pokerwebapp.controller.servlet;


import com.example.pokerwebapp.controller.service.AccountService;
import com.example.pokerwebapp.model.dao.AccountDAO;
import com.example.pokerwebapp.model.entity.Account;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "removeFriendServlet", value = "/removeFriendServlet")
public class RemoveFriendServlet extends HttpServlet {
    protected AccountDAO dao = new AccountDAO();

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Account logged = (Account) request.getSession().getAttribute("Account");
        String accountUsernameToRemove = request.getParameter("remove");
        Account toRemove = dao.findUserByUsername(accountUsernameToRemove);
        AccountService.removeFriend(logged,toRemove);
        response.sendRedirect("testfriendaction.jsp");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }

}
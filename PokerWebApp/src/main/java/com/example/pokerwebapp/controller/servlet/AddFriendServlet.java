package com.example.pokerwebapp.controller.servlet;

import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.dao.AccountDAO;

import com.example.pokerwebapp.controller.service.AccountService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "addFriendServlet", value = "/addFriendServlet")
public class AddFriendServlet extends HttpServlet {
    protected AccountDAO dao = new AccountDAO();
    public void setDao(AccountDAO newdao){
        this.dao = newdao;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Account of the currently logged-in user
        Account logged = (Account) request.getSession().getAttribute("Account");

        String accountUsernameToAdd = request.getParameter("friendname");

        //Account of the person you want to add
        Account toAdd = dao.findUserByUsername(accountUsernameToAdd);

        AccountService.addFriend(logged,toAdd);

        response.sendRedirect("index.jsp");
    }

}
package com.example.pokerwebapp.controller.servlet;

import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.dao.GenericDAO;
import com.example.pokerwebapp.model.dao.AccountDAO;

import com.example.pokerwebapp.controller.service.AccountService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.pokerwebapp.model.entity.Account;

import java.io.IOException;

@WebServlet(name = "accountActionServlet", value = "/accountActionServlet")
public class AccountActionServlet extends HttpServlet {



    protected AccountDAO dao = new AccountDAO();
    public void setDao(AccountDAO newdao){
        this.dao = newdao;
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        dao.setDbTypeOutput(GenericDAO.DbType.TEST);

        String one = request.getParameter("accountID");
        String two = request.getParameter("friendID");


        int accountID = Integer.parseInt(one);
        int friendID = Integer.parseInt(two);


        Account account = dao.read(accountID);
        Account friend = dao.read(friendID);

        AccountService.addFriend(account,friend);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }

}
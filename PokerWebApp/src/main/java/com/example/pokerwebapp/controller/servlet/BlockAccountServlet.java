package com.example.pokerwebapp.controller.servlet;


import com.example.pokerwebapp.controller.service.AccountService;
import com.example.pokerwebapp.model.dao.AccountDAO;
import com.example.pokerwebapp.model.entity.Account;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "blockAccountServlet", value = "/blockAccountServlet")
public class BlockAccountServlet extends HttpServlet {
    //protected AccountDAO dao = new AccountDAO();


//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//
//}
}
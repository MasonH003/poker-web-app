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

import java.io.IOException;

@WebServlet(name = "accountActionServlet", value = "/accountActionServlet")
public class AccountActionServlet extends HttpServlet {


    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }

}
package com.example.pokerwebapp.controller.servlet;


import java.io.*;

import com.example.pokerwebapp.controller.service.AccountService;
import com.example.pokerwebapp.model.entity.Account;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "registerAccountServlet", value = "/registerAccountServlet")
public class RegisterAccountServlet extends HttpServlet {


    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strLogin = request.getParameter("txt_reg_username");
        String strPass = request.getParameter("txt_reg_pw");

        //System.out.println("hello!:) "+strLogin+strPass);
        Account newUser = new Account();
        newUser.setUsername(strLogin);
        newUser.setPassword(strPass);
        newUser.setMoney( 500 );
        newUser.setPermission( 0 );


        Account registered = AccountService.registerUser(newUser);
        if(registered==null) {
            response.sendRedirect("registernewaccount.jsp?error=1");
        } else {
            //Lets also "log-in"
            HttpSession session = request.getSession();
            registered.setPassword("");
            session.setAttribute("User", registered);
            response.sendRedirect("registrationsuccess.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);
    }

}
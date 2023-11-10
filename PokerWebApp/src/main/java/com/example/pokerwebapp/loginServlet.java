//package com.example.pokerwebapp;
//
////import edu.loyola.cs.se.parkinglotexample.controller.service.UserService;
//// edu.loyola.cs.se.parkinglotexample.model.entity.User;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//
//@WebServlet(name = "loginServlet", value = "/loginServlet")
//public class loginServlet extends HttpServlet {
//
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        System.out.println("Security Issue: passwords should never be sent via GET.");
//        response.sendRedirect("index.jsp");
//    }
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        String login = request.getParameter("txt_login");
//        String unhashedPass = request.getParameter("txt_pass");
//        User logged = UserService.loginUser(login, unhashedPass);
//
//        if(logged!=null){
//
//            //Session is how we (safe) persist info from a specif user in a webapp
//            HttpSession session = request.getSession();
//            logged.setPassword(""); //For security lets scrub the password
//            session.setAttribute("User",logged); //adding user to session
//            response.sendRedirect("index.jsp");
//        } else {
//            response.sendRedirect("login.jsp?msg=1");
//        }
//    }
//}

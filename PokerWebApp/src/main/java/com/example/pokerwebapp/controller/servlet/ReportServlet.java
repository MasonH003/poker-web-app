package com.example.pokerwebapp.controller.servlet;

import com.example.pokerwebapp.controller.service.AccountService;
import com.example.pokerwebapp.controller.service.ReportService;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Report;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "reportAccountServlet", value = "/reportAccountServlet")
public class ReportServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Account logged = (Account) request.getSession().getAttribute("Account");
        String toReport = request.getParameter("report_name");
        String report_message = request.getParameter("report_message");
        Account reported = AccountService.dao.findUserByUsername(toReport);

        if(reported != null && toReport != "")
        {
            Report r = new Report(logged, reported, report_message);
            Report created = ReportService.createReport(r);
            if(created == null)
            {
                //reported the same account twice (not allowed)
            }
            else{
                response.sendRedirect("index.jsp");
            }

        }
        else{
            //account reported not found
        }
    }



}
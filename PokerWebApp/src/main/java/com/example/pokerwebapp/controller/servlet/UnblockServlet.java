package com.example.pokerwebapp.controller.servlet;

import com.example.pokerwebapp.controller.service.BlockService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "unblockServlet", value = "/unblockServlet")
public class UnblockServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int block_id = Integer.parseInt(request.getParameter("block_id"));
        BlockService.unblock(block_id);
        response.sendRedirect("friends.jsp");
    }
}

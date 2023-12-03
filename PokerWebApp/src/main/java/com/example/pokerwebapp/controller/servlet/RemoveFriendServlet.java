package com.example.pokerwebapp.controller.servlet;
import com.example.pokerwebapp.controller.service.FriendshipService;
import com.example.pokerwebapp.model.entity.Account;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "removeFriendServlet", value = "/removeFriendServlet")
public class RemoveFriendServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int remove_id = Integer.parseInt(request.getParameter("friendship_id"));
        FriendshipService.removeFriend(remove_id);
        response.sendRedirect("index.jsp");
    }

}
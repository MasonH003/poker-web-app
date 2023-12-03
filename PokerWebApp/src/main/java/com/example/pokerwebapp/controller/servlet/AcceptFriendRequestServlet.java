package com.example.pokerwebapp.controller.servlet;

import com.example.pokerwebapp.controller.service.AccountService;
import com.example.pokerwebapp.controller.service.FriendshipService;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Friendship;
import com.example.pokerwebapp.model.entity.FriendshipStatus;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet(name = "acceptFriendRequestServlet", value = "/acceptFriendRequestServlet")
public class AcceptFriendRequestServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            int friendship_id = Integer.parseInt(request.getParameter("friendship_ID"));
            FriendshipService.acceptFriendRequest(friendship_id);
            response.sendRedirect("index.jsp");
        }
}





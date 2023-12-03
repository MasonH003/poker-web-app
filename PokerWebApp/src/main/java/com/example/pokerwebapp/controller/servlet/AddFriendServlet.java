package com.example.pokerwebapp.controller.servlet;

import com.example.pokerwebapp.controller.service.AccountService;
import com.example.pokerwebapp.controller.service.FriendshipService;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Friendship;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "addFriendServlet", value = "/addFriendServlet")
public class AddFriendServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Account sender = (Account) request.getSession().getAttribute("Account");
        String friend = request.getParameter("friend");
        Account receiver = AccountService.dao.findUserByUsername(friend);
        if(receiver != null && friend != "")
        {
            Friendship newFriendship = new Friendship(sender, receiver);
            Friendship created = FriendshipService.createFriendship(newFriendship);
            if (created == null) {
                response.sendRedirect("index.jsp?msg=1");
            } else
            {
                response.sendRedirect("index.jsp");
            }
        }
        else
        {
            response.sendRedirect("index.jsp?msg=2");
        }

    }

}
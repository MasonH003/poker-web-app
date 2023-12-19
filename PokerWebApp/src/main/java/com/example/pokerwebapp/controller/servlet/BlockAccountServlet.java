package com.example.pokerwebapp.controller.servlet;


import com.example.pokerwebapp.controller.service.AccountService;
import com.example.pokerwebapp.controller.service.BlockService;
import com.example.pokerwebapp.controller.service.FriendshipService;
import com.example.pokerwebapp.model.dao.AccountDAO;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Friendship;
import com.example.pokerwebapp.model.entity.Block;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "blockAccountServlet", value = "/blockAccountServlet")
public class BlockAccountServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Account sender = (Account) request.getSession().getAttribute("Account");
        String block = request.getParameter("block");
        Account toBlock = AccountService.dao.findUserByUsername(block);
        if(block != null && block != "" && sender.getID() != toBlock.getID())
        {
            Block b = new Block(sender, toBlock);
            Block created = BlockService.createBlock(b);
            if (created == null) {
                response.sendRedirect("index.jsp");
            } else
            {
                response.sendRedirect("index.jsp");
            }
        }
        else
        {
            response.sendRedirect("index.jsp");
        }

    }
}
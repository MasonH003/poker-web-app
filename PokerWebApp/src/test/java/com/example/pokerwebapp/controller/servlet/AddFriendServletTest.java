package com.example.pokerwebapp.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddFriendServletTest {

//
//    @Test
//    public void testDoGet() throws IOException, ServletException {
//
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        PrintWriter printMock = mock(PrintWriter.class);
//
//        when(request.getParameter("friend")).thenReturn("test@test.com");
//
//        AddFriendServlet servlet = new AddFriendServlet();
//        servlet.doGet(request,response);
//
//    }

    @Test public void testDoPostAddFriend() throws IOException, ServletException{
        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);

        when(request.getParameter("txt_id")).thenReturn("201");



        //No Assertions, therefore not really testing anything
    }

}

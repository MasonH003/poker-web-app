package com.example.pokerwebapp.controller.servlet;
import com.example.pokerwebapp.controller.service.AccountService;
import com.example.pokerwebapp.model.entity.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginServletTest {
    @Test
    public void testDoPostNormalPath() throws IOException, ServletException {
        Account answer = new Account();
        answer.setID(1);
        answer.setUsername("test@test.test");
        answer.setPassword("test");

        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); //In this case, also Session

        //Setup the mock "inputs" on request
        when(request.getParameter("txt_login")).thenReturn("test@test.test");
        when(request.getParameter("txt_pass")).thenReturn("test");
        when(request.getSession()).thenReturn(sessionMock);

        //Also need to mock UserService, since it is static method, the mocking is different
        try (MockedStatic<AccountService> service = mockStatic(AccountService.class)) {
            //Inside try block, we can replace static method calls to UserService
            service.when(() -> AccountService.loginAccount(anyString(), anyString())).thenReturn(answer);

            LoginServlet servlet = new LoginServlet();
            servlet.doPost(request, response);

            assertAll("LoginUserServlet - Logged User Path Assertions",
                    () -> assertDoesNotThrow(
                            () -> verify(response).sendRedirect("play-poker"),
                            "LoginServlet should called sendRedirect(\"play-poker\")"),
                    () -> assertDoesNotThrow(
                            () -> verify(sessionMock).setAttribute(eq("Account"), any(Account.class)),
                            "LoginServlet should called session.setAttribute(\"Account\",logged); ")
            );
        }
    }


    @Test public void testDoPostLoginIncorrect() throws IOException, ServletException {
        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); //In this case, also Session

        //Setup the mock "inputs" on request
        when(request.getParameter("txt_login")).thenReturn("test@test.test");
        when(request.getParameter("txt_pass")).thenReturn("testering");
        when(request.getSession()).thenReturn(sessionMock);

        //Also need to mock AccountService, since it is static method, the mocking is different
        try (MockedStatic<AccountService> service = mockStatic(AccountService.class)) {
            //Inside try block, we can replace static method calls to AccountService
            service.when(() -> AccountService.loginAccount(anyString(), anyString())).thenReturn(null);

            LoginServlet servlet = new LoginServlet();
            servlet.doPost(request, response);

            assertAll("LoginUserServlet - Incorrect Login User Path Assertions",
                    () -> assertDoesNotThrow(
                            () -> verify(response).sendRedirect("play-poker?msg=1"),
                            "LoginServlet should called sendRedirect(\"play-poker?msg=1\")"),
                    () -> assertDoesNotThrow(
                            () -> verify(sessionMock, never()).setAttribute(eq("Account"), any(Account.class)),
                            "LoginServlet should never call session.setAttribute(...); ")
            );
        }
    }

    @Test public void testDoGet() throws IOException, ServletException{
        //Servlets always have two main parameters request & repost which we need to mock
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class); //In this case, also Session

        //Setup the mock "inputs" on request
        when(request.getParameter("txt_pass")).thenReturn("test");
        when(request.getSession()).thenReturn(sessionMock);

        LoginServlet servlet = new LoginServlet();
        servlet.doGet(request, response);

        assertAll("LoginUserServlet - doGet Assertions",
                () -> assertDoesNotThrow(
                        () -> verify(response).sendRedirect("play-poker"),
                        "LoginServlet.doGet should called sendRedirect(\"player-poker\")"),
                () -> assertDoesNotThrow(
                        () -> verify(request,never()).getSession(),
                        "LoginServlet.doGet should never called request.getSession()"),
                () -> assertDoesNotThrow(
                        () -> verify(request,never()).getParameter(eq("txt_pass")),
                        "LoginServlet.doGet should never call request.getParameter(txt_pass); ")
        );

    }

}
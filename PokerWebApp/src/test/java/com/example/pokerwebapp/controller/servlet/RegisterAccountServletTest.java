package com.example.pokerwebapp.controller.servlet;

import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.controller.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterAccountServletTest {

    @Test public void testDoPostSuccessfulRegister() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);

        when(request.getParameter("txt_reg_username")).thenReturn("test@test");
        when(request.getParameter("txt_reg_pw")).thenReturn("testpw");
        when(request.getSession()).thenReturn(sessionMock);


        try (MockedStatic<AccountService> service = mockStatic(AccountService.class)) {

            service.when(() -> AccountService.registerUser(any()) ).thenReturn(new Account());

            RegisterAccountServlet servlet = new RegisterAccountServlet();
            servlet.doPost(request,response);

            assertAll("Register User Successfully assertions",
                    ()-> assertDoesNotThrow(
                            () -> verify(sessionMock).setAttribute(eq("User"),any(Account.class)),
                            "RegisterAccountServlet should called session.setAttribute(\"User\",userobject)"),
                    ()-> assertDoesNotThrow(
                            () -> verify(response).sendRedirect("registrationsuccess.jsp"),
                            "RegisterAccountServlet should called sendRedirect(\"registrationsuccess.jsp\")")
            );
        }
    }

    @Test public void testDoPostNotRegister() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession sessionMock = mock(HttpSession.class);

        when(request.getParameter("txt_reg_username")).thenReturn("test@test.com");
        when(request.getParameter("txt_reg_pw")).thenReturn("123456");
        when(request.getSession()).thenReturn(sessionMock);


        try (MockedStatic<AccountService> service = mockStatic(AccountService.class)) {

            service.when(() -> AccountService.registerUser(any()) ).thenReturn(null);

            RegisterAccountServlet servlet = new RegisterAccountServlet();
            servlet.doPost(request,response);

            assertAll("Register user - User not register assertions",
                    ()-> assertDoesNotThrow(
                            () -> verify(sessionMock, never()).setAttribute(eq("User"),any(Account.class)),
                            "RegisterUserServlet should never called session.setAttribute(...) when user is not register."),
                    ()-> assertDoesNotThrow(
                            () -> verify(response).sendRedirect("registernewaccount.jsp?error=1"),
                            "RegisterUserServlet should called sendRedirect(\"registernewaccount.jsp?error=1\")")
            );
        }
    }
}
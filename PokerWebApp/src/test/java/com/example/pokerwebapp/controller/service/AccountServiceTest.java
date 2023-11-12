package com.example.pokerwebapp.controller.service;

import com.example.pokerwebapp.model.dao.AccountDAO;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.util.PasswordUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    @Test
    public void testRegisterUser(){
        Account registered = new Account(101,"acc@ount.com",PasswordUtil.hash("test"), Account.NORMAL_PERMISSION);
        Account newAccount = new Account(null,"acc@ount.com","test", Account.NORMAL_PERMISSION);

        AccountDAO mockDAO = mock(AccountDAO.class);
        when(mockDAO.create(any(Account.class))).thenReturn(registered);
        com.example.pokerwebapp.controller.service.AccountService.setDAO(mockDAO);

        Account returned = com.example.pokerwebapp.controller.service.AccountService.registerUser(newAccount);

        assertAll("Register User Service Assertions",
                () -> assertEquals(returned.getID(),registered.getID(), "Registered ID should be 101"),
                () -> assertEquals(returned.getUsername(), registered.getUsername(), "Registered Login should be test@test.com")
        );
    }

    @Test
    public void testRegisteredUserCatchingException(){
        Account newAccount = new Account();
        newAccount.setPassword("123");

        AccountDAO mockDAO = mock(AccountDAO.class);
        when(mockDAO.create(any(Account.class))).thenThrow(new javax.persistence.PersistenceException("Test Exception"));
        AccountService.setDAO(mockDAO);

        Account returned = AccountService.registerUser(newAccount);

        assertNull(returned, "Returned user should be null if Login exists (exception).");
    }

    @Test
    public void testLoginSuccessful(){
        String email = "test@test.com";
        String unhashed = "654321";
        String hashed = PasswordUtil.hash(unhashed);
        Account answer = new Account(101,email,hashed, Account.NORMAL_PERMISSION);

        AccountDAO mockDAO = mock(AccountDAO.class);
        when(mockDAO.findUserByUsername(anyString())).thenReturn(answer);
        AccountService.setDAO(mockDAO);

        Account logged = AccountService.loginAccount(email,unhashed);

        assertAll("Login User Service assertion",
                ()-> assertNotNull(logged,"Logged user cannot be null") ,
                ()-> assertEquals(logged.getID(), answer.getID())
        );
    }

    @Test
    public void testLoginFailurePasswordDotNotMatch(){
        String email = "test@test.com";
        String incorrectPass = "654321";
        String hashed = PasswordUtil.hash("123456");
        Account answer = new Account(101,email,hashed, Account.NORMAL_PERMISSION);

        AccountDAO mockDAO = mock(AccountDAO.class);
        when(mockDAO.findUserByUsername(anyString())).thenReturn(answer);
        AccountService.setDAO(mockDAO);

        Account logged = AccountService.loginAccount(email,incorrectPass);

        assertNull(logged,"Logged must be null if password is incorrect");
    }

    @Test
    public void testListUsers(){
        AccountDAO mockDAO = mock(AccountDAO.class);
        when(mockDAO.list(anyString())).thenReturn(new ArrayList<Account>());
        AccountService.setDAO(mockDAO);

        List<Account> lst = AccountService.listAccounts("Login");
        assertNotNull(lst);
    }

    @Test
    public void smokeTestDeleteUser(){
        AccountDAO mockDAO = mock(AccountDAO.class);
        AccountService.setDAO(mockDAO);

        assertDoesNotThrow( ()-> AccountService.deleteAccount(1) );
    }

    @Test
    public void testEditUserNoPassword(){
        Account registered = new Account(101,"test@test.com",PasswordUtil.hash("123456"), Account.NORMAL_PERMISSION);
        Account updatedAccount = new Account(101,"test@test.com",null, Account.NORMAL_PERMISSION);

        AccountDAO mockDAO = mock(AccountDAO.class);
        when(mockDAO.update(any(Account.class))).thenReturn(registered);
        when(mockDAO.read(anyInt())).thenReturn(registered);
        AccountService.setDAO(mockDAO);

        Account returned = AccountService.editAccount(updatedAccount);

        assertAll("Edit User Assertions -- No password",
                () -> assertEquals(returned.getID(),registered.getID(), "Updated User ID should be 101"),
                () -> assertEquals(returned.getPassword(), registered.getPassword(), "Updated User Password should be a hashed version from read()"),
                () -> assertDoesNotThrow(
                        () -> verify(mockDAO).read(eq(updatedAccount.getID())),"editUser should have called dao.read(101)"
                ),
                () -> assertDoesNotThrow(
                        () -> verify(mockDAO).update(any(Account.class)),"editUser should have called dao.update()"
                )
        );
    }

    @Test
    public void testEditUserWithPassword(){
        String unhashedPass = "123456";
        Account updatedAccount = new Account(101,"test@test.com",unhashedPass, Account.NORMAL_PERMISSION);

        AccountDAO mockDAO = mock(AccountDAO.class);
        when(mockDAO.update(any(Account.class))).thenReturn(updatedAccount);
        AccountService.setDAO(mockDAO);

        Account returned = AccountService.editAccount(updatedAccount);

        assertAll("Edit User Assertions -- No password",
                () -> assertEquals(returned.getID(), updatedAccount.getID(), "Updated User ID should be 101"),
                () -> assertNotEquals(returned.getPassword(), unhashedPass, "Returned User Password should be a hashed version from read()"),
                () -> assertDoesNotThrow(
                        () -> verify(mockDAO, never()).read(anyInt()),"editUser should never called dao.read() for a user with password"
                ),
                () -> assertDoesNotThrow(
                        () -> verify(mockDAO).update(any(Account.class)),"editUser should have called dao.update()"
                )
        );
    }
}
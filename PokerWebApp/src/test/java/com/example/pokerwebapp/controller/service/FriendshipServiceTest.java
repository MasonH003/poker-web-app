package com.example.pokerwebapp.controller.service;
import com.example.pokerwebapp.model.dao.FriendshipDAO;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Friendship;
import com.example.pokerwebapp.util.PasswordUtil;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class FriendshipServiceTest {
    @Test
    public void testCreateFriendship()
    {
        //setup data & expected return
        Account account = new Account(101,"acc@ount.com", PasswordUtil.hash("test"), Account.NORMAL_PERMISSION);
        Account friend = new Account(102,"acc2@ount.com",PasswordUtil.hash("test2"), Account.NORMAL_PERMISSION);
        Friendship f = new Friendship(account, friend);

        //test the control layer
        FriendshipDAO mockDAO = mock(FriendshipDAO.class);
        when(mockDAO.findFriendshipByAccounts(account, friend)).thenReturn(null);
        when(mockDAO.create(any(Friendship.class))).thenReturn(f);
        FriendshipService.setDAO(mockDAO);

        //Method under test
        Friendship created = FriendshipService.createFriendship(f);

        assertAll("Add Friend FriendshipService Assertions",

                () -> assertEquals(created.getAccount(), f.getAccount(), "Account's match"),
                () -> assertEquals(created.getFriend(), f.getFriend(), "Friend's match"),
                () -> assertNotNull(created, "Friendship cannot be null")
        );
    }


    @Test
    public void testCreateFriendshipCatchingException()
    {
        //setup data & expected return
        Account account = new Account(101,"acc@ount.com", PasswordUtil.hash("test"), Account.NORMAL_PERMISSION);
        Account friend = new Account(102,"acc2@ount.com",PasswordUtil.hash("test2"), Account.NORMAL_PERMISSION);
        Friendship f = new Friendship(account, friend);

        //test the control layer
        FriendshipDAO mockDAO = mock(FriendshipDAO.class);
        when(mockDAO.findFriendshipByAccounts(account, friend)).thenReturn(null);
        when(mockDAO.create(any(Friendship.class))).thenThrow(new javax.persistence.PersistenceException("Test Exception"));
        FriendshipService.setDAO(mockDAO);

        //Method under test
        Friendship created = FriendshipService.createFriendship(f);

        assertNull(created, "Returned user should be null (exception).");
    }
    //TODO more tests



}

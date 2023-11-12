package com.example.pokerwebapp.model.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test public void testAccountNoConstructor(){
        Account acc = new Account();
        assertAll(
                ()-> assertNull(acc.getID()),
                ()-> assertNull(acc.getUsername()),
                ()-> assertNull(acc.getPassword()),
                ()-> assertEquals(acc.getPermission(), Account.NORMAL_PERMISSION),
                ()-> assertEquals(acc.getPermissionAsString(), "Normal")
        );
    }

    @Test public void testAccountConstructor(){
        Account acc = new Account(5,"test@test","test",Account.ADMIN_PERMISSION);
        assertAll(
                ()-> assertEquals(acc.getID(), 5),
                ()-> assertEquals(acc.getUsername(),"test@test"),
                ()-> assertEquals(acc.getEmail(), acc.getUsername()),
                ()-> assertEquals(acc.getPassword(), "test"),
                ()-> assertEquals( acc.getPermission(), Account.ADMIN_PERMISSION),
                ()-> assertEquals(acc.getPermissionAsString(), "Admin")
        );
    }
    @Test public void testUnknownPermissionString(){
        Account acc = new Account();
        acc.setPermission(-1);
        assertEquals(acc.getPermissionAsString(),"Unknown");
    }
}
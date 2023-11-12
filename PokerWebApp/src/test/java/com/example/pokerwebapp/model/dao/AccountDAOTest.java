package com.example.pokerwebapp.model.dao;

//import com.example.sevencardstud.*;
import com.example.pokerwebapp.model.entity.Account;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountDAOTest {

    public static AccountDAO dao = null;

    /***
     * To facilitate tests, this method create a new User Entity object
     *
     * @return User with ID=null, login="test@test.com", password="111",
     */
    public static Account createNewUserEntity(){
        Account acc = new Account();
        acc.setUsername("test@test.com");
        acc.setPassword("111");
        acc.setPermission(Account.NORMAL_PERMISSION);
        return acc;
    }

    @BeforeAll public static void createDao() {
        dao = new AccountDAO();
        dao.setDbTypeOutput(GenericDAO.DbType.TEST);
    }
    @BeforeEach public void deleteAll(){
        dao.deleteAll();
    }

    @AfterAll public static void deleteAllAfter(){
        dao.deleteAll();
    }

    @Test public void testCreateUser(){
        Account newAccount = createNewUserEntity();

        dao.create(newAccount);
        Account found = dao.read(newAccount.getID());
        assertAll("Grouped Assertions of Create User",
                () -> assertNotNull(newAccount.getID(), "ID should not be null after creation"),
                () -> assertNotNull(found, "Found user after reading should not be null"),
                () -> assertEquals(newAccount.getUsername(), found.getUsername(), "Login for the newAccount must be equal to the Login for the found user"),
                () -> assertEquals(newAccount.getPermissionAsString(),found.getPermissionAsString())
        );
    }

    @Test public void testDeleteUserById(){
        Account newAccount = createNewUserEntity();
        dao.create(newAccount);
        dao.delete(newAccount.getID());
        Account found = dao.read(newAccount);
        assertNull(found, "User cannot be in the DB after deletion");
    }

    @Test public void smokeTestDeleteWhatDoesNotExists(){
        //Smoke test has no assertion, we are only testing if this does not raise any exceptions
        Account notsaved = createNewUserEntity();
        assertDoesNotThrow(() -> dao.delete(notsaved));
    }

    @Test public void testUpdateUser(){
        Account newAccount = createNewUserEntity();
        String newlogin = "Altered";
        dao.create(newAccount);
        newAccount.setUsername(newlogin);
        Account updated = dao.update(newAccount);
        Account found = dao.read(newAccount);
        assertAll("Grouped Assertions of Updated User",
                () -> assertEquals(updated.getUsername(),newlogin),
                () -> assertEquals(updated.getID(), found.getID()),
                () -> assertEquals(updated.getUsername(),found.getUsername()),
                () -> assertEquals(updated.getPermission(),found.getPermission())
        );
    }

    @Test public void testList(){
        Account u1 = createNewUserEntity();
        Account u2 = createNewUserEntity();
        Account u3 = createNewUserEntity();
        u1.setEmail("ZZ");
        u2.setEmail("LL");
        u3.setEmail("AA");
        dao.create(u1);
        dao.create(u2);
        dao.create(u3);
        java.util.List<Account> lstUser = dao.list("login");
        assertAll("Grouped Assertions for List User",
                () -> assertEquals(lstUser.size(), 3),
                () -> assertEquals(lstUser.get(0).getUsername(),"AA"),
                () -> assertEquals(lstUser.get(2).getEmail(),"ZZ")
        );
    }

    @Test
    public void testCreateUniqueConstraintLogin(){
        Account user1 = createNewUserEntity();
        dao.create(user1);

        Account user2 = createNewUserEntity();
        assertThrows(javax.persistence.PersistenceException.class,
                () -> dao.create(user2),
                "Login is a unique field in the DB, cannot have repeated login saved on create."
        );//Should not allow to create two user with the same Login
    }

    @Test
    public void testUpdateUniqueConstraintLogin(){
        Account user1 = createNewUserEntity();
        dao.create(user1);

        Account user2 = createNewUserEntity();
        user2.setUsername("different@test.com");
        dao.create(user2);

        user2.setUsername( user1.getUsername() ); //Set Login to one that in DB

        assertThrows(javax.persistence.PersistenceException.class,
                () -> dao.update(user2),
                "Login is a unique field in the DB, cannot have repeated login saved on update."
        );//Should not allow to create two user with the same Login
    }

    @Test public void testLoginSuccess(){
        Account newuser = createNewUserEntity();

        dao.create(newuser); //Create a new user
        Account logged = dao.findUserByUsername(newuser.getUsername());
        assertAll("Successful Login Assertions",
                ()-> assertNotNull(logged,"Logged user cannot be null"),
                ()-> assertNotNull(logged.getPassword(), "Password from logged user cannot be null"),
                ()-> assertTrue(logged.getPassword().length()>0, "Password cannot be empty")
        );
    }

    @Test public void testLoginFailure(){
        Account logged = dao.findUserByUsername("DoesNotExist@nowhere.com");
        assertNull(logged, "Logged User must return null if Login not found");
    }
}
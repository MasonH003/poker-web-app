package com.example.pokerwebapp.controller.service;

import com.example.pokerwebapp.model.dao.AccountDAO;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.util.PasswordUtil;
import java.util.List;

public class AccountService {

    public static AccountDAO dao = new AccountDAO();

    public static void setDAO(AccountDAO dao){
        AccountService.dao = dao;
    }

    /***
     * Registers new account in the DB by calling the DAO.
     * Although you could put this method inside the Servlet,
     *  by separating into another class, it is easier to test and mock
     * @param newAccount Account entity to save (without ID)
     * @return Saved Account Entity (with ID) or null if Login already exists
     */
    public static Account registerUser(Account newAccount){
        try {
            String hashed = PasswordUtil.hash(newAccount.getPassword());
            newAccount.setPassword(hashed);
            newAccount = dao.create(newAccount);
        }catch(javax.persistence.PersistenceException ex){
            //Repeated login
            newAccount = null;
            System.out.println(ex);
        }
        finally {
            return newAccount;
        }
    }

    /***
     * Checks if Login & Password matches the DB
     * @param username The login (email)
     * @param unhashedPassword Unhashed Password as String
     * @return The Account record if successful or null if Login/Password incorrect.
     */
    public static Account loginAccount(String username, String unhashedPassword){
        Account found = dao.findUserByUsername(username);
        if(found!=null){ //Found user by login
            //We must certify the passwords match
            if(PasswordUtil.compare(unhashedPassword,found.getPassword())){
                return found;
            }
            //I know I could combine both IFs in the same one,
            // I separated them to make it easier to explain the logic for some students
        }
        return null; //Login or Password incorrect
    }

    /***
     * Returns a list of all Accounts in the DB
     * Usually for an Admin CRUD needs to see all data
     * @param Order Which field to order the results
     * @return Account list
     */
    public static List<Account> listAccounts(String Order){
        List<Account> lstAccount = dao.list(Order);
        return lstAccount;
    }

    public static void deleteAccount(int id){
        dao.delete(id);
    }

    public static Account editAccount(Account u){

        if(u.getPassword()==null || u.getPassword().trim().length()==0){
            //No Password was given, we need to fetch it from DB
            Account original = dao.read(u.getID());
            u.setPassword(original.getPassword()); //original pass is already hashed (it is supposed to be!)
        }
        else{
            //Password was typed, we need to hash it
            String hashed = PasswordUtil.hash( u.getPassword() );
            u.setPassword(hashed);
        }

        return dao.update(u);
    }

    /***
     * Add a friend
     * @param account
     * @param accountToAdd
     */
    public static void addFriend(Account account, Account accountToAdd)
    {
        account.getFriends().add(accountToAdd);
        accountToAdd.getFriendOf().add(account);
        dao.update(account);
    //    dao.update(accountToAdd);

    }


    /***
     * Add a friend
     * @param account
     * @param accountToRemove
     */
    public static void removeFriend(Account account, Account accountToRemove)
    {
        account.getFriends().remove(accountToRemove);
        accountToRemove.getFriendOf().remove(account);
        dao.update(account);
       // dao.update(accountToRemove);

    }

}
// SPDX-License-Identifier: MIT

package com.example.pokerwebapp.model.dao;

import com.example.pokerwebapp.model.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/***
 * AccountDAO is a subclass of GenericDAO
 * It is a good practice to extend GenericDAO for each specific entity
 * Then we can add custom methods for this DAO (mostly, custom list operations)
 */
public class AccountDAO extends GenericDAO<Account> {

    public AccountDAO(){
        super(Account.class);
    }

    /***
     * Method used for login, finds the user tied to a specific login
     * @param username The login (email) which is unique in the DB
     * @return Account entity
     */
    public Account findUserByUsername(String username){
        EntityManager em = getEntityManager();

        String query = "SELECT u FROM "+getTableName()+" u WHERE u.username = :email"; // :email is a parameter, to avoid SQL Injection
        Account found = null;

        try {
            found = em.createQuery(query, Account.class).setParameter("email", username).getSingleResult();
        } catch(NoResultException ex){
            found = null;
        } finally{
            em.close();
        }
        return found;
    }

}
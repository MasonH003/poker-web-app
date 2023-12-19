package com.example.pokerwebapp.model.dao;

import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Friendship;
import com.example.pokerwebapp.model.entity.FriendshipStatus;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
public class FriendshipDAO extends GenericDAO<Friendship> {


    public FriendshipDAO(){
        super(Friendship.class);
    }

    public Friendship findFriendshipByAccounts(Account one, Account two)
    {
        EntityManager em = getEntityManager();
        String query = "Select f FROM "+getTableName()+" f WHERE f.account = :accountOne AND f.friend = :accountTwo OR " +
                "f.account = :accountTwo AND f.friend = :accountOne";

        Friendship found = null;

        try
        {
            found = em.createQuery(query, Friendship.class)
                    .setParameter("accountOne", one)
                    .setParameter("accountTwo", two)
                    .getSingleResult();
        }catch(NoResultException ex)
        {
            found = null;
        }finally{
            em.close();
        }
        return found;
    }


    //can make both of these into one function and just get the account/friend
    //TODO: Refactor this
    public List<Friendship> incomingFriendships(Account a)
    {
        EntityManager em = getEntityManager();
        String query = "Select f FROM "+getTableName()+" f WHERE f.friend = :receiver AND f.status = :status";
        List<Friendship> pending = null;
        try
        {
            pending = em.createQuery(query, Friendship.class)
                    .setParameter("receiver", a)
                    .setParameter("status", FriendshipStatus.PENDING)
                    .getResultList();
        }catch(NoResultException ex)
        {
            pending = null;
        }finally{
            em.close();
        }
        return pending;
    }


    public List<Friendship> sentFriendships(Account a)
    {
        EntityManager em = getEntityManager();
        String query = "Select f FROM "+getTableName()+" f WHERE f.account = :sender AND f.status = :status";
        List<Friendship> sent = null;
        try{
            sent = em.createQuery(query, Friendship.class)
                    .setParameter("sender", a)
                    .setParameter("status", FriendshipStatus.PENDING)
                    .getResultList();

        }catch(NoResultException ex)
        {
            sent = null;
        }finally{
            em.close();
        }
        return sent;
    }

    public List<Account> friends(Account a)
    {
        EntityManager em = getEntityManager();
        String query = "SELECT f FROM " + getTableName() + " f WHERE (f.account = :account OR f.friend = :account) AND f.status = :status";

        List<Friendship> accepted = null;
        List<Account> friends = new ArrayList<>();
        try{
           accepted = em.createQuery(query, Friendship.class)
                   .setParameter("account", a)
                   .setParameter("status", FriendshipStatus.ACCEPTED)
                   .getResultList();
           for (Friendship f : accepted)
           {
               if(f.getAccount().getID().equals(a.getID()))
               {
                   friends.add(f.getFriend());
               }
               else{
                   friends.add(f.getAccount());
               }
           }

        }catch(NoResultException ex)
        {
            accepted = null;
        }finally{
            em.close();
        }

        return friends;
    }





}

package com.example.pokerwebapp.model.dao;

import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Friendship;
import com.example.pokerwebapp.model.entity.FriendshipStatus;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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








//    public Friendship findFriendship(Account accountOne, Account accountTwo)
//    {
//        EntityManager em = getEntityManager();
//
//        String query = "Select f FROM "+getTableName()+" f WHERE f.user = :sender AND f.friend = :receiver";
//        Friendship found = null;
//        try {
//            found = em.createQuery(query, Friendship.class)
//                    .setParameter("accountOne",accountOne)
//                    .setParameter("accountTwo",accountTwo)
//                    .getSingleResult();
//        }
//        catch(NoResultException ex){
//            found = null;
//    } finally{
//            em.close();
//        }
//        return found;
//    }



    public List<Friendship> findSentPendingFriendRequests(Account user) {
        EntityManager em = getEntityManager();

        String query = "SELECT f FROM " + getTableName() + " f " +
                "WHERE f.account = :user AND f.status = :status";

        List<Friendship> sentPendingRequests = null;

        try {
            sentPendingRequests = em.createQuery(query, Friendship.class)
                    .setParameter("user", user)
                    .setParameter("status", FriendshipStatus.PENDING)
                    .getResultList();
        } finally {
            em.close();
        }

        return sentPendingRequests;
    }


    public List<Friendship> findIncomingFriendRequests(Account user) {
        EntityManager em = getEntityManager();

        String query = "SELECT f FROM " + getTableName() + " f " +
                "WHERE f.friend = :user AND f.status = :status";

        List<Friendship> incomingRequests = null;

        try {
            incomingRequests = em.createQuery(query, Friendship.class)
                    .setParameter("user", user)
                    .setParameter("status", FriendshipStatus.PENDING)
                    .getResultList();
        } finally {
            em.close();
        }

        return incomingRequests;
    }





    public void acceptFriendRequest(int friendshipId) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            // Retrieve the Friendship entity by ID
            Friendship friendship = em.find(Friendship.class, friendshipId);

            // Update the status to ACCEPTED
            friendship.setStatus(FriendshipStatus.ACCEPTED);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
            // Handle exceptions appropriately
        } finally {
            em.close();
        }
    }



}

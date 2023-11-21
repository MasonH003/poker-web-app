package com.example.pokerwebapp.model.dao;

import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Friendship;
import com.example.pokerwebapp.model.entity.FriendshipStatus;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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
}

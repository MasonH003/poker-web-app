package com.example.pokerwebapp.controller.service;
import com.example.pokerwebapp.model.dao.AccountDAO;
import com.example.pokerwebapp.model.dao.FriendshipDAO;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Friendship;
import com.example.pokerwebapp.model.entity.FriendshipStatus;
import com.mysql.cj.protocol.FullReadInputStream;

import java.util.List;

public class FriendshipService {

    public static FriendshipDAO dao = new FriendshipDAO();

    public static void setDAO(FriendshipDAO dao){
        FriendshipService.dao = dao;
    }


    public static Friendship createFriendship(Friendship f) {
        Friendship found = dao.findFriendshipByAccounts(f.getAccount(), f.getFriend());

        if(found == null) {
            try {
                f = dao.create(f);
            } catch (javax.persistence.PersistenceException ex) {
                f = null;
                System.out.println(ex);
            }
        }
        else
        {
            //friendship already exists
            f = null;
        }
        return f;
    }

    public static List<Friendship> listIncomingFriendships(Account a)
    {
        return dao.incomingFriendships(a);
    }
    public static List<Friendship> listSentFriendships(Account a)
    {
        return dao.sentFriendships(a);
    }
    public static void acceptFriendRequest(int friendship_id)
    {
        Friendship f = dao.read(friendship_id);
        f.setStatus(FriendshipStatus.ACCEPTED);
        dao.update(f);
    }

    public static void removeFriend(int friendship_id)
    {
         dao.delete(friendship_id);
    }

    public static List<Account> listFriends(Account a)
    {
        return dao.friends(a);
    }


}

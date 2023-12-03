package com.example.pokerwebapp.controller.service;
import com.example.pokerwebapp.model.dao.AccountDAO;
import com.example.pokerwebapp.model.dao.FriendshipDAO;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Friendship;
import com.example.pokerwebapp.model.entity.FriendshipStatus;

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
    public static void acceptFriendRequest(int friendship_id)
    {
        Friendship f = dao.read(friendship_id);
        f.setStatus(FriendshipStatus.ACCEPTED);
        dao.update(f);
    }


//    public static List<Friendship> pendingFriendships()
//    {
//
//    }



    public static List<Friendship> sentFriendRequests(Account logged)
    {
        return dao.findSentPendingFriendRequests(logged);
    }

    public static List<Friendship> incoming(Account user)
    {
        return dao.findIncomingFriendRequests(user);
    }
//    public static void acceptFriendRequest(Friendship f)
//    {
//        f.setStatus(FriendshipStatus.ACCEPTED);
//        dao.update(f);
//    }


}

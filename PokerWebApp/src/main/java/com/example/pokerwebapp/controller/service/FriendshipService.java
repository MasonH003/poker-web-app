package com.example.pokerwebapp.controller.service;
import com.example.pokerwebapp.model.dao.FriendshipDAO;
import com.example.pokerwebapp.model.entity.Friendship;
import com.example.pokerwebapp.model.entity.FriendshipStatus;


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



}

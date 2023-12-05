package com.example.pokerwebapp.controller.service;

import com.example.pokerwebapp.model.dao.BlockDAO;
import com.example.pokerwebapp.model.dao.FriendshipDAO;
import com.example.pokerwebapp.model.entity.Friendship;

public class BlockService {
    public static BlockDAO dao = new BlockDAO();

    public static void setDAO(BlockDAO dao){
        BlockService.dao = dao;
    }


//    public static Block createFriendship(Friendship f) {
//        Friendship found = dao.findFriendshipByAccounts(f.getAccount(), f.getFriend());
//
//        if(found == null) {
//            try {
//                f = dao.create(f);
//            } catch (javax.persistence.PersistenceException ex) {
//                f = null;
//                System.out.println(ex);
//            }
//        }
//        else
//        {
//            //friendship already exists
//            f = null;
//        }
//        return f;
//    }
}

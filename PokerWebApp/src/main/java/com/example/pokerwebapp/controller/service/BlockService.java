package com.example.pokerwebapp.controller.service;

import com.example.pokerwebapp.model.dao.BlockDAO;
import com.example.pokerwebapp.model.entity.Block;

import com.example.pokerwebapp.model.dao.FriendshipDAO;
import com.example.pokerwebapp.model.entity.Friendship;

public class BlockService {
    public static BlockDAO dao = new BlockDAO();

    public static void setDAO(BlockDAO dao){
        BlockService.dao = dao;
    }


    public static Block createBlock(Block b) {
        Block found = dao.existingBlock(b.getAccount(), b.getBlocked());

        if(found == null) {
            try {
                b = dao.create(b);
            } catch (javax.persistence.PersistenceException ex) {
                b = null;
                System.out.println(ex);
            }
        }
        else
        {
            b = null;
        }
        return b;
    }

}

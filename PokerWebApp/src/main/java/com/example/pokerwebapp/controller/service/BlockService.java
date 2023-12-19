package com.example.pokerwebapp.controller.service;

import com.example.pokerwebapp.model.dao.BlockDAO;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Block;

import com.example.pokerwebapp.model.dao.FriendshipDAO;
import com.example.pokerwebapp.model.entity.Friendship;

import java.util.List;

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
    public static List<Block> blockList(Account a)
    {
        return dao.blockList(a);
    }

    public static void unblock(int block_id)
    {
        dao.delete(block_id);
    }


}

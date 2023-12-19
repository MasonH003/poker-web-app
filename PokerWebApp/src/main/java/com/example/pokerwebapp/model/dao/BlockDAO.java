package com.example.pokerwebapp.model.dao;
import com.example.pokerwebapp.model.entity.Block;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Friendship;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class BlockDAO extends GenericDAO<Block>{

    public BlockDAO(){
        super(Block.class);
    }

    public Block existingBlock(Account one, Account two)
    {
        EntityManager em = getEntityManager();
        String query = "Select b FROM "+getTableName()+" b WHERE b.account = :account AND b.blocked = :blocked";
        Block found = null;
        try
        {
            found = em.createQuery(query, Block.class)
                    .setParameter("account", one)
                    .setParameter("blocked", two)
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

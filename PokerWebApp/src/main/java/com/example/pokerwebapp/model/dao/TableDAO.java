package com.example.pokerwebapp.model.dao;

import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public abstract class TableDAO extends GenericDAO<Table> {
    public AccountDAO(){
        super(Account.class);
    }

    public Table findTable(String find){
        EntityManager em = getEntityManager();

        String query = "SELECT u FROM "+getTableName()+" u WHERE u.username = :table_name"; // :email is a parameter, to avoid SQL Injection
        Table found = null;

        try
        {
            found = em.createQuery(query, Table.class).setParameter("table_name", find).getResultList();
        }
        catch(NoResultException ex){
            found = null;
        }
        finally{
            em.close();
        }
        return found;
    }
}
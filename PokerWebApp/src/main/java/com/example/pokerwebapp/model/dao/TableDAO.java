package com.example.pokerwebapp.model.dao;

import com.example.pokerwebapp.model.entity.TableEntity;

import javax.persistence.EntityManager;

public class TableDAO extends GenericDAO<TableEntity> {
    public TableDAO(){
        super(TableEntity.class);
    }

    public TableEntity read(String name){
        EntityManager em = this.getEntityManager();
        TableEntity entity = em.find(EntityClass, name);
        em.close();
        return entity;
    }
}

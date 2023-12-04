package com.example.pokerwebapp.model.dao;

import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.TableEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.List;

public class TableDAO extends GenericDAO<TableEntity> {
    public TableDAO(){
        super(TableEntity.class);
    }

    /***
     * Read operation for the specified table name.
     * @param t The table in the DB
     * @return A BaseEntity subclass with the appropriate record in the DB, or null if it does not exist
     */
    public TableEntity read(TableEntity t){
        EntityManager em = this.getEntityManager();
        TableEntity entity = em.find(EntityClass, t.getID());
        em.close();
        return entity;
    }


    public List<TableEntity> find(String table_name){
        List<TableEntity> results;
        EntityManager em = this.getEntityManager();
        try {
            String query = "SELECT u FROM "+getTableName()+" u WHERE u.table_name =:name";
            results=em.createQuery(query,TableEntity.class).setParameter("name", table_name).getResultList();
        } catch(NoResultException ex){
            results = null;
        } finally{
            em.close();
        }
        return results;
    }

    /***
     * Updates the entity in the DB
     * @param t to be updated
     * @param pl player count to be updated
     * @return The updated version of the entity
     */
    public TableEntity updatePlayers(TableEntity t, int pl){
    EntityManager em = this.getEntityManager();
        TableEntity updated = null;
        try {
        em.getTransaction().begin();
        t.setPlayers(pl);
        updated = em.merge(t);
        em.getTransaction().commit();
        em.close();
        }
        catch(Exception ex){
        em.getTransaction().rollback();
        em.close();
        throw ex;
        }
        return updated;
    }
}

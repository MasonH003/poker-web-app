package com.example.pokerwebapp.model.dao;

import com.example.pokerwebapp.model.entity.TableEntity;

import javax.persistence.EntityManager;
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

    /***
     * Deletes the record in DB specified by the ID.
     * @param id The ID in the DB
     */
    public void delete(int id){
        EntityManager em = this.getEntityManager();

        try {
            TableEntity entity = em.find(TableEntity.class, id);
            if(entity==null || entity.getID()==null){
                return;
            }
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            em.close();
        }catch(Exception ex){
            em.getTransaction().rollback();
            em.close();
            throw ex;
        }
    }

    /***
     * Deletes the entity in the DB
     * @param t
     */
    public void delete(TableEntity t){
        delete(t.getID());
    }
    public List<TableEntity> find(String name){
        String query = "SELECT FROM "+getTableName()+" WHERE table_name CONTAINS "+name;
        EntityManager em = this.getEntityManager();

        List<TableEntity> results = em.createQuery(query,EntityClass).getResultList();
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

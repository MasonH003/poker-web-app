package com.example.pokerwebapp.model.dao;
import com.example.pokerwebapp.model.entity.Account;
import com.example.pokerwebapp.model.entity.Report;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class ReportDAO extends GenericDAO<Report> {

    public ReportDAO(){
        super(Report.class);
    }

    public Report existingReport(Account logged, Account reported)
    {
        EntityManager em = getEntityManager();
        String query = "Select r FROM "+getTableName()+" r WHERE r.account = :accountLogged AND r.reported = :accountReported";
        Report found = null;
        try
        {
            found = em.createQuery(query, Report.class)
                    .setParameter("accountLogged", logged)
                    .setParameter("accountReported", reported)
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

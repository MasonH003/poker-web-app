package com.example.pokerwebapp.controller.service;
import com.example.pokerwebapp.model.dao.ReportDAO;
import com.example.pokerwebapp.model.entity.Report;

public class ReportService {
    public static ReportDAO dao = new ReportDAO();

    public static void setDAO(ReportDAO dao){
        ReportService.dao = dao;
    }

    public static Report createReport(Report r)
    {
        Report found = dao.existingReport(r.getAccount(), r.getReported());
        if(found == null)
        {
            r = dao.create(r);
        }
        else{
            //reported the same user twice
            r = null;
        }
        return r;
    }




}

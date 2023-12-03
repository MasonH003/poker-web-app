package com.example.pokerwebapp.model.entity;

import javax.persistence.*;
@Entity
public class Report extends BaseEntity{
    @Id @Column(name="id_reported") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "reported_id")
    private Account reported;

    private String report_message;

    public Report()
    {

    }

    public Report(Account account, Account reported, String report_message)
    {
        this.account = account;
        this.reported = reported;
        this.report_message = report_message;
    }
    @Override
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getReported() {
        return reported;
    }

    public void setReported(Account reported) {
        this.reported = reported;
    }

    public String getReportMessage() {
        return report_message;
    }

    public void setReportMessage(String report_message) {
        this.report_message = report_message;
    }
}

/**
 create table report(
 id_report INT AUTO_INCREMENT PRIMARY KEY,
 account_id INT,
 reported_id INT,
 report_message VARCHAR(150),
 FOREIGN KEY (account_id) REFERENCES Account(id_account),
 FOREIGN KEY (reported_id) REFERENCES Account(id_account)
 );
 */
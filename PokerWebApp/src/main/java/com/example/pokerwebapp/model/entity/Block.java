package com.example.pokerwebapp.model.entity;
import javax.persistence.*;

@Entity
public class Block extends BaseEntity{
    @Id @Column(name="id_blocked") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "blocked_id")
    private Account blocked;

    public Block()
    {
    }

    public Block(Account account, Account blocked)
    {
        this.account = account;
        this.blocked = blocked;
    }

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

    public Account getBlocked() {
        return blocked;
    }

    public void setBlocked(Account blocked) {
        this.blocked = blocked;
    }
}

/**
 CREATE TABLE block (
 id_blocked INT AUTO_INCREMENT PRIMARY KEY,
 account_id INT,
 blocked_id INT,
 FOREIGN KEY (account_id) REFERENCES Account(id_account),
 FOREIGN KEY (blocked_id) REFERENCES Account(id_account)
 );
 */

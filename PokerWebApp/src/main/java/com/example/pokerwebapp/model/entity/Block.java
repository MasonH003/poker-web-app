package com.example.pokerwebapp.model.entity;
import javax.persistence.*;

public class Block extends BaseEntity{
    @Id @Column(name="id_account") @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Block(Account a, Account b)
    {
        this.account = a;
        this.blocked = b;
    }

    public Integer getID() {
        return ID;
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
 CREATE TABLE Friendship (
 id_block INT AUTO_INCREMENT PRIMARY KEY,
 account_id INT,
 block_id INT,
 FOREIGN KEY (account_id) REFERENCES Account(id_account),
 FOREIGN KEY (block_id) REFERENCES Account(id_account)
 );
 */

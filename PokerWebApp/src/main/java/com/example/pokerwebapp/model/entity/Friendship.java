package com.example.pokerwebapp.model.entity;
import javax.persistence.*;

@Entity
public class Friendship extends BaseEntity{

    @Id @Column(name="id_friendship") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Account friend;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;


    public Friendship() {
    }
    public Friendship(Account account, Account friend)
    {
        this.account = account;
        this.friend = friend;
        this.status = FriendshipStatus.PENDING;
    }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getFriend() {
        return friend;
    }

    public void setFriend(Account friend) {
        this.friend = friend;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}


/**
 CREATE TABLE friendship (
 id_friendship INT AUTO_INCREMENT PRIMARY KEY,
 status VARCHAR(255),
 account_id INT,
 friend_id INT,
 FOREIGN KEY (account_id) REFERENCES Account(id_account),
 FOREIGN KEY (friend_id) REFERENCES Account(id_account)
 );
 */
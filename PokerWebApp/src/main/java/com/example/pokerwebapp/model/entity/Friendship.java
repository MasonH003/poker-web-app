package com.example.pokerwebapp.model.entity;
import javax.persistence.*;

@Entity
public class Friendship extends BaseEntity{

    @Id @Column(name="id_friendship") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Account friend;

    public Friendship() {
        // Initialization or leave empty if not needed
    }
    public Friendship(Account user, Account friend)
    {
        this.user = user;
        this.friend = friend;
        this.status = FriendshipStatus.PENDING;
    }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
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

}


/**
 CREATE TABLE Friendship (
 id_friendship INT AUTO_INCREMENT PRIMARY KEY,
 status VARCHAR(255),
 user_id INT,
 friend_id INT,
 FOREIGN KEY (user_id) REFERENCES account(id_account),
 FOREIGN KEY (friend_id) REFERENCES account(id_account)
 );
 */
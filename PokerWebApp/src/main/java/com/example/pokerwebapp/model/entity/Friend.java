package com.example.pokerwebapp.model.entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;

    private Account account;
    @ManyToMany
    @JoinTable(
            name = "account_friend",
            joinColumns = @JoinColumn(name = "id_friend"),
            inverseJoinColumns = @JoinColumn(name = "id_account")

    )
    private List<Account> friends;

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

    public List<Account> getFriends() {
        return friends;
    }

    public void setFriends(List<Account> friends) {
        this.friends = friends;
    }
}

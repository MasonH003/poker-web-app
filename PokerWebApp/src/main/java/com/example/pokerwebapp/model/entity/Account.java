// SPDX-License-Identifier: MIT
package com.example.pokerwebapp.model.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account extends BaseEntity {
    @Id @Column(name="id_account") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;
    private int is_admin;
    @Column(unique=true) //Login must be unique for each record in DB
    private String username;
    private String password;
    private int money;

    // bidirectional relationship
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_friend",
            joinColumns = @JoinColumn(name = "id_account"),
            inverseJoinColumns = @JoinColumn(name = "id_friend")
    )

    private Set<Account> friends = new HashSet<>();

    @ManyToMany(mappedBy = "friends")
    private Set<Account> friendOf = new HashSet<>();

    // unidirectional relationship
    @ManyToMany
    @JoinTable(
            name = "user_block",
            joinColumns = @JoinColumn(name = "id_account"),
            inverseJoinColumns = @JoinColumn(name = "id_blocked")
    )
    private Set<Account> blockedAccounts = new HashSet<>();



    public transient static final int NORMAL_PERMISSION = 0;
    public transient static final int ADMIN_PERMISSION = 1;

    public Account(){
        this.is_admin = NORMAL_PERMISSION;
    }

    public Account(Integer ID, String username, String password, int is_admin) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
    }

    /*public Account(String username, String password ) {
        this.username = username;
        this.password = password;
        this.money = 500;
        this.is_admin = NORMAL_PERMISSION;
    }*/

    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /***
     * Returns the email which should be same as the Login
     *
     * @return
     */
    public String getEmail(){ return getUsername(); }

    /***
     * Sets the Email which should be same as Login
     */
    public void setEmail(String email){
        setUsername(email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermission() {
        return is_admin;
    }

    public void setPermission(int permission) {
        this.is_admin = permission;
    }

    public String getPermissionAsString(){
        if(is_admin ==NORMAL_PERMISSION) return "Normal";
        else if(is_admin ==ADMIN_PERMISSION) return "Admin";
        else return "Unknown";
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    public Set<Account> getFriends() {
        return friends;
    }

    public void setFriends(Set<Account> friends) {
        this.friends = friends;
    }

    public Set<Account> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(Set<Account> friendOf) {
        this.friendOf = friendOf;
    }

    public Set<Account> getBlockedAccounts() {
        return blockedAccounts;
    }

    public void setBlockedAccounts(Set<Account> blockedAccounts) {
        this.blockedAccounts = blockedAccounts;
    }

    //move this to the service layer eventually
    public void addFriend(Account toAdd)
    {
        this.friends.add(toAdd);
        toAdd.getFriendOf().add(this);
    }
    //move to service layer eventually
    public void deleteFriend(Account toRemove)
    {
        this.friends.remove(toRemove);
        toRemove.getFriendOf().remove(this);

    }

    //move to service layer
    public void blockAccount(Account toBlock)
    {
        this.blockedAccounts.add(toBlock);
    }

    //move to service layer
    public void unblockAccount(Account toUnblock)
    {
        this.blockedAccounts.remove(toUnblock);
    }
}

/**
 create table account(
 id_account int not null auto_increment,
 is_admin int not null default 0,
 username varchar(50) not null,
 password varchar(5000) not null,
 money int not null default 500,

 constraint account_pk primary key(id_account),
 constraint account_username_uk unique key(username)
 );
 */


/**
 CREATE TABLE user_friend (
 id_account INT,
 id_friend INT,
 PRIMARY KEY (id_account, id_friend),
 FOREIGN KEY (id_account) REFERENCES account(id_account),
 FOREIGN KEY (id_friend) REFERENCES account(id_account)
 );
 */

/**
 CREATE TABLE user_block (
 id_account INT,
 id_blocked INT,
 PRIMARY KEY (id_account, id_blocked),
 FOREIGN KEY (id_account) REFERENCES account(id_account),
 FOREIGN KEY (id_blocked) REFERENCES account(id_account)
 );
 */
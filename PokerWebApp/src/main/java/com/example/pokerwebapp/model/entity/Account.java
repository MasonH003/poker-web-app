// SPDX-License-Identifier: MIT
package com.example.pokerwebapp.model.entity;


import javax.persistence.*;

@Entity
public class Account extends BaseEntity {
    @Id @Column(name="id_user") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;
    private int isAdmin;
    @Column(unique=true) //Login must be unique for each record in DB
    private String username;
    private String password;
    private int money;

    public transient static final int NORMAL_PERMISSION = 0;
    public transient static final int ADMIN_PERMISSION = 1;

    public Account(){
        this.isAdmin = NORMAL_PERMISSION;
    }

    public Account(Integer ID, String username, String password, int isAdmin) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

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
        return isAdmin;
    }

    public void setPermission(int permission) {
        this.isAdmin = permission;
    }

    public String getPermissionAsString(){
        if(isAdmin==NORMAL_PERMISSION) return "Normal";
        else if(isAdmin==ADMIN_PERMISSION) return "Admin";
        else return "Unknown";
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }
}

/**
 create table account(
 id_account int not null auto_increment,
 is_admin int not null default 0,
 username varchar(50) not null,
 password varchar(50) not null,
 money int not null default 500,

 constraint account_pk primary key(id_account),
 constraint account_username_uk unique key(username)
 );
 */
//package com.example.pokerwebapp.model.entity;
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//public class Friendship extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer ID = null;
//
//    @ManyToOne
//    @JoinColumn(name = "sender_id")
//    private Account sender;
//
//    @ManyToOne
//    @JoinColumn(name = "receiver_id")
//    private Account receiver;
//
//    //TODO: Add friendship status attribute like PENDING / ACCEPTED / DECLINED
//   // private boolean isAccepted;
//    public Friendship(Account sender, Account receiver)
//    {
//        this.sender = sender;
//        this.receiver = receiver;
//    }
//
//
//    public Integer getID() {
//        return ID;
//    }
//
//    public Account getSender() {
//        return sender;
//    }
//
//    public void setSender(Account sender) {
//        this.sender = sender;
//    }
//
//    public Account getReceiver() {
//        return receiver;
//    }
//
//    public void setReceiver(Account receiver) {
//        this.receiver = receiver;
//    }
//}

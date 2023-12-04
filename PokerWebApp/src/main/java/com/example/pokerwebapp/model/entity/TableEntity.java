package com.example.pokerwebapp.model.entity;

import javax.persistence.*;

@Entity
public class TableEntity extends BaseEntity{

    @Id @Column(name="id_table") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;

    @Column(name="table_name")
    private String name;

    @Column(name="player_count")
    private Integer players = 0;

    @Override
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlayers() {
        return players;
    }

    public void setPlayers(Integer players) {
        this.players = players;
    }
}

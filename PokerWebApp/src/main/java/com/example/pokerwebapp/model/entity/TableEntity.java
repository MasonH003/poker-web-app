package com.example.pokerwebapp.model.entity;

import javax.persistence.*;

@Entity
public class TableEntity extends BaseEntity{

    @Id @Column(name="id_table") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID = null;

    @Column(name="table_name")
    private String name;

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
}

package com.sg.inventory.entities;

import javax.persistence.*;

@Entity
public class Store {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String location;

    @Column
    private String manager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    
    
    
}

package com.battlefield.model;

import java.util.HashSet;
import java.util.Set;

public class Player {

    private int id;
    private String name;
    private Set<Ship> ships;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.ships = new HashSet<>();
    }

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

    public Set<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", ships=" + ships + "]";
    }
    
}

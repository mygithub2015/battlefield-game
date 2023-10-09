package com.battlefield.model;

import java.util.List;

public class Ship {

    private int id;
    private Player player;
    private ShipIdentifier identifier;
    private ShipStatus status;
    private List<Pair> cells;

    
    public Ship(int id, Player player, ShipIdentifier identifier) {
        this.id = id;
        this.player = player;
        this.identifier = identifier;
        this.status = ShipStatus.AVAILABLE;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public ShipIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(ShipIdentifier identifier) {
        this.identifier = identifier;
    }

    public ShipStatus getStatus() {
        return status;
    }

    public void setStatus(ShipStatus status) {
        this.status = status;
    }

    public List<Pair> getCells() {
        return this.cells;
    }

    public void setCells(List<Pair> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "Ship [id=" + id + ", ship identity=" + identifier + ", status=" + status
                + ", cells=" + cells + "]";
    }
    
}

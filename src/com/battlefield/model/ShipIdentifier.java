package com.battlefield.model;

public class ShipIdentifier {
    
    private int x;
    private int y;
    private int size;
    private String name;
    
    public ShipIdentifier(int x, int y, int size, String name) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Override
    public String toString() {
        return "ShipIdentifier [x=" + x + ", y=" + y + ", size=" + size + "]";
    }
    
}

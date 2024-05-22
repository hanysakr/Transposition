package com.transposition.transposition.model;

public class Note {
    public Note(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int x = 0;
    private int y = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" + x + "," + y + "}";
    }
}

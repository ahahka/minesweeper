package com.radiantcreek;

public class Location {
    private int row;
    private int col;

    
    public void setRow (int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return col;
    }

    public String toString() {
        return ("[" + row + "," + col + "]");
    }

    public boolean equals (Location other) {
        return this.col == other.col && this.row == other.row;
    }

}

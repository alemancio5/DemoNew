package main.java.model;



public class Player {
    private String username;
    private int row;
    private int column;
    private String skinname;

    public Player(String username, int row, int column, String skinname) {
        this.username = username;
        this.row = row;
        this.column = column;
        this.skinname = skinname;
    }

    public void moveUp() {
        this.row--;
    }

    public void moveDown() {
        this.row++;
    }

    public void moveLeft() {
        this.column--;
    }

    public void moveRight() {
        this.column++;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
    
    public String getSkinname() {
        return this.skinname;
    }
}

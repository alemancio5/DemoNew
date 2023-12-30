package main.java.model;

import javafx.scene.input.KeyCode;

public class Player {
    private String username;
    private int row;
    private int column;
    private String skinname;
    private KeyCode direction;


    
    public Player(String username, int row, int column, String skinname) {
        this.username = username;
        this.row = row;
        this.column = column;
        this.skinname = skinname;
        this.direction = KeyCode.S;
    }

    public void move() {
        switch (this.direction) {
            case KeyCode.W:
                this.row--;
                break;
            case KeyCode.A:
                this.column--;
                break;
            case KeyCode.S:
                this.row++;
                break;
            case KeyCode.D:
                this.column++;
                break;
            default:
                throw new IllegalArgumentException("Invalid key");
        }
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getSkinname() {
        return this.skinname;
    }

    public KeyCode getDirection() {
        return direction;
    }

    public void setDirection(KeyCode direction) {
        this.direction = direction;
    }   
}

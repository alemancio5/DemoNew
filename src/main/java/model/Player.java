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

    public void move(char direction) {
        switch(direction) {
            case 'W':
                this.row--;
                break;
            case 'A':
                this.column--;
                break;
            case 'S':
                this.row++;
                break;
            case 'D':
                this.column++;
                break;
        }
    }
    
    public String getUsername() {
        return this.username;
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
    
    public void setRow(int row) {
        this.row = row;
    }
    
    public void setColumn(int column) {
        this.column = column;
    }
}

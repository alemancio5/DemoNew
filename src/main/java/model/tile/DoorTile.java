package main.java.model.tile;

import main.java.ctrl.GameCtrl;



public class DoorTile extends Tile {
    private String destination;
    private int row;
    private int column;

    

    public DoorTile(String boardname, int i, int j) {
        switch (boardname) {
            case "Nowhere":
                if (i == 17 && j == 26) {
                    this.destination = "WoodHouse1";
                    this.row = 8;
                    this.column = 3;
                }
                break;
            case "WoodHouse1":
                if (i == 9 && j == 3) {
                    this.destination = "Nowhere";
                    this.row = 18;
                    this.column = 26;
                }
                break;
            default:
                throw new IllegalArgumentException("Illegal boardname passed to door tile");
        }

    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    public boolean isOnActionable() {
        return false;
    }

    public boolean isFrontActionable() {
        return true;
    }

    @Override
    public void action() {
        GameCtrl.changeBoard(this.destination, this.row, this.column);
    }
}
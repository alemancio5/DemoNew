package main.java.model.tile;

import main.java.ctrl.GameCtrl;;

public class PortalTile extends Tile {
    private String destination;
    private int row;
    private int column;

    
    public PortalTile(String boardname, int i, int j) {
        switch (boardname) {
            case "Nowhere":
                if (i == 25 && j == 42) {
                    this.destination = "GreenForest";
                    this.row = 72;
                    this.column = 21;
                }
                break;
            case "GreenForest":
                if (i == 72 && j == 21) {
                    this.destination = "Nowhere";
                    this.row = 25;
                    this.column = 42;
                }
                break;
            default:
                throw new IllegalArgumentException("Illegal boardname passed to portal tile");
        }

    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    public boolean isOnActionable() {
        return true;
    }

    public boolean isFrontActionable() {
        return false;
    }

    @Override
    public void action() {
        GameCtrl.changeBoard(this.destination, this.row, this.column);
    }
}
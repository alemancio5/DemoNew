package main.java.model.tile;

import main.java.ctrl.GameCtrl;
import main.java.model.Board;
import main.java.view.View;

public class PortalTile extends Tile {
    private String destination;
    private int row;
    private int column;

    
    public PortalTile(String boardname, int i, int j) {
        switch (boardname) {
            case "Nowhere":
                if (i == 17 && j == 26) {
                    this.destination = "WoodHouse1";
                    this.row = 16;
                    this.column = 11;
                }
                break;
            case "WoodHouse1":
                if (i == 17 && j == 11) {
                    this.destination = "Nowhere";
                    this.row = 18;
                    this.column = 26;
                }
                break;
            default:
                throw new IllegalArgumentException("Illegal boardname passed to portal tile");
        }

    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public void action() {
        GameCtrl.teleport(this.destination, this.row, this.column);
    }
}
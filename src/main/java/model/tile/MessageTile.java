package main.java.model.tile;

import main.java.ctrl.GameCtrl;;

public class MessageTile extends Tile {
    private String message;

    
    public MessageTile(String boardname, int i, int j) {
        switch (boardname) {
            case "Nowhere":
                if (i == 23 && j == 42) {
                    this.message = "Welcome to Nowhere";
                }
                if (i == 19 && j == 25) {
                    this.message = "Not your house!";
                }
                break;
            case "GreenForest":
                if (i == 70 && j == 21) {
                    this.message = "This is the Green Forest";
                }
                break;
            default:
                throw new IllegalArgumentException("Illegal boardname passed to message tile");
        }

    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    public boolean isActionable() {
        return true;
    }

    @Override
    public void action() {
        GameCtrl.showMessage(this.message);
    }
}
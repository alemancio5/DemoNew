package main.java.model.tile;

import java.util.ArrayList;

import main.java.ctrl.GameCtrl;;



public class SignTile extends Tile {
    private ArrayList<String> dialogList = new ArrayList<>();
    

    
    public SignTile(String boardname, int i, int j) {
        switch (boardname) {
            case "Nowhere":
                if (i == 23 && j == 42) {
                    this.dialogList.add(0, "Welcome to Nowhere");
                }
                if (i == 18 && j == 25) {
                    this.dialogList.add(0, "Not your house!");
                }
                break;
            case "GreenForest":
                if (i == 70 && j == 21) {
                    this.dialogList.add(0, "This is the Green Forest");
                    this.dialogList.add(1, "You can find a lot of trees here");
                }
                break;
            default:
                throw new IllegalArgumentException("Illegal boardname passed to sign tile");
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
        GameCtrl.showDialog(this.dialogList);
    }
}
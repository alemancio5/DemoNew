package main.java.model.tile;

import java.util.ArrayList;

import main.java.ctrl.GameCtrl;;

public class MessageTile extends Tile {
    private ArrayList<String> messageList = new ArrayList<>();

    
    public MessageTile(String boardname, int i, int j) {
        switch (boardname) {
            case "Nowhere":
                if (i == 23 && j == 42) {
                    this.messageList.add(0, "Welcome to Nowhere");
                }
                if (i == 18 && j == 25) {
                    this.messageList.add(0, "Not your house!");
                }
                break;
            case "GreenForest":
                if (i == 70 && j == 21) {
                    this.messageList.add(0, "This is the Green Forest");
                    this.messageList.add(1, "You can find a lot of trees here");
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

    public boolean isOnActionable() {
        return false;
    }

    public boolean isFrontActionable() {
        return true;
    }

    @Override
    public void action() {
        GameCtrl.showMessage(this.messageList);
    }
}
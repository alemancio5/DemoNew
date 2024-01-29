package main.java.model.tile;



public class EmptyTile extends Tile {


    
    public EmptyTile() {
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    public boolean isOnActionable() {
        return false;
    }

    public boolean isFrontActionable() {
        return false;
    }

    @Override
    public void action() {
    }
}

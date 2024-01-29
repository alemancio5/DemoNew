package main.java.model.tile;



public class WallTile extends Tile {
    

    
    public WallTile() {
    }

    @Override
    public boolean isWalkable() {
        return false;
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

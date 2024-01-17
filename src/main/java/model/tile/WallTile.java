package main.java.model.tile;



public class WallTile extends Tile {
    

    
    public WallTile() {
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    public boolean isActionable() {
        return false;
    }

    @Override
    public void action() {
    }
}

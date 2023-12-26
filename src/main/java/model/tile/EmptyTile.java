package main.java.model.tile;



public class EmptyTile extends Tile {


    
    public EmptyTile() {
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}

package main.java.model.tile;



public abstract class Tile {


    
    public Tile() {
    }

    public abstract boolean isWalkable();

    public abstract boolean isActionable();
    
    public abstract void action();
}


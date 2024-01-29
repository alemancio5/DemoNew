package main.java.model.tile;



public abstract class Tile {

    

    public Tile() {
    }

    public abstract boolean isWalkable();

    public abstract boolean isOnActionable();

    public abstract boolean isFrontActionable();
    
    public abstract void action();
}


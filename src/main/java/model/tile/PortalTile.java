package main.java.model.tile;



public class PortalTile extends Tile {


    
    public PortalTile() {
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void action() {
        System.out.println("Ciao");
    }
}
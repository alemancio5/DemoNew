package main.java.model;



public class Tile {
    private final TileType type;

    public Tile(String symbol) {
        this.type = fromChar(symbol);
    }

    private TileType fromChar(String symbol) {
        return switch (symbol) {
            case "e" -> TileType.EMPTY;
            case "w" -> TileType.WALL;
            default -> null;
        };
    }

    public boolean isWalkable() {
        return this.type != TileType.WALL;
    }

    public TileType getType() {
        return type;
    }
}


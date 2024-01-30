package main.java.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.java.model.tile.DoorTile;
import main.java.model.tile.EmptyTile;
import main.java.model.tile.SignTile;
import main.java.model.tile.PortalTile;
import main.java.model.tile.Tile;
import main.java.model.tile.WallTile;



public class Board {
    private final String boardname;
    private final int rows;
    private final int columns;
    private final Tile[][] tiles;



    public Board(String boardname) {
        // setting the board name
        this.boardname = boardname;

        // reading the board file
        File file = new File("src/main/resources/board_files/" + this.boardname + ".txt");
        try {
            Scanner scanner = new Scanner(file);
            this.rows = scanner.nextInt();
            this.columns = scanner.nextInt();
            this.tiles = new Tile[this.rows][this.columns];
            this.initTiles(scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Board file does not exist");
        }
    }

    private void initTiles(Scanner scanner) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                switch (scanner.next()) {
                    case "e":
                        this.tiles[i][j] = new EmptyTile();
                        break;
                    case "w":
                        this.tiles[i][j] = new WallTile();
                        break;
                    case "p":
                        this.tiles[i][j] = new PortalTile(this.boardname, i, j);
                        break;
                    case "d":
                        this.tiles[i][j] = new DoorTile(this.boardname, i, j);
                        break;
                    case "s":
                        this.tiles[i][j] = new SignTile(this.boardname, i, j);
                        break;
                    default:
                        throw new IllegalArgumentException("Board file has illegal arguments");
                }
            }
        }
    }

    public boolean isWalkable(int row, int column) {
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns || !this.tiles[row][column].isWalkable()) {
            return false;
        } else {
            return true;
        }
    }

    public String getBoardname() {
        return boardname;
    }

    public int getRows() {
        return rows;
    }
    
    public int getColumns() {
        return columns;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}



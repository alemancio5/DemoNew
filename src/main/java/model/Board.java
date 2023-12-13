package main.java.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {
    private final String boardname;
    private final int rows;
    private final int columns;
    private final Tile[][] tiles;

    public Board(String boardname) throws FileNotFoundException {
        this.boardname = boardname;

        File file = new File("src/main/resources/board_files/" + boardname + ".txt");
        Scanner scanner = new Scanner(file);

        this.rows = scanner.nextInt();
        this.columns = scanner.nextInt();

        this.tiles = new Tile[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.tiles[i][j] = new Tile(scanner.next());
            }
        }

        scanner.close();
    }

    public boolean isWalkable(int row, int column) {
        return this.tiles[row][column].isWalkable();
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



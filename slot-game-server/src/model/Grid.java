package model;

import java.util.Random;

public class Grid {
    public static final int rows = 8;
    public static final int columns = 8;
    private Cell[][] cells;
    private Random random;

    public Grid() {
        this.cells = new Cell[rows][columns];
        this.random = new Random();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                cells[row][col] = new Cell(row, col, Symbol.EMPTY);
            }
        }
        fillGrid();
    }

    public void fillGrid() {
        Symbol[] paySymbols = {Symbol.H1, Symbol.H2, Symbol.H3, Symbol.H4,
                Symbol.L5, Symbol.L6, Symbol.L7, Symbol.L8, Symbol.WR};

        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                if (cells[row][col].getSymbol() == Symbol.EMPTY) {
                    Symbol randomSymbol = paySymbols[random.nextInt(paySymbols.length)];
                    cells[row][col].setSymbol(randomSymbol);
                }
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, Symbol symbol) {
        cells[row][col].setSymbol(symbol);
    }


    /*public void printGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                System.out.print(cells[row][col].getSymbol() + " ");
            }
            System.out.println();
        }
    }*/
}

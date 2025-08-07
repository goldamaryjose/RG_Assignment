package model;

public class Cell {
    private Symbol symbol;
    private int row;
    private int column;

    public Cell(int row, int column, Symbol symbol) {
        this.row = row;
        this.column = column;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
}

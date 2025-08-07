package service;

import model.*;

import java.util.List;

public class AvalancheService {
    private Grid grid;

    public AvalancheService(Grid grid) {
        this.grid = grid;
    }

    public void processAvalanche(List<List<Cell>> winningClusters) {
        for (List<Cell> cluster : winningClusters) {
            for (Cell cell : cluster) {
                grid.setCell(cell.getRow(), cell.getColumn(), Symbol.EMPTY);
                destroyAdjacentBlockers(cell);
            }
        }

        for (int col = 0; col < Grid.columns; col++) {
            for (int row = Grid.rows - 1; row >= 0; row--) {
                if (grid.getCell(row, col).getSymbol() == Symbol.EMPTY) {
                    for (int above = row - 1; above >= 0; above--) {
                        if (grid.getCell(above, col).getSymbol() != Symbol.EMPTY) {
                            grid.getCell(row, col).setSymbol(grid.getCell(above, col).getSymbol());
                            grid.getCell(above, col).setSymbol(Symbol.EMPTY);
                            break;
                        }
                    }
                }
            }
        }
        grid.fillGrid();
    }

    private void destroyAdjacentBlockers(Cell cell) {
        int row = cell.getRow();
        int col = cell.getColumn();

        if (row > 0 && grid.getCell(row-1, col).getSymbol() == Symbol.BLOCKER) {
            grid.setCell(row-1, col, Symbol.EMPTY);
        }
        if (row < Grid.rows-1 && grid.getCell(row+1, col).getSymbol() == Symbol.BLOCKER) {
            grid.setCell(row+1, col, Symbol.EMPTY);
        }
        if (col > 0 && grid.getCell(row, col-1).getSymbol() == Symbol.BLOCKER) {
            grid.setCell(row, col-1, Symbol.EMPTY);
        }
        if (col < Grid.columns-1 && grid.getCell(row, col+1).getSymbol() == Symbol.BLOCKER) {
            grid.setCell(row, col+1, Symbol.EMPTY);
        }
    }
}

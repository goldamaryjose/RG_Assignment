package service;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class PayoutService {
    private Grid grid;
    private List<Cell> visitedCells;

    public PayoutService(Grid grid) {
        this.grid = grid;
        this.visitedCells = new ArrayList<>();
    }

    public List<List<Cell>> findWinningClusters() {
        List<List<Cell>> winningClusters = new ArrayList<>();
        visitedCells.clear();

        for (int row = 0; row < Grid.rows; row++) {
            for (int col = 0; col < Grid.columns; col++) {
                Cell cell = grid.getCell(row, col);
                Symbol symbol = cell.getSymbol();

                if (visitedCells.contains(cell)) continue;
                if (symbol == Symbol.WR || symbol == Symbol.BLOCKER || symbol == Symbol.EMPTY) continue;

                List<Cell> cluster = new ArrayList<>();
                findCluster(cell, symbol, cluster);

                if (cluster.size() >= 5) {
                    winningClusters.add(cluster);
                }
            }
        }

        return winningClusters;
    }

    private void findCluster(Cell cell, Symbol targetSymbol, List<Cell> cluster) {
        if (visitedCells.contains(cell)) return;
        Symbol cellSymbol = cell.getSymbol();

        if (cellSymbol == Symbol.BLOCKER) return;

        if (cellSymbol != Symbol.WR && cellSymbol != targetSymbol) return;

        visitedCells.add(cell);
        cluster.add(cell);

        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        for (int[] dir : directions) {
            int newRow = cell.getRow() + dir[0];
            int newCol = cell.getColumn() + dir[1];

            if (newRow >= 0 && newRow < Grid.rows &&
                    newCol >= 0 && newCol < Grid.columns) {
                findCluster(grid.getCell(newRow, newCol), targetSymbol, cluster);
            }
        }
    }
}

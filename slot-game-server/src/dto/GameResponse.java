package dto;

import model.Cell;
import java.util.List;

public class GameResponse {
    private List<List<Cell>> currentGrid;
    private int winAmount;
    private boolean avalancheOccurred;
    private List<List<Cell>> destroyedSymbols;

    public List<List<Cell>> getCurrentGrid() {
        return currentGrid;
    }

    public void setCurrentGrid(List<List<Cell>> currentGrid) {
        this.currentGrid = currentGrid;
    }

    public int getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(int winAmount) {
        this.winAmount = winAmount;
    }

    public boolean isAvalancheOccurred() {
        return avalancheOccurred;
    }

    public void setAvalancheOccurred(boolean avalancheOccurred) {
        this.avalancheOccurred = avalancheOccurred;
    }

    public List<List<Cell>> getDestroyedSymbols() {
        return destroyedSymbols;
    }

    public void setDestroyedSymbols(List<List<Cell>> destroyedSymbols) {
        this.destroyedSymbols = destroyedSymbols;
    }
}
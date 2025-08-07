package service;

import model.*;
import dto.*;
import java.util.*;

public class GameService {
    private final Grid grid;
    private final PayoutService payoutService;
    private final AvalancheService avalancheService;
    private final GameState gameState;
    private final Random random;

    public GameService() {
        this.grid = new Grid();
        this.payoutService = new PayoutService(grid);
        this.avalancheService = new AvalancheService(grid);
        this.gameState = new GameState();
        this.random = new Random();
    }

    public GameResponse playRound(int betAmount) {
        gameState.recordBet(betAmount);
        int roundWin = 0;
        boolean avalancheOccurred = false;
        List<List<Cell>> destroyedClusters = new ArrayList<>();

        do {
            List<List<Cell>> winningClusters = payoutService.findWinningClusters();
            if (winningClusters.isEmpty()) break;

            avalancheOccurred = true;
            destroyedClusters.addAll(winningClusters);

            for (List<Cell> cluster : winningClusters) {
                Symbol symbol = getClusterSymbol(cluster);
                int payout = Payout.getPayout(symbol, cluster.size());
                roundWin += (payout * betAmount) / 10;
            }
            System.out.println("Cluster found: " + winningClusters.size());
            for (List<Cell> cluster : winningClusters) {
                System.out.println("Symbol: " + getClusterSymbol(cluster) +
                        ", Size: " + cluster.size());
            }

            avalancheService.processAvalanche(winningClusters);
        } while (true);

        if (roundWin > 0) {
            gameState.recordWin(roundWin);
        }

        GameResponse response = new GameResponse();
        response.setCurrentGrid(copyGridState());
        response.setWinAmount(roundWin);
        response.setAvalancheOccurred(avalancheOccurred);
        response.setDestroyedSymbols(destroyedClusters);

        return response;
    }

    public boolean gamble() {
        return random.nextBoolean();
    }

    public GameState getGameState() {

        return gameState;
    }

    private Symbol getClusterSymbol(List<Cell> cluster) {
        return cluster.stream()
                .filter(c -> c.getSymbol() != Symbol.WR)
                .findFirst()
                .map(Cell::getSymbol)
                .orElse(Symbol.WR);
    }

    private List<List<Cell>> copyGridState() {
        List<List<Cell>> gridState = new ArrayList<>();
        for (int row = 0; row < Grid.rows; row++) {
            List<Cell> rowCells = new ArrayList<>();
            for (int col = 0; col < Grid.columns; col++) {
                Cell original = grid.getCell(row, col);
                rowCells.add(new Cell(row, col, original.getSymbol()));
            }
            gridState.add(rowCells);
        }
        return gridState;
    }
}

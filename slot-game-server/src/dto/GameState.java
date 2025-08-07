package dto;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private int totalBet;
    private int totalWin;
    private List<Integer> spinResults;

    public GameState() {
        this.totalBet = 0;
        this.totalWin = 0;
        this.spinResults = new ArrayList<>();
    }

    public void recordBet(int betAmount) {
        totalBet += betAmount;
    }

    public void recordWin(int winAmount) {
        totalWin += winAmount;
        spinResults.add(winAmount);
    }

    public double calculateRTP() {
        return (totalBet == 0) ? 0 : (double) totalWin / totalBet * 100;
    }

    public int getTotalBet() {
        return totalBet;
    }
    public int getTotalWin() {
        return totalWin;
    }
    public List<Integer> getSpinResults() {
        return spinResults;
    }
}

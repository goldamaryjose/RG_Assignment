import service.GameService;
import dto.GameState;
import java.text.DecimalFormat;

public class Main {
    private static final DecimalFormat RTP_FORMAT = new DecimalFormat("0.00%");
    private static final int simulations = 1000;
    private static final int betAmount = 10;

    public static void main(String[] args) {
        GameService game = new GameService();
        int totalGambles = 0;
        int successfulGambles = 0;

        for (int i = 1; i <= simulations; i++) {
            var response = game.playRound(betAmount);
            int roundWin = response.getWinAmount();

            if (roundWin > 0 && game.gamble()) {
                totalGambles++;
                if (Math.random() < 0.5) {
                    successfulGambles++;
                    roundWin *= 2;
                } else {
                    roundWin = 0;
                }
            }

            if (i % 100 == 0) {
                printIntermediateStats(game.getGameState(), i);
            }
        }

        printFinalStats(game.getGameState(), totalGambles, successfulGambles);
    }

    private static void printIntermediateStats(GameState state, int spinsCompleted) {
        System.out.printf("\nAfter %,d spins:%n", spinsCompleted);
        System.out.printf("Current RTP: %s%n",
                RTP_FORMAT.format(state.calculateRTP() / 100));
    }

    private static void printFinalStats(GameState state, int totalGambles, int successfulGambles) {
        System.out.println("\n=== FINAL RESULTS ===");
        System.out.printf("Total spins: %,d%n", state.getSpinResults().size());
        System.out.printf("Total bet: %,d%n", state.getTotalBet());
        System.out.printf("Total win: %,d%n", state.getTotalWin());
        System.out.printf("Final RTP: %s%n",
                RTP_FORMAT.format(state.calculateRTP() / 100));

        if (totalGambles > 0) {
            System.out.printf("\nGamble stats:%n");
            System.out.printf("Attempted: %,d (%.1f%% of winning spins)%n",
                    totalGambles,
                    (double) totalGambles / state.getSpinResults().stream().filter(w -> w > 0).count() * 100);
            System.out.printf("Success rate: %.1f%%%n",
                    (double) successfulGambles / totalGambles * 100);
        }
    }
}
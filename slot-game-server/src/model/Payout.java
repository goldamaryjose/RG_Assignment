package model;

import java.util.HashMap;
import java.util.Map;

public class Payout {
    private static final Map<Symbol, int[]> payoutTable = new HashMap<>();

    static {
        payoutTable.put(Symbol.H1, new int[]{5, 6, 7, 8, 10});
        payoutTable.put(Symbol.H2, new int[]{4, 5, 6, 7, 9});
        payoutTable.put(Symbol.H3, new int[]{4, 5, 6, 7, 9});
        payoutTable.put(Symbol.H4, new int[]{3, 4, 5, 6, 7});
        payoutTable.put(Symbol.L5, new int[]{1, 2, 3, 4, 5});
        payoutTable.put(Symbol.L6, new int[]{1, 2, 3, 4, 5});
        payoutTable.put(Symbol.L7, new int[]{1, 2, 3, 4, 5});
        payoutTable.put(Symbol.L8, new int[]{1, 2, 3, 4, 5});
    }

    public static int getPayout(Symbol symbol, int clusterSize) {
        if (!payoutTable.containsKey(symbol)) return 0;

        int[] payouts = payoutTable.get(symbol);
        if (clusterSize >= 21) return payouts[4];
        if (clusterSize >= 17) return payouts[3];
        if (clusterSize >= 13) return payouts[2];
        if (clusterSize >= 9) return payouts[1];
        if (clusterSize >= 5) return payouts[0];
        return 0;
    }
}

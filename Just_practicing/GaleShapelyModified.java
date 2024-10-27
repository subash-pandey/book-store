public class GaleShapelyModified {

    public static void main(String[] args) {
        int n = 3; // Number of men and women
        int[][] data = {
                {0, 2, 3, 1, 0, 0, -1},
                {0, 1, 3, 2, 0, 0, -1},
                {0, 3, 1, 2, -1, 0, 0},
                {1, 3, 1, 2, 0, 0, 0},
                {1, 1, 2, 3, 0, 0, 0},
                {1, 2, 3, 1, 0, 0, 0}
        };

        int[] match = new int[2 * n]; // Stores the matches (initialized to -1 for free)
        for (int i = 0; i < 2 * n; i++) {
            match[i] = -1;
        }

        boolean[] freeMan = new boolean[n]; // Tracks if a man is free
        for (int i = 0; i < n; i++) {
            freeMan[i] = true;
        }

        while (isFreeManAvailable(freeMan, data)) {
            int m = getFreeMan(freeMan);
            int w = getNextPreferredWoman(m, data, match);

            if (w != -1) { // If a valid woman is found
                if (match[w + n] == -1) { // If woman is free
                    engage(m, w, match);
                    freeMan[m] = false;
                } else {
                    int mPrime = match[w + n];
                    if (womanPrefersM(w, m, mPrime, data)) {
                        engage(m, w, match);
                        freeMan[m] = false;
                        freeMan[mPrime] = true;
                    }
                }
            }
        }

        // Print the matches
        System.out.println("Stable Matching:");
        for (int i = 0; i < n; i++) {
            System.out.println("M" + (i + 1) + " - W" + (match[i] + 1));
        }
    }

    // Helper functions

    static boolean isFreeManAvailable(boolean[] freeMan, int[][] data) {
        for (int i = 0; i < freeMan.length; i++) {
            if (freeMan[i] && hasValidProposalLeft(i, data, freeMan)) {
                return true;
            }
        }
        return false;
    }

    static boolean hasValidProposalLeft(int m, int[][] data, boolean[] freeMan) {
        int n = freeMan.length;
        for (int j = 1; j <= n; j++) {
            int w = data[m][j] - 1;
            if (data[m][w + n + 1] == 0) { // Check if the pair is allowed
                return true;
            }
        }
        return false;
    }

    static int getFreeMan(boolean[] freeMan) {
        for (int i = 0; i < freeMan.length; i++) {
            if (freeMan[i]) {
                return i;
            }
        }
        return -1;
    }

    static int getNextPreferredWoman(int m, int[][] data, int[] match) {
        int n = data.length / 2;
        for (int i = 1; i <= n; i++) {
            int w = data[m][i] - 1;
            if (data[m][w + n + 1] == 0 && match[m] != w) { // Check if allowed and not already proposed
                return w;
            }
        }
        return -1; // No valid woman left
    }

    static boolean womanPrefersM(int w, int m, int mPrime, int[][] data) {
        int n = data.length / 2;
        for (int i = 1; i <= n; i++) {
            if (data[w + n][i] - 1 == m) {
                return true;
            } else if (data[w + n][i] - 1 == mPrime) {
                return false;
            }
        }
        return false; // Should not reach here in a valid scenario
    }

    static void engage(int m, int w, int[] match) {
        match[m] = w;
        match[w + match.length / 2] = m;
    }
}
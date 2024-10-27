import java.util.Scanner;

public class SomeCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numWords = scanner.nextInt();
        int textLength = scanner.nextInt();
        scanner.nextLine();
        String[] dictionary = scanner.nextLine().split(" ");
        String text = scanner.nextLine();

        boolean[] dp = new boolean[textLength + 1];
        dp[0] = true;

        int[] result = new int[textLength + 1]; // Store indices instead of strings
        result[0] = -1; // Initialize to -1 to indicate no previous word

        for (int i = 0; i <= textLength; i++) {
            if (!dp[i]) continue;

            for (int j = 0; j < numWords; j++) {
                int wordLength = dictionary[j].length();
                if (i + wordLength <= textLength && text.substring(i, i + wordLength).equals(dictionary[j])) {
                    dp[i + wordLength] = true;
                    if (result[i] != -1 || i == 0) { // Check if there's a previous word or it's the first word
                        result[i + wordLength] = i;
                    }
                }
            }
        }

        // Reconstruct the solution from indices
        if (result[textLength] == -1) {
            System.out.println(""); // No solution found
        } else {
            StringBuilder solution = new StringBuilder();
            int current = textLength;
            while (current != 0) {
                int prev = result[current];
                solution.insert(0, text.substring(prev, current) + (prev != 0 ? " " : ""));
                current = prev;
            }
            System.out.println(solution.toString());
        }
    }
}
public class Factorial {

    private static final int MAX_FACTORIAL = 20; // Maximum factorial calculable with long
    private static long[] factorials = new long[MAX_FACTORIAL + 1];

    public static long factorial(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Value of x must be positive");
        }
        if (x > MAX_FACTORIAL) {
            throw new IllegalArgumentException("Result will overflow");
        }

        // Check if factorial is already calculated
        if (factorials[x] != 0) {
            return factorials[x];
        }

        // Calculate factorial
        long result = 1;
        for (int i = 2; i <= x; i++) {
            result *= i;
        }

        // Store calculated factorial for reuse
        factorials[x] = result;

        return result;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Factorial of 5: " + factorial(5));
            System.out.println("Factorial of 10: " + factorial(10));
            System.out.println("Factorial of 20: " + factorial(20));
            // This will throw an exception
            System.out.println("Factorial of -1: " + factorial(-1));
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
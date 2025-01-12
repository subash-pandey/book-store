import java.util.ArrayList;
import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in); // Single Scanner object
        while (flag) {
            System.out.println("\nPlease enter Y to continue:");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                System.out.println("Please enter a number to check if it is a prime number:");
                int number = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (number <= 1) {
                    System.out.println(number + " is not a prime number.");
                } else if (isPrime(number)) {
                    System.out.println(number + " is a prime number.");
                } else {
                    System.out.println(number + " is not a prime number.");
                    ArrayList<Integer> factors = factors(number);
                    System.out.println("The factors of " + number + " are: " + factors);
                }
            } else {
                flag = false;
                System.out.println("Thank you for using this program!");
            }
        }
        scanner.close(); // Close Scanner
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false; // Handle edge cases
        int end = (int) Math.sqrt(number);
        for (int i = 2; i <= end; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Integer> factors(int number) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                factors.add(i);
                if (i != number / i) { // Avoid duplicates
                    factors.add(number / i);
                }
            }
        }
        factors.sort(Integer::compareTo); // Sort factors for neat output
        return factors;
    }
}
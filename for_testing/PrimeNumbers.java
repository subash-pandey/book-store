import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers {

    public static List<Integer> findFirstNPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n <= 0) {
            return primes;
        }

        primes.add(2); // 2 is the first prime number

        if (n == 1) {
            return primes;
        }

        int count = 1;
        int num = 3;
        while (count < n) {
            if (isPrime(num)) {
                primes.add(num);
                count++;
            }
            num += 2; // Optimization: Check only odd numbers
        }
        return primes;
    }

    // Efficiently checks if a number is prime using the Sieve of Eratosthenes concept
    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }

        // Iterate from 5 to the square root of 'num' with a step of 6
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 20;
        List<Integer> primes = findFirstNPrimes(n);
        System.out.println("The first " + n + " prime numbers are: " + primes);
    }
}

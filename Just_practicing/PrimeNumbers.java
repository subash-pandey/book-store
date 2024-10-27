
public class PrimeNumbers {

    public static void main(String[] args) {

        int count = 0; // Counter for prime numbers found
        int num = 2;   // Starting number to check for primality

        System.out.println("The first 30 prime numbers are:");

        while (count < 30) {
            boolean isPrime = true;

            // Check if num is divisible by any number from 2 to num-1
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break; // No need to check further if not prime
                }
            }

            // If isPrime is still true, then num is a prime number
            if (isPrime) {
                System.out.print(num + " ");
                count++;
            }
            num++;
        }
    }
}
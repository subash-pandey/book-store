import java.util.ArrayList;
import java.util.List;

public class FunctionalSumOfSquares {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            numbers.add(i);
        }

        int sum = numbers.stream()
                .parallel() // Enables parallel processing
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);

        System.out.println("Sum of squares of even numbers: " + sum);
    }
}
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListFilter<T> {

    public static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        if (list == null || predicate == null) {
            throw new IllegalArgumentException("List and predicate cannot be null.");
        }

        List<T> filteredList = new ArrayList<>();
        for (T element : list) {
            if (predicate.test(element)) {
                filteredList.add(element);
            }
        }
        return filteredList;
    }

    public static void main(String[] args) {
        // Example with Integer list
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Predicate<Integer> isEven = num -> num % 2 == 0;
        List<Integer> evenNumbers = filterList(numbers, isEven);
        System.out.println("Even numbers: " + evenNumbers);

        // Example with String list
        List<String> fruits = List.of("apple", "banana", "mango", "orange", "kiwi");
        Predicate<String> startsWithB = fruit -> fruit.startsWith("b");
        List<String> fruitsStartingWithB = filterList(fruits, startsWithB);
        System.out.println("Fruits starting with 'b': " + fruitsStartingWithB);

        // Edge case: Empty list
        List<String> emptyList = List.of();
        List<String> filteredEmptyList = filterList(emptyList, startsWithB);
        System.out.println("Filtered empty list: " + filteredEmptyList);

        // Test for null list
        try {
            filterList(null, isEven);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception for null list: " + e.getMessage());
        }

        // Test for null predicate
        try {
            filterList(numbers, null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception for null predicate: " + e.getMessage());
        }
    }
}
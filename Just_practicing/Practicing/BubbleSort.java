import java.util.ArrayList;
import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        // Integer list
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(5);
        numbers.add(3);
        numbers.add(9);
        numbers.add(2);

        // String list
        ArrayList<String> strings = new ArrayList<>(Arrays.asList("Ram", "Shyam", "hari", "Rita"));

        // Before sorting
        System.out.println("Before Sorting Numbers: " + numbers);
        System.out.println("Before Sorting Strings: " + strings);

        // Perform sorting
        bubbleSortNumbers(numbers);
        bubbleSort(strings);

        // After sorting
        System.out.println("After Sorting Numbers: " + numbers);
        System.out.println("After Sorting Strings: " + strings);
    }

    // Bubble sort for Integer ArrayList
    public static void bubbleSortNumbers(ArrayList<Integer> numbers) {
        int n = numbers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    // Swap elements
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                }
            }
        }
    }

    // Bubble sort for String ArrayList
    public static void bubbleSort(ArrayList<String> strings) {
        int n = strings.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (strings.get(j).compareTo(strings.get(j + 1)) > 0) {
                    // Swap elements
                    String temp = strings.get(j);
                    strings.set(j, strings.get(j + 1));
                    strings.set(j + 1, temp);
                }
            }
        }
    }
}
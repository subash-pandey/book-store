public class ShiftedSortedArray {

    public static int findMiddleElement(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid is the pivot (smallest element)
            if (mid > 0 && arr[mid - 1] > arr[mid]) {
                return arr[mid];
            }

            // If left side is sorted
            if (arr[low] <= arr[mid]) {
                // Check if the middle element is actually the middle element
                if (mid == (arr.length - 1) / 2) {
                    return arr[mid];
                }
                // Middle element is not in the left side
                low = mid + 1;
            } else { // Right side is sorted
                // Check if the middle element is actually the middle element
                if (mid == (arr.length - 1) / 2) {
                    return arr[mid];
                }
                // Middle element is not in the right side
                high = mid - 1;
            }
        }

        // Array is not shifted, return the actual middle element
        return arr[(arr.length - 1) / 2];
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {5, 1, 2, 3, 4};

        System.out.println("Middle element of arr1: " + findMiddleElement(arr1)); // Output: 7
        System.out.println("Middle element of arr2: " + findMiddleElement(arr2)); // Output: 3
        System.out.println("Middle element of arr3: " + findMiddleElement(arr3)); // Output: 2
    }
}
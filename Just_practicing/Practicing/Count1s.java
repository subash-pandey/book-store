import java.util.Scanner;

public class Count1s {
    public static void main(String[] args) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a binary number: ");
        int a = sc.nextInt();
        try {
            String binaryString = Integer.toBinaryString(a);
            for (char c : binaryString.toCharArray()) {
                if (c == '1') {
                    count++;
                }
            }
            System.out.format("The count of 1s in binary %d is %d. ", a, count);
        } catch (ArithmeticException e) {
           System.out.println("Exception: " + e);
        }
    }
}

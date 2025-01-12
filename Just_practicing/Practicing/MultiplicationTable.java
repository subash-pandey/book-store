import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        System.out.println("Multiplication Table");
        System.out.println("Please enter the  number:   ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println("Multiplication Table for "+number);
        System.out.println();
        for(int i=1;i<=10;i++) {
            System.out.println(i+" "+"X "+number+" = "+number*i);
        }

    }
}

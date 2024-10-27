import java.util.Random;
import java.util.Scanner;

public class CheckingErrors {

    private static void practiceBasicArithmetic(Scanner scanner, int difficulty) {
        int score = 0;
        Random random = new Random();
        System.out.println("Get ready for 10 questions!");
        int operand1, operand2, operation, maxOperand;

        for (int i = 1; i <= 10; i++) {
            switch (difficulty) {
                case 1: // Easy
                    maxOperand = 10;
                    operation = random.nextInt(2); // Only addition and subtraction
                    break;
                case 2: // Medium
                    maxOperand = 50;
                    operation = random.nextInt(4); // All four operations
                    break;
                case 3: // Hard
                    maxOperand = 100;
                    operation = random.nextInt(4); // All four operations
                    break;
                default:
                    maxOperand = 50; // Default to medium
                    operation = random.nextInt(4);
            }

            do {
                operand1 = random.nextInt(maxOperand);
                operand2 = random.nextInt(maxOperand);
                // 0 = addition, 1 = subtraction, 2 = multiplication, 3 = division
            } while (operation == 3 && operand2 == 0); // Check for division by zero

            int answer;
            switch (operation) {
                case 0:
                    answer = operand1 + operand2;
                    break;
                case 1:
                    answer = operand1 - operand2;
                    break;
                case 2:
                    answer = operand1 * operand2;
                    break;
                case 3:
                    answer = operand1 / operand2;
                    break;
                default:
                    answer = 0;
            } // Check for division by zero

            switch (operation) {
                case 0:
                    answer = operand1 + operand2;
                    break;
                case 1:
                    answer = operand1 - operand2;
                    break;
                case 2:
                    answer = operand1 * operand2;
                    break;
                case 3:
                    answer = operand1 / operand2;
                    break;
                default:
                    answer = 0;
            }


            System.out.print("Question " + i + ": ");
            switch (operation) {
                case 0:
                    System.out.print(operand1 + " + " + operand2 + " = ");
                    break;
                case 1:
                    System.out.print(operand1 + " - " + operand2 + " = ");
                    break;
                case 2:
                    System.out.print(operand1 + " * " + operand2 + " = ");
                    break;
                case 3:
                    System.out.print(operand1 + " / " + operand2 + " = ");
                    break;
            }

            if (Double.parseDouble(scanner.nextLine()) == answer) {
                score++;
            }
        }

        System.out.println("You got " + score + " out of 10 questions correct!");



    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        practiceBasicArithmetic(scanner, 2);



    }
}


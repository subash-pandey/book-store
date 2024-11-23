import java.util.Scanner;

public class BowlingScoreCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] scores = new int[21];
        int rollIndex = 0;
        int totalScore = 0;
        int frame = 1;

        while (frame <= 10) {
            System.out.printf("Frame %d, Roll 1: ", frame);
            scores[rollIndex] = scanner.nextInt();

            if (scores[rollIndex] == 10) { // Strike
                totalScore += 10;
                if (frame == 10) {
                    getBonusRolls(scores, rollIndex, scanner);
                }
                frame++;
            } else {
                System.out.printf("Frame %d, Roll 2: ", frame);
                rollIndex++;
                scores[rollIndex] = scanner.nextInt();

                if (scores[rollIndex - 1] + scores[rollIndex] == 10) { // Spare
                    totalScore += 10;
                    if (frame == 10) {
                        getBonusRolls(scores, rollIndex, scanner);
                    }
                } else {
                    totalScore += scores[rollIndex - 1] + scores[rollIndex];
                }
                frame++;
            }
            rollIndex++;
        }

        System.out.println("Total Score: " + totalScore);
    }

    static void getBonusRolls(int[] scores, int rollIndex, Scanner scanner) {
        System.out.print("Bonus Roll 1: ");
        rollIndex++;
        scores[rollIndex] = scanner.nextInt();

        if (scores[rollIndex - 2] == 10 || scores[rollIndex - 1] == 10) { // Bonus roll after strike or spare
            System.out.print("Bonus Roll 2: ");
            rollIndex++;
            scores[rollIndex] = scanner.nextInt();
        }
    }
}

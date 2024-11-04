import java.math.BigInteger;
import java.util.Scanner;

public class BeautifulPartitions {

    private static final int MOD = 998244353;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String aStr = scanner.nextLine();
        BigInteger a = new BigInteger(aStr);
        BigInteger l = new BigInteger(scanner.nextLine());
        BigInteger r = new BigInteger(scanner.nextLine());

        int n = aStr.length();
        BigInteger[] dp = new BigInteger[n + 1];
        dp[n] = BigInteger.ONE;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = BigInteger.ZERO;
            BigInteger currentNum = BigInteger.ZERO;
            for (int j = i; j < n; j++) {
                currentNum = currentNum.multiply(BigInteger.TEN).add(BigInteger.valueOf(aStr.charAt(j) - '0'));
                if (aStr.charAt(i) == '0' && j > i) {
                    break; // Leading zero condition
                }
                if (currentNum.compareTo(l) >= 0 && currentNum.compareTo(r) <= 0) {
                    dp[i] = (dp[i].add(dp[j + 1])).mod(BigInteger.valueOf(MOD));
                }
                if (currentNum.compareTo(r) > 0) {
                    break; // Optimization: currentNum will only get larger
                }
            }
        }

        System.out.println(dp[0]);
    }
}
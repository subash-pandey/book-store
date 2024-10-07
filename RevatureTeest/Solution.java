import java.util.ArrayList;
import java.util.List;
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // Assume odd length, try to extend Palindrome as centered at i
            int len2 = expandAroundCenter(s, i, i + 1); // Assume even length.
            int len = Math.max(len1, len2);
            if (len > (end - start)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left + 1; // return the length of the palindrome
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "babad";
        System.out.println("The longest palindromic substring of '" + input + "' is: " + solution.longestPalindrome(input));

        input = "cbbd";
        System.out.println("The longest palindromic substring of '" + input + "' is: " + solution.longestPalindrome(input));
    }
}
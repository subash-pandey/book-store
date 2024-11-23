// Java program to check if a number
// is happy number
import java.util.*;

class GFG
{

    // Returns sum of squares of digits
// of a number n. For example for n = 12
// it returns 1 + 4 = 5
    static int sumDigitSquare(int n)
    {
        int sq = 0;
        while (n > 0)
        {
            int digit = n % 10;
            sq += digit * digit;
            n = n / 10;
        }
        return sq;
    }

    // Returns true if n is Happy number
// else returns false.
    static boolean isHappy(int n)
    {
        // A set to store numbers during
        // repeated square sum process
        HashSet<Integer> s = new HashSet<Integer>();
        s.add(n);

        // Keep replacing n with sum of
        // squares of digits until we either
        // reach 1 or we endup in a cycle
        while (true)
        {

            // Number is Happy if we reach 1
            if (n == 1)
                return true;

            // Replace n with sum of squares
            // of digits
            n = sumDigitSquare(n);

            // If n is already visited, a cycle
            // is formed, means not Happy

            if ((s.contains(n) && n != (int)s.toArray()[ s.size()-1 ] ))
                return false;

            // Mark n as visited
            s.add(n);
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        int n = 2023;
        if (isHappy(n))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}

/* This code contributed by PrinciRaj1992 */
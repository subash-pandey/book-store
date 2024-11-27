import java.util.ArrayList;

public class ExpressionSolver {
    private ArrayList<String> expressionList;

    public ExpressionSolver(String s) {
        setExpression(s);
    }

    public void setExpression(String s) {
        expressionList = new ArrayList<>();

        String currentNumber = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber += c;
            } else {
                if (!currentNumber.isEmpty()) {
                    expressionList.add(currentNumber);
                    currentNumber = "";
                }
                expressionList.add(String.valueOf(c));
            }
        }
        if (!currentNumber.isEmpty()) {
            expressionList.add(currentNumber);
        }
    }

    public void solveExpression() {
        // Multiplication and Division
        for (int i = 0; i < expressionList.size(); i++) {
            if (expressionList.get(i).equals("*") || expressionList.get(i).equals("/")) {
                int num1 = Integer.parseInt(expressionList.get(i - 1));
                int num2 = Integer.parseInt(expressionList.get(i + 1));
                int result;
                if (expressionList.get(i).equals("*")) {
                    result = num1 * num2;
                } else {
                    result = num1 / num2;
                }
                expressionList.set(i - 1, String.valueOf(result));
                expressionList.remove(i);
                expressionList.remove(i);
                i -= 2; // Adjust index after removing two elements
            }
        }

        // Addition and Subtraction
        for (int i = 0; i < expressionList.size(); i++) {
            if (expressionList.get(i).equals("+") || expressionList.get(i).equals("-")) {
                int num1 = Integer.parseInt(expressionList.get(i - 1));
                int num2 = Integer.parseInt(expressionList.get(i + 1));
                int result;
                if (expressionList.get(i).equals("+")) {
                    result = num1 + num2;
                } else {
                    result = num1 - num2;
                }
                expressionList.set(i - 1, String.valueOf(result));
                expressionList.remove(i);
                expressionList.remove(i);
                i -= 2; // Adjust index after removing two elements
            }
        }
    }

    public String toString() {
        return expressionList.get(0);
    }
}
public class test {
    public static void main(String[] args) {
        ExpressionSolver expressionSolver = new ExpressionSolver("12+13+16-24*36");
        expressionSolver.solveExpression();
        System.out.println(expressionSolver);
    }
}

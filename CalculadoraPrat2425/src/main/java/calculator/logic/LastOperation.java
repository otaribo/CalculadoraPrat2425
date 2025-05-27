package main.java.calculator.logic;

public class LastOperation {
    private static String operator;
    private static String operand;

    public static void setLastOperation(String op, String num) {
        operator = op;
        operand = num;
    }

    public static String getOperator() { return operator; }
    public static String getOperand() { return operand; }
}

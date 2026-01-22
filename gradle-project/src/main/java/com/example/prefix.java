package com.example;
import java.util.*;
public class prefix{
    public static int evaluateExpr(String[] tokens, int start, String[][] vars, int varCount) {
        if (!tokens[start].equals("(")) {
            String val = getVarValue(tokens[start], vars, varCount);
            return Integer.parseInt(val);
        }
        start++;
        String operator = tokens[start];
        start++;
        List<Integer> operands = new ArrayList<>();
        while (!tokens[start].equals(")")) {
            if (tokens[start].equals("(")) {
                operands.add(evaluateExpr(tokens, start, vars, varCount));
                int parenCount = 1;
                start++;
                while (parenCount > 0) {
                    if (tokens[start].equals("(")) parenCount++;
                    if (tokens[start].equals(")")) parenCount--;
                    start++;
                }
            } else {
                String val = getVarValue(tokens[start], vars, varCount);
                operands.add(Integer.parseInt(val));
                start++;
            }
        }
        int result = operands.get(0);
        for (int i = 1; i < operands.size(); i++) {
            result = applyOperator(operator, result, operands.get(i));
        }
        return result;
    }
    
    private static String getVarValue(String name, String[][] vars, int varCount) {
        for (int i = 0; i < varCount; i++) {
            if (vars[i][0].equals(name)) {
                return vars[i][1];
            }
        }
        return name;
    }
    
    public static void solve_prefix(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int operand1 = stack.pop();
                int operand2 = stack.pop();
                int result = applyOperator(token, operand1, operand2);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        System.out.println("The result is: " + stack.pop());
    }

    private static int applyOperator(String operator, int operand1, int operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                System.out.println("Invalid operator");
                System.exit(1);
                return 0;
        }
    }

    public int evaluate(String input) {
        input = input.replace("(", " ( ").replace(")", " ) ");
        String[] tokens = input.trim().split("\\s+");
        return evaluateExpr(tokens, 0, new String[0][0], 0);
    }
}
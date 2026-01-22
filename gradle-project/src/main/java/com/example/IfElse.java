package com.example;
public class IfElse {

    public static int evaluateTokens(String[] tokens, int start, String[][] vars, int varCount) {
        int idx = start;
        int condition;
        if (tokens[idx].equals("(")) {
            idx++;
            if (tokens[idx].equals("<") || tokens[idx].equals(">") || 
                tokens[idx].equals("=") || tokens[idx].equals("<=") || tokens[idx].equals(">=")) {
                String cmpOp = tokens[idx++];
                String cmpLeft = getVarValue(tokens[idx], vars, varCount);
                idx++;
                String cmpRight = getVarValue(tokens[idx], vars, varCount);
                idx++;
                idx++;
                condition = compare(Integer.parseInt(cmpLeft), cmpOp, Integer.parseInt(cmpRight)) ? 1 : 0;
            } else {
                idx--;
                condition = prefix.evaluateExpr(tokens, idx, vars, varCount);
                int parenCount = 1;
                idx++;
                while (parenCount > 0) {
                    if (tokens[idx].equals("(")) parenCount++;
                    if (tokens[idx].equals(")")) parenCount--;
                    idx++;
                }
            }
        } else {
            String val = getVarValue(tokens[idx], vars, varCount);
            condition = Integer.parseInt(val);
            idx++;
        }
        
        int trueVal;
        if (tokens[idx].equals("(")) {
            trueVal = prefix.evaluateExpr(tokens, idx, vars, varCount);
            int parenCount = 1;
            idx++;
            while (parenCount > 0) {
                if (tokens[idx].equals("(")) parenCount++;
                if (tokens[idx].equals(")")) parenCount--;
                idx++;
            }
        } else {
            String val = getVarValue(tokens[idx], vars, varCount);
            trueVal = Integer.parseInt(val);
            idx++;
        }
        
        int falseVal;
        if (tokens[idx].equals("(")) {
            falseVal = prefix.evaluateExpr(tokens, idx, vars, varCount);
        } else {
            String val = getVarValue(tokens[idx], vars, varCount);
            falseVal = Integer.parseInt(val);
        }
        
        return condition != 0 ? trueVal : falseVal;
    }
    
    private static String getVarValue(String name, String[][] vars, int varCount) {
        for (int i = 0; i < varCount; i++) {
            if (vars[i][0].equals(name)) {
                return vars[i][1];
            }
        }
        return name;
    }
    
    public static int evaluate(String input) {
        String[] parts = input.split(" ");
        int left = Integer.parseInt(parts[1]);
        String operator = parts[2];
        int right = Integer.parseInt(parts[3]);
        int trueValue = Integer.parseInt(parts[4]);
        int falseValue = Integer.parseInt(parts[5]);
        boolean condition = compare(left, operator, right);
        if (condition) {
            return trueValue;
        } else {
            return falseValue;
        }
    }

    public static boolean compare(int left, String op, int right) {
        switch (op) {
            case ">":
                return left > right;
            case "<":
                return left < right;
            case "=":
                return left == right;
            case ">=":
                return left >= right;
            case "<=":
                return left <= right;
            default:
                System.out.println("Unknown operator");
                return false;
        }
    }
}

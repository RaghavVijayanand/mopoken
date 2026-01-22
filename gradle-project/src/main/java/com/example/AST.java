package com.example;
import java.util.*;
public class AST{
    static String[][] vars = new String[100][2];
    static int varCount = 0;
    
    public static String[] lexer(String input){
        input = input.replace("(", " ( ").replace(")", " ) ");
        String[] tokens = input.trim().split("\\s+");
        return tokens;
    }
    
    public static void parser(String[] tokens){
        int i = 0;
        while (i < tokens.length) {
            String token = tokens[i];
            if (token.equals("(")) {
                i++;
                if (i >= tokens.length) break;
                String cmd = tokens[i];
                if (cmd.equals("define")) {
                    String varName = tokens[i+1];
                    String varValue = tokens[i+2];
                    for (int j = 0; j < varCount; j++) {
                        if (vars[j][0].equals(tokens[i+2])) {
                            varValue = vars[j][1];
                            break;
                        }
                    }
                    String varInput = "define " + varName + " " + varValue;
                    varCount = variable.define(vars, varCount, varInput);
                    while (!tokens[i].equals(")")) i++;
                    i++;
                }
                else if (cmd.equals("if")) {
                    int result = IfElse.evaluateTokens(tokens, i+1, vars, varCount);
                    System.out.println(result);
                    while (!tokens[i].equals(")")) i++;
                    i++;
                }
                else if (cmd.equals("+") || cmd.equals("-") || cmd.equals("*") || cmd.equals("/")) {
                    i--;
                    int result = prefix.evaluateExpr(tokens, i, vars, varCount);
                    System.out.println(result);
                    int parenCount = 1;
                    i++;
                    while (parenCount > 0) {
                        if (tokens[i].equals("(")) parenCount++;
                        if (tokens[i].equals(")")) parenCount--;
                        i++;
                    }
                }
                else if (cmd.equals("<") || cmd.equals(">") || cmd.equals("=") || 
                         cmd.equals("<=") || cmd.equals(">=")) {
                    String op = tokens[i];
                    String left = tokens[i+1];
                    for (int j = 0; j < varCount; j++) {
                        if (vars[j][0].equals(left)) {
                            left = vars[j][1];
                            break;
                        }
                    }
                    String right = tokens[i+2];
                    for (int j = 0; j < varCount; j++) {
                        if (vars[j][0].equals(right)) {
                            right = vars[j][1];
                            break;
                        }
                    }
                    boolean result = IfElse.compare(Integer.parseInt(left), op, Integer.parseInt(right));
                    System.out.println(result ? 1 : 0);
                    while (!tokens[i].equals(")")) i++;
                    i++;
                }
                else {
                    i++;
                }
            }
            else {
                i++;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("LISP(exit to quit)");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equals("exit")) 
                break;
            String[] tokens = lexer(input);
            parser(tokens);
        }
        scanner.close();
    }

    public static void testing(String input) {
        String[] tokens = lexer(input);
        parser(tokens);
    }
}
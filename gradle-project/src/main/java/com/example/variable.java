package com.example;
public class variable {
    public static void main(String[] args) {
        String[][] vars = new String[100][2];
        int count = 0;
        count = define(vars, count, "define x 10");
        count = define(vars, count, "define y 20");
        count = define(vars, count, "define x 5");
        for (int i = 0; i < count; i++) {
            System.out.println(vars[i][0] + " = " + vars[i][1]);
        }
    }

    public static int define(String[][] vars, int count, String input) {
        String[] parts = input.split(" ");
        String name = parts[1];
        String value = parts[2];
        for (int i = 0; i < count; i++) {
            if (vars[i][0].equals(name)) {
                vars[i][1] = value;
                return count;
            }
        }
        vars[count][0] = name;
        vars[count][1] = value;
        return count + 1;
    }
}

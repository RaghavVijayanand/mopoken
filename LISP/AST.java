import java.util.*;

public class AST {
    
    public static String[] lexer(String input) {
        input = input.replace("(", " ( ").replace(")", " ) ");
        return input.trim().split("\\s+");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("LISP(exit to quit)");
        ExprFactory factory = new ExprFactory();
        EvalVisitor visitor = new EvalVisitor();
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equals("exit")) break;
            String[] tokens = lexer(input);
            int[] pos = {0};
            
            while (pos[0] < tokens.length) {
                Expression expr = factory.create(tokens, pos);
                if (expr != null) {
                    Object result = expr.accept(visitor);
                    if (result != null) {
                        System.out.println(result);
                    }
                }
            }
        }
        scanner.close();
    }
}
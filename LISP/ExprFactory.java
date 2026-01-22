import java.util.*;

public class ExprFactory {
    public Expression create(String[] tokens, int[] pos) {
        if (pos[0] >= tokens.length) return null;
        String tok = tokens[pos[0]];
        if (tok.equals("(")) {
            pos[0]++;
            String cmd = tokens[pos[0]];
            pos[0]++; 
            if (cmd.equals("define")) {
                String name = tokens[pos[0]++];
                Expression val = create(tokens, pos);
                while (!tokens[pos[0]].equals(")")) pos[0]++;
                pos[0]++;
                return new DefineExpr(name, val);
            }
            if (cmd.equals("if")) {
                Expression cond = create(tokens, pos);
                Expression t = create(tokens, pos);
                Expression f = create(tokens, pos);
                while (!tokens[pos[0]].equals(")")) pos[0]++;
                pos[0]++;
                return new IfExpr(cond, t, f);
            }
            if (cmd.equals("+") || cmd.equals("-") || cmd.equals("*") || cmd.equals("/") ||
                cmd.equals("<") || cmd.equals(">") || cmd.equals("=") || 
                cmd.equals("<=") || cmd.equals(">=")) {
                List<Expression> ops = new ArrayList<>();
                while (!tokens[pos[0]].equals(")")) {
                    ops.add(create(tokens, pos));
                }
                pos[0]++;
                return new OpExpr(cmd, ops);
            }
            while (!tokens[pos[0]].equals(")")) pos[0]++;
            pos[0]++;
            return null;
        }
        pos[0]++;
        if (tok.matches("-?\\d+")) {
            return new NumberExpr(Integer.parseInt(tok));
        }
        return new VarExpr(tok);
    }
}

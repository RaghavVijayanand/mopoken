public class EvalVisitor implements Visitor {
    public Object visit(NumberExpr expr) {
        return expr.value;
    }
    public Object visit(VarExpr expr) {
        try {
            String val = SymbolTable.getInstance().get(expr.name);
            if (val == null) {
                throw new RuntimeException("Variable not found: " + expr.name);
            }
            if (val.matches("-?\\d+")) {
                return Integer.parseInt(val);
            }
            return val;
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating variable: " + expr.name, e);
        }
    }
    public Object visit(OpExpr expr) {
        try {
            int result = (int) expr.operands.get(0).accept(this);
            for (int i = 1; i < expr.operands.size(); i++) {
                int val = (int) expr.operands.get(i).accept(this);
                if (expr.op.equals("+")) result += val;
                else if (expr.op.equals("-")) result -= val;
                else if (expr.op.equals("*")) result *= val;
                else if (expr.op.equals("/")) {
                    if (val == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    result /= val;
                }
                else if (expr.op.equals("<")) return (result < val) ? 1 : 0;
                else if (expr.op.equals(">")) return (result > val) ? 1 : 0;
                else if (expr.op.equals("=")) return (result == val) ? 1 : 0;
                else if (expr.op.equals("<=")) return (result <= val) ? 1 : 0;
                else if (expr.op.equals(">=")) return (result >= val) ? 1 : 0;
            }
            return result;
        } catch (ClassCastException e) {
            throw new RuntimeException("Invalid operand type for operation: " + expr.op, e);
        } catch (ArithmeticException e) {
            throw new RuntimeException("Arithmetic error: " + e.getMessage(), e);
        }
    }
    public Object visit(IfExpr expr) {
        Object cond = expr.condition.accept(this);
        int c = (cond instanceof Integer) ? (int) cond : 0;
        if (c != 0) {
            return expr.truePart.accept(this);
        } else {
            return expr.falsePart.accept(this);
        }
    }  
    public Object visit(DefineExpr expr) {
        Object val = expr.value.accept(this);
        SymbolTable.getInstance().set(expr.name, val.toString());
        return null;
    }
}

public interface Visitor {
    Object visit(NumberExpr expr);
    Object visit(VarExpr expr);
    Object visit(OpExpr expr);
    Object visit(IfExpr expr);
    Object visit(DefineExpr expr);
}

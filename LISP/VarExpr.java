public class VarExpr implements Expression {
    public String name;
    public VarExpr(String name) {
        this.name = name;
    }
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

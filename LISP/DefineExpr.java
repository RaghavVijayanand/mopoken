public class DefineExpr implements Expression {
    public String name;
    public Expression value;
    public DefineExpr(String name, Expression value) {
        this.name = name;
        this.value = value;
    }
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

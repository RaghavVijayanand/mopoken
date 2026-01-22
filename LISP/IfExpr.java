public class IfExpr implements Expression {
    public Expression condition;
    public Expression truePart;
    public Expression falsePart;
    public IfExpr(Expression condition, Expression truePart, Expression falsePart) {
        this.condition = condition;
        this.truePart = truePart;
        this.falsePart = falsePart;
    }
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

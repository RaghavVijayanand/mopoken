public class NumberExpr implements Expression {
    public int value;
    public NumberExpr(int value) {
        this.value = value;
    }
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

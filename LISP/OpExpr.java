import java.util.*;
public class OpExpr implements Expression {
    public String op;
    public List<Expression> operands;
    public OpExpr(String op, List<Expression> operands) {
        this.op = op;
        this.operands = operands;
    } 
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

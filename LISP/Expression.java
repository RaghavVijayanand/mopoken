public interface Expression {
    Object accept(Visitor visitor);
}

import java.util.function.BinaryOperator;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.function.Supplier;

class Expr<T> {

    private final Supplier<T> val;
    private final Supplier<Optional<Operator<T>>> oper;
    private final Supplier<Optional<Expr<T>>> left;
 
    private Expr(T val) {
        this.val = () -> val;
        this.left = () -> Optional.<Expr<T>>empty();
        this.oper = () -> Optional.<Operator<T>>empty();
    }

    private Expr(Supplier<T> val, Supplier<Optional<Operator<T>>> oper,
            Supplier<Optional<Expr<T>>> left) {

        this.val = val;
        this.left = left;
        this.oper = oper;
    }

    /*
    private Expr(T val, Optional<Operator<T>> oper, Optional<Expr<T>> left) {
        this.val = val;
        this.left = () -> left;
        this.oper = () -> oper;
    }
    */

    protected Expr(Expr<T> expr) {
        this(() -> expr.getVal(), () -> expr.getOp(), () -> expr.getLeft());
    }

    static <R> Expr<R> of(R val) {
        return new Expr<R>(val);
    }

    Optional<Operator<T>> getOp() {
        return this.oper.get();
    }

    Optional<Expr<T>> getLeft() {
        return this.left.get();
    }

    T getVal() {
        return this.val.get();
    }

    Supplier<T> getSup() {
        return this.val;
    }

    T evaluate() {
        return evaluate(this.getVal(), Optional.<Expr<T>>of(this));
    }

    T evaluate(T t, Optional<Expr<T>> expr) {
        return expr.flatMap(ex -> ex.getOp().flatMap(op -> ex.getLeft().map(leftExpr -> {
            T newVal = op.apply(leftExpr.getVal(), t);
            return ex.evaluate(newVal, Optional.<Expr<T>>of(leftExpr));
        }))).orElse(t);

    }
    
    Expr<T> op(Operator<T> oper2, Expr<T> expr) {

        return this.op(oper2, () -> expr.evaluate());
    }

    Expr<T> op(Operator<T> oper2, T curr) {
        return this.op(oper2, () -> curr);
    }

    Expr<T> op(Operator<T> oper2, Supplier<T> curr) {

        return this.getOp().map(x -> {
            Supplier<T> newVal;
            Supplier<Optional<Operator<T>>> newOper;
            Supplier<Optional<Expr<T>>> newLeft;

            if (x.compareTo(oper2) <= 0) {
                newVal = this.getLeft().map(y -> {Supplier.<T>of(x.apply(y.getVal(), this.getVal()));})
                    .orElseGet(this.getSup());
                newOper = () -> this.getLeft().flatMap(y -> y.getOp());
                newLeft = () -> this.getLeft().flatMap(y -> y.getLeft());
                Expr<T> newExpr = new Expr<T>(newVal, newOper, newLeft);
                return newExpr.op(oper2, curr);
            } else {
                return new Expr<T>(curr, () -> Optional.<Operator<T>>of(oper2),
                       () -> Optional.<Expr<T>>of(this));
            }
        }).orElse(new Expr<T>(curr, () -> Optional.<Operator<T>>of(oper2),
                () -> Optional.<Expr<T>>of(this)));
    }
                
    @Override 
    public String toString() {
        
        return String.format("(%s)", this.evaluate().toString());
    }
}

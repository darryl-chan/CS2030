import java.util.function.BinaryOperator;
import java.util.stream.Stream;
import java.util.Optional;

class IntExpr extends AbstractIntExpr {
    private static final Operator<Integer> division =
        Operator.<Integer>of((x, y) -> x / y, 3);
    private static final Operator<Integer> exponent =
        Operator.<Integer>of((x, y) -> {
            int curr = x;
            int counter = y;
            while (counter != 1) {
                curr *= x;
                counter -= 1;
            }
            return curr;
        }, 2);

    IntExpr(int i) {
        super(Expr.<Integer>of(i));
    }

    IntExpr(Expr<Integer> expr) {
        super(expr);
    }

    static IntExpr of(int i) {
        return new IntExpr(i);
    }

    IntExpr add(int i) {
        return new IntExpr(this.op(AbstractIntExpr.addition, i));
    }

    IntExpr sub(int i) {
        return new IntExpr(this.op(AbstractIntExpr.addition, -1 * i));
    }

    IntExpr mul(int i) {
        return new IntExpr(this.op(AbstractIntExpr.multiplication, i));
    }

    IntExpr div(int i) {
        return new IntExpr(this.op(division, i));
    }

    IntExpr exp(int i) {
        return new IntExpr(this.op(exponent, i));
    }
}

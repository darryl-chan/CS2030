import java.util.function.BinaryOperator;
import java.util.stream.Stream;
import java.util.Optional;

class Operator<T> implements Comparable<Operator<T>> {

    private final BinaryOperator<T> func;
    private final Integer val;

    private Operator(BinaryOperator<T> func, Integer val) {
        this.func = func;
        this.val = val;
    }

    static <R> Operator<R> of(BinaryOperator<R> func, Integer val) {
        return new Operator<R>(func, val);
    }

    T apply(T i1, T i2) {
        return this.func.apply(i1, i2);
    }

    public int getPrecedence() {
        return this.val;
    }

    @Override
    public int compareTo(Operator<T> o) {
        return this.getPrecedence() - o.getPrecedence();
    }

    @Override
    public String toString() {
        return "Operator of precedence " + this.val;
    }
}

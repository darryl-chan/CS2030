import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.BinaryOperator;

class DnC<T, R> {

    private final Optional<Supplier<T>> t;
    private final Predicate<T> pred;
    private final Function<T, R> func;
    private final Optional<Function<T, Pair<T, T>>> func2;
    private final Optional<Function<T, Pair<Supplier<T>, Supplier<T>>>> func3;

    protected DnC(T t, Predicate<T> pred, Function<T, R> func,
            Optional<Function<T, Pair<T, T>>> func2, Optional<Function<T, Pair<Supplier<T>, Supplier<T>>>> func3) {
        this.t = Optional.<Supplier<T>>of(() -> t);
        this.pred = pred;
        this.func = func;
        this.func2 = func2;
        this.func3 = func3;
    }

    private DnC(Optional<Supplier<T>> t, Predicate<T> pred, Function<T, R> func, 
            Optional<Function<T, Pair<T, T>>> func2, Optional<Function<T, Pair<Supplier<T>, Supplier<T>>>> func3) {
        this.t = t;
        this.pred = pred;
        this.func = func;
        this.func2 = func2;
        this.func3 = func3;
    }
 
    static <T, R> DnC<T, R> of(T t, Predicate<T> pred, Function<T, R> func) {
        return new DnC<T, R>(t, pred, func, Optional.empty(), Optional.empty());
    }

    static <T, R> DnC<T, R> of(T t, Predicate<T> pred, Function<T, R> func, Function<T, Pair<T, T>> func2) {
        return new DnC<T, R>(t, pred, func, Optional.<Function<T, Pair<T, T>>>of(func2), Optional.empty());
    }

    static <T, R> DnC<T, R> of(Supplier<T> t, Predicate<T> pred, Function<T, R> func, Function<T, Pair<Supplier<T>, Supplier<T>>> func2) {
        return new DnC<T, R>(Optional.<Supplier<T>>of(t), pred, func, Optional.empty(),Optional.<Function<T, Pair<Supplier<T>, Supplier<T>>>>of(func2));
    }

    DnC<T, R> create(Supplier<T> t) {
        return new DnC<T, R>(Optional.<Supplier<T>>of(t), this.getPred(), this.getFunc(), this.getFunc2(), this.getFunc3());
    }

    DnC<T, R> create(T t) {
        return create(() -> t);
    }

    DnC<T, R> empty() {
        return new DnC<T, R>(Optional.empty(), this.getPred(), this.getFunc(), this.getFunc2(), this.getFunc3());
    }

    Optional<Supplier<T>> getT() {
        return this.t;
    }

    Predicate<T> getPred() {
        return this.pred;
    }

    Function<T, R> getFunc() {
        return this.func;
    }

    Optional<Function<T, Pair<T, T>>> getFunc2() {
        return this.func2;
    }

    Optional<Function<T, Pair<Supplier<T>, Supplier<T>>>> getFunc3() {
        return this.func3;
    }

    void peek(Consumer<T> action) {
        this.getT().map(t -> t.get()).ifPresent(action);
    }

    Optional<R> solve() {
        return this.getT().flatMap(x -> this.solve(x.get()));
    }

    Optional<R> solve(T t) {
        return Optional.<T>of(t).filter(this.getPred()).map(this.getFunc());
    }

    Optional<R> solve(BinaryOperator<R> func3) {
        return this.getT().map(t -> t.get()).flatMap(t -> this.solve(t)
                .or(() -> this.getFunc2().flatMap(f -> this.left().solve(func3).flatMap(l -> 
                            this.right().solve(func3).map(r -> func3.apply(l,r)))))
                .or(() -> this.getFunc3().map(f -> f.apply(t)).flatMap(p1 -> this.create(p1.first()).solve(func3).flatMap(l ->
                            this.create(p1.second()).solve(func3).map(r -> func3.apply(l,r))))));
    }

    Optional<Pair<DnC<T, R>, DnC<T, R>>> split() {
        Optional<Pair<T, T>> p = this.getT().map(t -> t.get()).flatMap(t -> 
                this.solve(t).map(x -> Pair.of(t,t)).or(() -> this.getFunc2().map(f -> f.apply(t))));
        return p.map(p1 -> Pair.<DnC<T, R>, DnC<T, R>>of(this.create(p1.first()),
                    this.create(p1.second())));
    }

    DnC<T, R> left() {
        return this.split().map(p -> p.first()).orElse(this.empty());
    }

    DnC<T, R> right() {
        return this.split().map(p -> p.second()).orElse(this.empty());
    }
}

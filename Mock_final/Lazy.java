import java.util.function.Function;
import java.util.function.Supplier;

class Lazy<T> implements Supplier<T> {
    private final Supplier<T> supplier;

    Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    Lazy(T t) {
        this(() -> t);
    }

    public T get() {
        return this.supplier.get();
    }

    <U> Lazy<U> flatMap(Function<? super T, ? extends Lazy<? extends U>> mapper) {
        return new Lazy<U>(() -> mapper.apply(this.supplier.get()).get());
    }

    <U> Lazy<U> map(Function<? super T, ? extends U> mapper) {
        return new Lazy<U>(() -> mapper.apply(this.supplier.get()));
    }
}

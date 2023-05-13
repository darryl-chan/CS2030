import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    static <U> Maybe<U> of(U value) {
        return new Maybe<U>(value);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    private T get() {
        return this.value;
    }

    private boolean isEmpty() {
        return this.value == null;
    }

    private boolean isPresent() {
        return !this.isEmpty();
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.isEmpty()) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.value));
        }
    }

    Maybe<T> filter(Predicate<? super T> pred) {
        return this.isEmpty() ? this : pred.test(this.get()) ? this : Maybe.<T>empty(); 
    }

    void ifPresent(Consumer<? super T> action) {
        if (this.isPresent()) {
            action.accept(this.get());
        }
    }

    void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (this.isPresent()) {
            action.accept(this.get());
        } else {
            emptyAction.run();
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Maybe<?> other) {
            if (other.isEmpty()) {
                return this.isEmpty();
            } else {
                return other.map(x -> x.equals(this.get())).get();
            }
        } else {
            return false;
        }
    }

    public T orElse(T t) {
        return (this.isPresent()) ? this.get() : t;
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        return (this.isPresent()) ? this.get() : supplier.get();
    }

    public Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier) {
        if (this.isPresent()) {
            return this;
        } else {

            Maybe<T> t = supplier.get().map(x -> x);
            //Maybe<T> t = supplier.get();
            //R r = supplier.get().map(x -> x);
            return t;
        }
    }

    public <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
        if (this.isEmpty()) {
            return Maybe.<R>empty();
        } else {
            Maybe<? extends R> curr = mapper.apply(this.value);
            return Maybe.<R>of(curr.get());
        }
    }

    @Override
    public String toString() {
        if (this.value == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.value + "]";
        }
    }
}

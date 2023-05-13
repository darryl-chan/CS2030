import java.util.function.Function;
import java.util.Optional;

class Log<T> {

    private final T t;
    private final String str;

    private Log(T t, String str) {

        this.t = t;
        this.str = str;
    }

    public T get() {
        return this.t;
    }

    public String getString() {
        return this.str;
    }

    public boolean compareT(Object o) {
        return this.t.equals(o);
    }

    public boolean compareString(String str) {
        return this.str.equals(str);
    }

    public static <T> Log<T> of(T t) {
        return Log.<T>of(t, "");
    }

    public static <T> Log<T> of(T t, String str) {
        Optional<T> opt = Optional.<T>ofNullable(t).filter(x -> !(x instanceof Log<?>));
        Optional<String> ops = Optional.<String>ofNullable(str);
        Optional<Log<T>> oplogt = ops.flatMap(x -> opt.map(y -> new Log<T>(y, x)));

        return oplogt.orElseThrow(() -> new IllegalArgumentException("Invalid arguments"));
    }

    public <R> Log<R> map(Function<? super T, ? extends R> mapper) {
        return Log.of(mapper.apply(this.t), str);
    }

    public Log<T> addString(String str) {
        String finalStr = str;
        if (!str.equals("")) {
            finalStr += "\n";
        }
        finalStr += this.str;
        return Log.of(this.t, finalStr);
    }

    public <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> mapper) {
        R r = mapper.apply(this.t).get();
        String str = mapper.apply(this.t).getString();
        return Log.<R>of(r, str).addString(this.str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Log<?> log) {
            return log.compareT(this.t) && log.compareString(this.str);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String str1 = String.format("Log[%s]", this.t.toString());
        return (this.str.equals("")) ? str1 : 
            str1 + "\n" + this.str;
    }
}

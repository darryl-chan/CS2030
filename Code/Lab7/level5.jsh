import java.util.function.Function;
import java.util.Optional;

Log<Integer> sum(int n) {
    if (n == 0) {
        return Log.<Integer>of(0, "hit base case!");
    } else {
        return sum(n-1).flatMap(x -> Log.<Integer>of(x + n, "adding " + n));
    }
}

Log<Integer> f(int n) {
    Log<Integer> lg = Log.<Integer>of(n);

    while(n != 1) {
        
        if(n %2 == 0) {
            lg = lg.flatMap(x -> Log.<Integer>of(x / 2, String.format("%d / 2", x)));
        } else {
            lg = lg.flatMap(x -> Log.<Integer>of(3 * x + 1, String.format("3(%d) + 1", x)));
        }
        n = lg.get();
    }
    lg = lg.flatMap(x -> Log.<Integer>of(x, "1"));
    return lg;
}


import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.function.UnaryOperator;

class Main {

    Main() {
    }

    static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2, n)
            .noneMatch(x -> n % x == 0);
    }

    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(1, n).filter(x -> isPrime(x))
            .filter(x -> isPrime(x - 2) || isPrime(x + 2));
    }

    static String reverse(String str) {
        String finalString = "";
        return Stream.<String>of(str.split("")).reduce(finalString,
                (x, y) -> y + x);
    }

    static long countRepeats(List<Integer> list) {
        
        IntStream s1 = IntStream.range(0, list.size() - 1);

        IntStream s2 = s1.filter(x -> list.get(x) == list.get(x + 1));

        List<Integer> l1 = s2.boxed().toList();
        if (l1.size() == 0) {
            return 0;
        }

        IntStream s3 = IntStream.range(0, l1.size() - 1);

        long i1 = s3.filter(x -> l1.get(x) + 1 != l1.get(x + 1)).mapToLong(x -> x).count() + 1;

        return i1;
    }

    static UnaryOperator<List<Integer>> generateRule() {
        return list -> {
            int len = list.size();
            IntStream s1 = IntStream.rangeClosed(0, len - 1);
            return s1.map(x -> {
                if (list.get(x) == 1) {
                    return 0;
                } else if (x == 0) {
                    return (list.get(x + 1) == 1) ? 1 : 0;
                } else if (x == len - 1) {
                    return (list.get(x - 1) == 1) ? 1 : 0;
                } else {
                    return (list.get(x - 1) + list.get(x + 1) == 1) ? 1 : 0;
                }
            }).boxed().toList();
        };
    }

    static Stream<String> gameOfLife(List<Integer> list,
            UnaryOperator<List<Integer>> rule, int n) {
        int len = list.size();
        Stream<Integer> i1 = IntStream.rangeClosed(0, len * n - 1).boxed();

        List<String> list1 = Stream.<List<Integer>>iterate(list, rule).limit(n)
            .flatMap(x -> x.stream())
            .map(x -> (x == 0) ? "." : "x")
            .toList();

        return Stream.<String>of(i1.map(x -> {
            if ((x) % len == 0 && x != len * n - 1 && x != 0) {
                return "\n" + list1.get(x);
            } else {
                return list1.get(x);
            }
        }).reduce("", (x, y) -> x + y));
        

        //Stream<? extends Integer> i1 = sli1.map(x -> x.stream());
        //i1.forEach(x -> System.out.println(x));
        
        //Stream<Integer> i1 = list.stream().iterate(list, rule).limit(n);
        //Stream<String> s1 = i1.
        //Stream<String> s1 = 
    }

}

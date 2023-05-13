import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class SumList extends DnC<List<Integer>, Integer> {
    SumList(List<Integer> lst) {
        super(lst, list -> list.size() == 1, list -> list.get(0), Optional.of(list -> {
            int size = list.size();
            int mid = Optional.of(size).filter(x -> x % 2 == 1).map(x -> x / 2 + 1).orElse(size / 2);
            return Pair.of(list.subList(0, mid), list.subList(mid, size));
        }), Optional.empty());
    }
}

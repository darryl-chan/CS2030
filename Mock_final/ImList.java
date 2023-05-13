import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.BiFunction;

class ImList<E> implements Iterable<E> {
    private final ArrayList<E> elems;

    ImList() {
        this.elems = new ArrayList<E>();
    }

    ImList(List<? extends E> list) {
        this.elems = new ArrayList<E>(list);
    }

    ImList<E> add(E elem) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.add(elem);
        return newList;
    }

    ImList<E> addAll(ImList<? extends E> list) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.addAll(list.elems);
        return newList;
    }

    E get(int index) {
        return this.elems.get(index);
    }

    boolean isEmpty() {
        return this.elems.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return this.elems.iterator();
    }

    <U> U reduce(U identity, BiFunction<? super U, ? super E, ? extends U> acc) {
        for (E t : this) {
            identity = acc.apply(identity, t);
        }
        return identity;
    }

    ImList<E> remove(int index) {
        ImList<E> newList = new ImList<E>(this.elems);
        if (index < this.size()) {
            newList.elems.remove(index);
        }
        return newList;
    }

    int size() {
        return this.elems.size();
    }

    @Override
    public String toString() {
        return this.elems.toString();
    }
}

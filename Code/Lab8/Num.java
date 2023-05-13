import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.List;

class Num extends AbstractNum<Integer> {

    private Num(AbstractNum<Integer> abnum) {
        super(abnum.opt);
    }

    static Num of(Optional<Integer> opt) {
        return new Num(new AbstractNum<Integer>(opt.filter(AbstractNum.valid)));
    }

    static Num of(int i) {
        if (!AbstractNum.valid.test(i)) {
            return new Num(new AbstractNum<Integer>(Optional.<Integer>empty()));
        }
 

        List<Optional<Integer>> list = Stream.<Optional<Integer>>iterate(
                AbstractNum.zero().opt, x -> x.map(
                    AbstractNum.s)).limit(AbstractNum.s.apply(i)).toList();
        int index = AbstractNum.n.apply(AbstractNum.s.apply(AbstractNum.n.apply(list.size())));
        Optional<Integer> opti = list.get(index).filter(AbstractNum.valid);

        return new Num(new AbstractNum<Integer>(opti));
    }

    static Num invalid() {
        return new Num(new AbstractNum<Integer>(Optional.<Integer>empty()));
    }

    static Num zero() {
        return new Num(AbstractNum.zero());
    }
 
    static Num one() {
        return Num.of(AbstractNum.zero().opt.map(AbstractNum.s));
    }

    Num succ() {
        if (this.isValid()) {
            return Num.of(this.opt.map(AbstractNum.s));
        } else {
            return this;
        }
    }

    Num add(Num number) {
        if (!this.isValid() || !number.isValid()) {
            return Num.invalid();
        } else {
            return Num.of(this.opt.flatMap(x -> number.opt.map(y -> Integer.sum(x, y))));
        }
    }

    Num mul(Num number) {
        if (!this.isValid() || !number.isValid()) {
            return Num.invalid();
        } else if (this.equals(Num.zero()) || number.equals(Num.zero())) {
            return Num.zero();
        } else {

            Num one = Num.one();
            Num original = number;
            while (!this.equals(one)) {
                one = one.succ();
                number = number.add(original);
            }
            return number;
        }
    }
            
    Num sub(Num number) {
        if (!this.isValid() || !number.isValid()) {
            return Num.invalid();
        } else {
            Optional<Integer> curr = number.opt.map(AbstractNum.n);
            Num zero = Num.zero();
            while (!this.equals(zero)) {
                curr = curr.map(AbstractNum.s);
                zero = zero.succ();
            }

            return Num.of(curr);
        }
    }


}











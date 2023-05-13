import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.List;

class Fraction extends AbstractNum<Frac> {

    
    private Fraction(Num n, Num d) {
        super(Optional.<Frac>of(Frac.of(n, d)));
    }

    private Fraction(int n, int d) {
        this(Num.of(n), Num.of(d));
    }

  
    private Fraction(Optional<Frac> opt) {
        super(opt);
    }


    static Fraction invalid() {
        return new Fraction(Optional.<Frac>empty());
    }

    static Fraction of(Optional<Frac> opt) {
        return new Fraction(opt.filter(x -> x.first().isValid() &&
                    x.second().sub(Num.one()).isValid()));
    }
    
    static Fraction of(Num n, Num d) {
        if (n.isValid() && d.sub(Num.one()).isValid()) {
            return new Fraction(n, d);
        } else {
            return Fraction.invalid();
        }
    }

    static Fraction of(int n, int d) {
        return Fraction.of(Num.of(n), Num.of(d));
    }

    public Fraction add(Fraction f) {
        Optional<Frac> topt = this.opt;
        Optional<Frac> fopt = f.opt;

        Optional<Num> numer = topt.flatMap(x -> fopt.map(y -> x.first().mul(y.second()).add(
                            x.second().mul(y.first()))));
        Optional<Num> denom = topt.flatMap(x -> fopt.map(y -> x.second().mul(y.second())));

        return Fraction.of(numer.flatMap(x -> denom.map(y -> Frac.of(x, y))));
    }

    public Fraction sub(Fraction f) {
        Optional<Frac> topt = this.opt;
        Optional<Frac> fopt = f.opt;

        Optional<Num> numer = topt.flatMap(x -> fopt.map(y -> x.first().mul(y.second()).sub(
                            x.second().mul(y.first()))));
        Optional<Num> denom = topt.flatMap(x -> fopt.map(y -> x.second().mul(y.second())));

        return Fraction.of(numer.flatMap(x -> denom.map(y -> Frac.of(x, y))));
        
    }

    public Fraction mul(Fraction f) {
        Optional<Frac> topt = this.opt;
        Optional<Frac> fopt = f.opt;

        Optional<Num> numer = topt.flatMap(x -> fopt.map(y -> x.first().mul(y.first())));
        Optional<Num> denom = topt.flatMap(x -> fopt.map(y -> x.second().mul(y.second())));

        return Fraction.of(numer.flatMap(x -> denom.map(y -> Frac.of(x, y))));

    }
}


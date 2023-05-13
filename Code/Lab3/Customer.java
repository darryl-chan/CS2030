import java.util.function.Supplier;

class Customer {

    private final int id;
    private final Supplier<Double> dst;

    public Customer(int id, Supplier<Double> dst) {
        this.id = id;
        this.dst = dst;
    }

    public int getId() {
        return this.id;
    }

    public double getServiceTime() {
        return this.dst.get();
    }

    @Override
    public String toString() {
        return "" + this.getId();
    }
}

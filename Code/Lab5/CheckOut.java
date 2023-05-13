import java.util.function.Supplier;
import java.util.Random;

class CheckOut extends Server {

    CheckOut(int id, int qmax, Supplier<Double> rest) {
        super(id, qmax, rest);
    }

    CheckOut(int id, int qmax, int q, double available, Supplier<Double> rest) {
        super(id, qmax, q, available, rest);
    }

    public Server newCheck(int q, double available) {
        return new CheckOut(this.getId(), this.getQMax(), q, available, this.getRest());
    }

    public Server newCheck(int q) {
        return this.newCheck(q, this.getAvailable());
    }

    public Server newCheck(double available) {
        return this.newCheck(this.getQ(), available);
    }

    @Override
    public boolean currAvailable(double time) {
        return this.getQ() == 0;
    }

    @Override
    public Server serve(Customer c) {
        int q = this.getQ();
        q += 1;

        return this.newCheck(q);
    }
    
    @Override
    public Server serve() {
        int q = this.getQ();
        q += 1;
        return this.newCheck(q);
    }

    @Override
    public Server returnServer() {
        int q = this.getQ();
        q -= 1;
        return this.newCheck(q);
    }

    @Override
    public Server updateTime(double time) {
        return this.newCheck(time);
    }

    @Override
    public Server done(Server s) {
        if (this.equals(s)) {
            int q  = this.getQ();
            q -= 1;
            return this.newCheck(q);
        } else {
            return this;
        }
    }


    @Override
    public double getRestTime() {
        return 0;
    }

    @Override
    public String toString() {
        return "self-check " + this.getId();
    }
    
}


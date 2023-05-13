import java.util.function.Supplier;
import java.util.Random;

class Server {
    
    private final int id;
    private final int qmax;
    private final int q;
    private final double available;
    private final Supplier<Double> rest;
    //private final boolean isRest;

    public int getId() {
        return this.id;
    }

    public int getQMax() {
        return this.qmax;
    }

    public int getQ() {
        return this.q;
    }

    public double getAvailable() {
        return this.available;
    }

    public Supplier<Double> getRest() {
        return this.rest;
    }

    Server(int id, int qmax, Supplier<Double> rest) {
        this.id = id;
        this.qmax = qmax;
        this.q = 0;
        this.available = 0;
        this.rest = rest;
        //this.isRest = false;
    }

    Server(int id, int qmax, int q, double available, Supplier<Double> rest) {
        this.id = id;
        this.qmax = qmax;
        this.q = q;
        this.available = available;
        this.rest = rest;
        //this.isRest = isRest;
    }

    public Server newServer(int currQ, double available) {
        return new Server(this.id, this.qmax, currQ, available, this.rest);
    }

    public Server returnServer() {
        int q = this.q;
        q -= 1;
        return this.newServer(q, this.available);
    }

    /*
    public boolean currAvailable() {
        return this.q == 0;
    }
    */

    public boolean currAvailable(double time) {
        return this.available <= time && this.q == 0;
    }

    public boolean waitAvailable() {
        return this.q < qmax;
    }

    public Server serve(Customer c) {
        int q = this.q;
        q += 1;
 
        return this.newServer(q, this.available);
        // return new Server(this.id, this.qmax, q, this.available,);
    }

    public Server serve() {
        int q = this.q;
        q += 1;
        return this.newServer(q, this.available);
    }

    public Server done(Server s) {
        if (this.equals(s)) {
            int q = this.q;
            q -= 1;
            return this.newServer(q, this.available);
            //return new Server(this.id, this.qmax, q, this.available);
        } else {
            return this;
        }
    }

    public Server updateTime(double time) {
        return this.newServer(this.q, time);
        //return new Server(this.id, this.qmax, this.q, time);
    }

    public boolean equals(Server s) {
        return s.getId() == this.id;
    }

    public double getRestTime() {
        return this.rest.get();
    }

    @Override
    public String toString() {
        return  "" + this.getId();
    }
}



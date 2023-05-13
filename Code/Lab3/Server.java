class Server {
    
    private final int id;
    private final int qmax;
    private final int q;
    private final double available;

    public int getId() {
        return this.id;
    }

    Server(int id, int qmax) {
        this.id = id;
        this.qmax = qmax;
        this.q = 0;
        this.available = 0;
    }

    Server(int id, int qmax, int q, double available) {
        this.id = id;
        this.qmax = qmax;
        this.q = q;
        this.available = available;
    }

    public double getAvailable() {
        return this.available;
    }

    public Server returnServer() {
        int q = this.q;
        q -= 1;
        return new Server(this.id, this.qmax, q, this.available);
    }

    public boolean currAvailable() {
        return this.q == 0;
    }

    public boolean waitAvailable() {
        return this.q < qmax;
    }

    public Server serve(Customer c) {
        int q = this.q;
        q += 1;
        return new Server(this.id, this.qmax, q, this.available);
    }

    public Server done(Server s) {
        if (this.equals(s)) {
            int q = this.q;
            q -= 1;
            return new Server(this.id, this.qmax, q, this.available);
        } else {
            return this;
        }
    }

    public Server updateTime(double time) {
        return new Server(this.id, this.qmax, this.q, time);
    }

    public boolean equals(Server s) {
        return s.getId() == this.id;
    }

    public Server nextAvailable(double time) {
        return new Server(this.id, this.qmax, this.q, time);
    }

    @Override
    public String toString() {
        return  "" + this.getId();
    }
}



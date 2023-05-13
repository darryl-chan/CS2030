class Server {
    
    private final int id;
    private final Customer customer;
    private final boolean available;
    
    private int getId() {
        return this.id;
    }

    private Customer getCustomer() {
        return this.customer;
    }

    private boolean getAvailable() {
        return this.available;
    }

    public boolean isAvailable() {
        return this.getAvailable();
    }

    public Server(int id) {
        this.id = id;
        this.customer = new Customer(0,0,0);
        this.available = true;
    }

    public Server(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.available = false;
    }

    public Server service(double time) {
        if (this.getAvailable()) {
            return this;
        } else if (this.getCustomer().shouldLeave(time)) {
            return new Server(this.getId());
        } else {
            return this;
        }
    }

    public Server serveCustomer(Customer customer) {
        return new Server(this.getId(), customer);
    }

    @Override
    public String toString() {
        return "server " + this.getId();
    }
}



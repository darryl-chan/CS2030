class Event {

    private static final int priority = 5;
    private final Customer customer;
    private final PQ<Server> pqServer;

    Event(Customer customer) {
        this.customer = customer;
        this.pqServer = new PQ<Server>(new ServerComparator());
    }

    Event(Customer customer, PQ<Server> pqServer) {
        this.customer = customer;
        this.pqServer = pqServer;
    }

    protected Pair<Integer,Integer> updateList(Pair<Integer, Integer> list) {
        return list;
    }

    protected PQ<Server> getpqServer() {
        return this.pqServer;
    }

    public int getCustomerId() {
        return this.getCustomer().getId();
    }

    protected double getExitTime() {
        return this.getCustomer().getExit();
    }

    public double getTime() {
        return this.getCustomer().getArrival();
    }

    protected Customer getCustomer() {
        return this.customer;
    }

    public int getPriority() {
        return this.priority;
    }
    
    public boolean hasNextEvent() {
        return true;
    }

    public Event serve(PQ<Server> pqServer) {
        return new Event(this.getCustomer(), pqServer);
    }

    public PQ<Server> updateServer() {
        return this.getpqServer();
    }

    public Event updateEvent() {
        return this;
    }

}

class DoneEvent extends Event {
    
    private static final int donePriority = 1;
    private final Server server;
    
    DoneEvent(Customer customer, Server server) {
        super(customer);
        this.server = server;
    }

    DoneEvent(Customer customer, PQ<Server> pqServer, Server server) {
        super(customer, pqServer);
        this.server = server;
    }

    private Server getServer() {
        return this.server;
    }
 
    @Override
    public int getPriority() {
        return this.donePriority;
    }


    @Override
    public Pair<Integer, Integer> updateList(Pair<Integer, Integer> list) {
        return new Pair<Integer, Integer>(list.first() + 1, list.second());
    }

    @Override
    public boolean hasNextEvent() {
        return false;
    }

    @Override
    public DoneEvent serve(PQ<Server> pqServer) {
        return new DoneEvent(this.getCustomer(), pqServer, this.getServer());
    }

    @Override
    public PQ<Server> updateServer() {
        PQ<Server> updatedPQ = this.getpqServer().add(this.getServer());
        return updatedPQ;
    }

    @Override
    public double getTime() {
        return this.getCustomer().getExit();
    }

    @Override
    public String toString() {
        String str1 = String.format("%.3f " +  this.getCustomer().toString(), this.getTime());
        String str2 = " done serving by " + this.getServer().toString();
        return str1 + str2;
    }
}

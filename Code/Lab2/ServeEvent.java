class ServeEvent extends Event {
    
    private static final int servePriority = 3;
    private final Server server;

    ServeEvent(Customer customer, Server server) {
        super(customer);
        this.server = server;
    }

    ServeEvent(Customer customer, Server server, PQ<Server> pqServer) {
        super(customer, pqServer);
        this.server = server;
    }

    @Override 
    public ServeEvent serve(PQ<Server> pqServer) {
        return new ServeEvent(this.getCustomer(), this.server, pqServer);
    }

    private Server getServer() {
        return this.server;
    }

    public Event updateEvent() {
        return new DoneEvent(this.getCustomer(), this.getServer());
    }

    @Override
    public int getPriority() {
        return this.servePriority;
    }

    @Override
    public String toString() {
        String str1 = String.format("%.3f " +  this.getCustomer().toString(), this.getTime());
        String str2 = " serves by " + this.getServer().toString();
        return str1 + str2;
    }
}

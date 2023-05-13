class ArrivalEvent extends Event {

    private static final int arrivePriority = 4;

    public ArrivalEvent(Customer customer) {
        super(customer);
    }

    public ArrivalEvent(Customer customer, PQ<Server> pqServer) {
        super(customer, pqServer);
    }

    public Event updateEvent() {
        if (this.getpqServer().isEmpty()) {
            return new LeaveEvent(this.getCustomer());
        } else { 
            Pair<Server, PQ<Server>> pairObj = this.getpqServer().poll();
            return new ServeEvent(this.getCustomer(), pairObj.first());
        }
    }
    
    @Override
    public ArrivalEvent serve(PQ<Server> pqServer) {
        return new ArrivalEvent(this.getCustomer(), pqServer);
    }

    @Override
    public PQ<Server> updateServer() {
        if (this.getpqServer().isEmpty()) {
            return this.getpqServer();
        } else {
            Pair<Server, PQ<Server>> pairObj = this.getpqServer().poll();
            return pairObj.second();
        }
    }
    
    @Override
    public int getPriority() {
        return this.arrivePriority;
    }

    @Override
    public String toString() {
        String str1 = String.format("%.3f " +  this.getCustomer().toString(), this.getTime());
        String str2 = " arrives";
        return str1 + str2;
    }
}

class LeaveEvent extends Event {

    private static final int leavePriority = 2;

    LeaveEvent(Customer customer) {
        super(customer);
    }

    LeaveEvent(Customer customer, PQ<Server> pqServer) {
        super(customer, pqServer);
    }
    
    @Override
    public int getPriority() {
        return this.leavePriority;
    }
   
    @Override
    public Pair<Integer, Integer> updateList(Pair<Integer, Integer> list) {
        return new Pair<Integer, Integer>(list.first(), list.second() + 1);
    }

    @Override
    public LeaveEvent serve(PQ<Server> pqServer) {
        return new LeaveEvent(this.getCustomer(), pqServer);
    }

    @Override
    public boolean hasNextEvent() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("%.3f " +  this.getCustomer().toString() + " leaves", this.getTime());
    }
}

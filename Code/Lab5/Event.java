abstract class Event {
  
    private final Customer customer;
    private final double time;

    Event(Customer customer) {
        this.customer = customer;
        time = 0;
    }

    Event(Customer customer, double time) {
        this.customer = customer;
        this.time = time;
    }

    protected Pair<Integer,Integer> updateList(Pair<Integer, Integer> list) {
        return list;
    }
    
    public int getCustomerId() {
        return this.getCustomer().getId();
    }

    public double getTime() {
        return this.time;
    }

    protected Customer getCustomer() {
        return this.customer;
    }

    protected Pair<PQ<Event>, ServerList> update(ServerList sList, PQ<Event> pq) {
        return new Pair<PQ<Event>, ServerList>(pq, sList);
    }

    protected double updateWait(double time) {
        return time;
    }

    protected int updateServe(int serve) {
        return serve;
    }

    protected int updateLeave(int leave) {
        return leave;
    }

}

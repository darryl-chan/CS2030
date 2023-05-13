class LeaveEvent extends Event {

    LeaveEvent(Customer customer, double time) {
        super(customer, time);
    }

    public Pair<PQ<Event>, ServerList> update(ServerList sList, PQ<Event> pq) {
        Pair<PQ<Event>, ServerList> pair = new Pair<PQ<Event>, ServerList>(pq, sList);
        return pair;
    }

    @Override
    protected int updateLeave(int leave) {
        return leave + 1;
    }

    @Override
    public String toString() {
        String str1 = String.format("%.3f " +  this.getCustomer().toString(), this.getTime());
        String str2 = " leaves\n";
        return str1 + str2;
    }
}

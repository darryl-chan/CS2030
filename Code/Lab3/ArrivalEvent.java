class ArrivalEvent extends Event {

    ArrivalEvent(Customer customer, double time) {
        super(customer, time);
    }

    @Override
    protected Pair<PQ<Event>, ServerList> update(ServerList sList, PQ<Event> pq) {
        Pair<Server, ServerList> p;
        Pair<PQ<Event>, ServerList> pqESL;

        if (sList.currAvailable()) {
            p = sList.serve(this.getCustomer()); 
            Event event = new ServeEvent(this.getCustomer(), this.getTime(), p.first());
            pq = pq.add(event);
            pqESL = new Pair<PQ<Event>, ServerList>(pq, p.second());
            return pqESL;

        } else if (sList.waitAvailable()) {
            p = sList.serve(this.getCustomer()); 
            Event event = new WaitEvent(this.getCustomer(), this.getTime(), p.first());
            pq = pq.add(event);
            pqESL = new Pair<PQ<Event>, ServerList>(pq, p.second());
            return pqESL;

        } else {
            Event event = new LeaveEvent(this.getCustomer(), this.getTime());
            pq = pq.add(event);
            pqESL = new Pair<PQ<Event>, ServerList>(pq, sList);
            return pqESL;
        }
    }

    @Override
    public String toString() {
        String str1 = String.format("%.3f " +  this.getCustomer().toString(), this.getTime());
        String str2 = " arrives";
        return str1 + str2 + "\n";
    }
}

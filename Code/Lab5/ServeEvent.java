class ServeEvent extends Event {
    
    private final Server server;
    private final double wait;

    ServeEvent(Customer customer, double time, Server server) {
        super(customer, time);
        this.server = server;
        this.wait = 0;
    }

    ServeEvent(Customer customer, double time, Server server, double wait) {
        super(customer, time);
        this.server = server;
        this.wait = wait;
    }
    
    private Server getServer() {
        return this.server;
    } 
    
    @Override
    protected Pair<PQ<Event>, ServerList> update(ServerList sList, PQ<Event> pq) {
        double doneTime = this.getCustomer().getServiceTime() + this.getTime();
        sList = sList.updateTime(this.getServer(), doneTime);

        Event event = new DoneEvent(this.getCustomer(), doneTime, this.getServer(), this.wait);
        pq = pq.add(event);
        Pair<PQ<Event>, ServerList> pair = new Pair<PQ<Event>, ServerList>(pq, sList);
        return pair;
    }

    @Override
    public String toString() {
        String str1 = String.format("%.3f " +  this.getCustomer().toString(), this.getTime());
        String str2 = " serves by " + this.getServer().toString();
        return str1 + str2 + "\n";
    }
}

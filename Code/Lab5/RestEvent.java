class RestEvent extends Event {

    private final Server server;
    private final double wait;

    RestEvent(Customer customer, double time, Server server, double wait) {
        super(customer, time);
        this.server = server;
        this.wait = wait;
    }

    public Server getServer() {
        return this.server;
    }

    @Override
    protected Pair<PQ<Event>, ServerList> update(ServerList sList, PQ<Event> pq) {
        //double restTime = this.server.getRestTime();
        sList = sList.returnServer(this.getServer());
        Pair<PQ<Event>, ServerList> pair = new Pair<PQ<Event>, ServerList>(pq, sList);
        return pair;
    }

    @Override
    public String toString() {
        return "";
    }
 
}



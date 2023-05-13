class WaitEvent extends Event {

    private final Server server;
    private final boolean print;
    private final double initial;

    WaitEvent(Customer customer, double time, Server server) {
        super(customer, time);
        this.server = server;
        this.print = true;
        this.initial = time;
    }

    WaitEvent(Customer customer, double time, Server server, boolean print, double initial) {
        super(customer, time);
        this.server = server;
        this.print = false;
        this.initial = initial;
    }

    public Server getServer() {
        return this.server;
    }

    @Override
    public String toString() {
        if (this.print) {
            String str1 = String.format("%.3f " +  this.getCustomer().toString(), this.getTime());
            String str2 = " waits at " + this.getServer().toString();
            return str1 + str2 + "\n";
        } else {
            return "";
        }
    }

    @Override
    protected Pair<PQ<Event>, ServerList> update(ServerList sList, PQ<Event> pq) {
        double time = sList.getAvailable(this.getServer());
        if (time == this.getTime()) {
            double wait = time - this.initial;
            Event event = new ServeEvent(this.getCustomer(), time, this.getServer(), wait);

            pq = pq.add(event);
            return new Pair<PQ<Event>, ServerList>(pq, sList);
        } else {
            double ini = this.initial;
            Event event = new WaitEvent(this.getCustomer(), time, this.getServer(), false, ini);
            pq = pq.add(event);
            return new Pair<PQ<Event>, ServerList>(pq, sList);
        }
    }
}

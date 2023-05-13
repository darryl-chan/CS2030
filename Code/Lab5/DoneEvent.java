class DoneEvent extends Event {
    
    private final Server server;
    private final double wait;

    DoneEvent(Customer customer, double time, Server server, double wait) {
        super(customer, time);
        this.server = server;
        this.wait = wait;
    }

    private Server getServer() {
        return this.server;
    }
    
    
    @Override
    protected double updateWait(double time) {
        return this.wait + time;
    }
    

    @Override
    protected int updateServe(int serve) {
        return serve + 1;
    }

    @Override
    protected Pair<PQ<Event>, ServerList> update(ServerList sList, PQ<Event> pq) {
        double restTime = this.getServer().getRestTime();
        //System.out.println("Rest Time: " + restTime);
        double finishRestTime = restTime + this.getTime();
        sList = sList.updateTime(this.getServer(), finishRestTime);
        //sList = sList.returnServer(this.getServer());
        
        Event event = new RestEvent(this.getCustomer(), finishRestTime,
                this.getServer(), this.wait);
        pq = pq.add(event);
        Pair<PQ<Event>, ServerList> pair = new Pair<PQ<Event>, ServerList>(pq, sList);
        return pair;
    }

    /*
    @Override
    protected Pair<PQ<Event>, ServerList> update(ServerList sList, PQ<Event> pq) {
        double restTime = this.server.getRestTime();
        sList = sList.returnServer(this.getServer());
        Pair<PQ<Event>, ServerList> pair = new Pair<PQ<Event>, ServerList>(pq, sList);
        return pair;
    }
    */

    @Override
    public Pair<Integer, Integer> updateList(Pair<Integer, Integer> list) {
        return new Pair<Integer, Integer>(list.first() + 1, list.second());
    }

    @Override
    public String toString() {
        String str1 = String.format("%.3f " +  this.getCustomer().toString(), this.getTime());
        String str2 = " done serving by " + this.getServer().toString();
        return str1 + str2 + "\n";
    }
}

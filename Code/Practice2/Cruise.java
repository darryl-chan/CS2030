class Cruise {

    private final String id;
    private final Time arrival;
    private final int service;
    private final int loaders;

    Cruise(String id, int arrival, int loaders, int service) {
        this.id = id;
        this.service = service;
        this.loaders = loaders;
        this.arrival = new Time(arrival);
    }

    @Override
    public String toString() {
        return this.getId() + "@" + String.format("%04d", this.getArrival().getTime()); 
    }

    protected String getId() {
        return this.id;
    }

    protected Time getArrival() {
        return this.arrival;
    }

    protected int getService() {
        return this.service;
    }

    protected int getLoaders() {
        return this.loaders;
    }

    public int getArrivalTime() {
        return this.getArrival().getMinutesAfterMidnight();
    }

    public int getNumOfLoadersRequired() {
        return this.getLoaders();
    }

    public int getServiceTime() {
        return this.getService();
    }

    public int getServiceEndTime() {
        return this.getArrivalTime() + this.getService();
    }

}


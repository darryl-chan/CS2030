class Service {
    
    private final Loader loader;
    private final Cruise cruise;

    Service(Loader loader, Cruise cruise) {
        this.loader = loader;
        this.cruise = cruise;
    }

    @Override
    public String toString() {
        return this.getLoader().toString() + " serving " + this.getCruise().toString();
    }

    public Loader getLoader() {
        return this.loader;
    }

    private Cruise getCruise() {
        return this.cruise;
    }
        
    public int getServiceStartTime() {
        return this.getCruise().getArrivalTime();
    }
    
    public int getServiceEndTime() {
        return this.getCruise().getServiceEndTime() + this.getLoader().getServiceTime();
    }

    public boolean retireService(int time) {
        return time >= this.getServiceEndTime();
    }

}

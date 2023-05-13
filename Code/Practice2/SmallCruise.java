class SmallCruise extends Cruise {
    
    private static final int smallCruiseServiceTime = 30;

    SmallCruise(String id, int arrival) {
        super(id, arrival, 1, 0);
    }

    @Override
    protected int getService() {
        return this.smallCruiseServiceTime;
    }
}

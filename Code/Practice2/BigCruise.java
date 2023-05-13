class BigCruise extends Cruise {
    private static final int lengthDivisor = 40;
    private static final int passengerDivisor = 50;
    private final int bigCruiseService;
    private final int bigCruiseLoaders;

    BigCruise(String id, int arrival, int length, int passengers) {
        super(id, arrival, 0, 0);

        if (length % lengthDivisor == 0) {
            this.bigCruiseLoaders = length / lengthDivisor;
        } else {
            this.bigCruiseLoaders = length / lengthDivisor + 1;
        }

        if (passengers % passengerDivisor == 0) {
            this.bigCruiseService = passengers / passengerDivisor;
        } else {
            this.bigCruiseService = passengers / passengerDivisor + 1;
        }

    }

    @Override
    protected int getService() {
        return this.bigCruiseService;
    }

    @Override
    protected int getLoaders() {
        return this.bigCruiseLoaders;
    }
     
}




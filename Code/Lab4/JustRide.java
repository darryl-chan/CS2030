class JustRide implements Service {
    
    private static final int fare = 22;
    private static final int surcharge = 500;
    private static final int peakLow = 600;
    private static final int peakHigh = 900;

    JustRide() {
    }
    
    private int calculateSurcharge(int time) {
        if (time >= this.peakLow && time <= this.peakHigh) {
            return this.surcharge;
        } else {
            return 0;
        }
    }
    
    @Override
    public int computeFare(int dist, int num, int time) {
        int currFare = dist * this.fare;
        int surchargeFare = this.calculateSurcharge(time);
        return currFare + surchargeFare;
    }

    @Override
    public String toString() {
        return "JustRide";
    }
}

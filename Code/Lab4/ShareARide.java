class ShareARide implements Service {

    private static final int fare = 50;
    private static final int surcharge = 500;
    private static final int peakLow = 600;
    private static final int peakHigh = 900;

    ShareARide() {
    }

    private int calculateSurcharge(int time) {
        if (this.peakHigh >= time && this.peakLow <= time) {
            return this.surcharge;
        } else {
            return 0;
        }
    }

    @Override
    public int computeFare(int dist, int num, int time) {
        int currFare = dist * this.fare;
        int surchargeFare = this.calculateSurcharge(time);
        return (currFare + surchargeFare) / num;
    }

    @Override
    public String toString() {
        return "ShareARide";
    }
}

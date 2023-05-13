class Booking implements Comparable<Booking> {

    private final Driver driver;
    private final int min;
    private final Request rq;
    private final Service s;
    private static final double divisor = 100;

    Booking(Driver driver, Request rq) {
        this.driver = driver;
        this.rq = rq;
        this.min = driver.getMin();
        this.s = driver.findService(rq);
    }

    Booking(Driver driver, Request rq, Service s) {
        this.driver = driver;
        this.rq = rq;
        this.min = driver.getMin();
        this.s = s;
    }

        
    int getFare() {
        return this.rq.computeFare(this.s);
    }

    @Override
    public int compareTo(Booking b) {
        if (this.getFare() != b.getFare()) {
            return this.getFare() - b.getFare();
        } else {
            return this.min - b.min;
        }
    }

    @Override
    public String toString() {
        double dollar = Double.valueOf(this.getFare()) / this.divisor;
        String drive = this.driver.toString();
        String service = this.s.toString();
        return String.format("$%.2f using %s (%s)", dollar, drive, service);
    }
}


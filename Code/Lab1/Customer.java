class Customer {

    private final int id;
    private final double arrival;
    private final double exit;

    public Customer(int id, double arrival, double exit) {
        this.id = id;
        this.arrival = arrival;
        this.exit = exit;
    }

    private int getId() {
        return this.id;
    }

    private double getArrival() {
        return this.arrival;
    }

    private double getExit() {
        return this.exit;
    }

    public boolean shouldLeave(double time) {
        if (time >= this.getExit()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "customer " + this.getId();
    }
}



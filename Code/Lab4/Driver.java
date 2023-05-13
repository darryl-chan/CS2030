abstract class Driver {

    private final String name;
    private final int min;

    Driver(String name, int min) {
        this.name = name;
        this.min = min;
    }

    public int getMin() {
        return this.min;
    }

    public ImList<Service> getSList() {
        return new ImList<Service>();
    }

    @Override 
    public String toString() {
        return String.format("%s (%d mins away) ", this.name, this.min);
    }

    public Service findService(Request rq) {
        return new JustRide();
    }
}

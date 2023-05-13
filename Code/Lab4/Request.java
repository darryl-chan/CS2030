class Request {
    
    private final int dist;
    private final int pax;
    private final int time;

    Request(int dist, int pax, int time) {
        this.dist = dist;
        this.pax = pax;
        this.time = time;
    }

    int computeFare(Service s) {
        return s.computeFare(this.dist, this.pax, this.time);
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", this.dist, this.pax, this.time);
    }
}

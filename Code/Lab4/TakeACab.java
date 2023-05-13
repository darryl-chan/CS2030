class TakeACab implements Service {

    private static final int fare = 33;
    private static final int bookFee = 200;

    TakeACab() {
    }
    
    @Override
    public int computeFare(int dist, int pax, int time) {
        return this.bookFee + this.fare * dist;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}

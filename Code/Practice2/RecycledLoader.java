class RecycledLoader extends Loader {

    private static final int serviceTime = 60;

    RecycledLoader(int id) {
        super(id);
    }

    @Override
    public int getServiceTime() {
        return this.serviceTime;
    }
    
    @Override
    public String toString() {
        return "Recycled " + super.toString();
    }

}

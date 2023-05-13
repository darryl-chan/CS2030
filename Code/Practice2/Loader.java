class Loader {

    private final int id;

    Loader(int id) {
        this.id = id;
    }

    private int getId() {
        return this.id;
    }

    public int getServiceTime() {
        return 0;
    }

    @Override
    public String toString() {
        return "Loader #" + this.getId();
    }
}

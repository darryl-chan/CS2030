class Server {
    
    private final int id;
    
    public int getId() {
        return this.id;
    }

    Server(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "" + this.getId();
    }
}



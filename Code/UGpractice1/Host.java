abstract class Host {
    
    private final String id;
    

    Host(String id) {
        this.id = id;
    }

    boolean finish() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Host) {
            Host curr = o;
            return this.id == curr.id;
        } else {
            return false;
        }
    }

    @Override
    public boolean 


}

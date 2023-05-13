abstract class Terminal {

    private final String id;

    Terminal(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Terminal) {
            Terminal curr = o;
            return this.id == curr.id;
        } else {
            return false;
        }
    }
    
}

class PCR extends Test {

    PCR(String test) {
        super(test);
    }

    @Override
    public boolean isPositive() {
        String test = this.getTest();
        if (test == "alpha" || test == "beta" || test == "delta" || test == "omicron") {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (this.isPositive()) {
            return "P+";
        } else {
            return "P-";
        }
    }
}



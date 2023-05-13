class ART extends Test {

    ART(String test) {
        super(test);
    }

    @Override
    public boolean isValid() {
        return this.getTest() != "T";
    }

    @Override
    public boolean isPositive() {
        if (this.getTest() == "CT") {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (!this.isValid()) {
            return "AX";
        } else if (this.isPositive()) {
            return "A+";
        } else {
            return "A-";
        }
    }
}


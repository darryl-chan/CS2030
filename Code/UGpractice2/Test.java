abstract class Test {

    private final String test;

    Test(String test) {
        this.test = test;
    }

    boolean isValid() {
        return true;
    }

    boolean isPositive() {
        return true;
    }

    public String getTest() {
        return this.test;
    }
}


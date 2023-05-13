class P3 implements Protocol {

    private static final int dischargeDays = 5;

    P3() {
    }

   
    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isPositive()) {
            Protocol p = new P1().next(person, test, numOfDays);
            return p;
        } else if (numOfDays <= dischargeDays) {
            return new P3();
        } else {
            return new DischargedComplete();
        }
    }

    @Override
    public String toString() {
        return "P3";
    } 

}



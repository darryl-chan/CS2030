class P2 implements Protocol {

    private final Person person;
    private final Test test;
    private final int days;

    private static final int dischargeOne = 3;
    private static final int dischargeTwo = 7;
    private static final int dischargeThree = 14;

    P2(Person person, Test test, int days) {
        this.person = person;
        this.test = test;
        this.days = days;
    }

    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (this.isDischarged(test, numOfDays)) {
            return new DischargedComplete();
        } else {
            return new P2(person, test, numOfDays);
        }
    }

    boolean isDischarged(Test test, int days) {
        boolean vaccinated = this.person.isVaccinated();
        boolean positive = test.isPositive();
        
        if (days <= this.dischargeOne) {
            return false;
        } else if (days <= this.dischargeTwo && vaccinated && positive) {
            return false;
        } else if (days <= this.dischargeThree && !vaccinated && positive) {
            return false;
        } else {
            return true;
        }
    }

    @Override 
    public String toString() {
        return "P2";
    }

}

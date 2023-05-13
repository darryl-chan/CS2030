class P1 implements Protocol {
    
    P1() {
    }

    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isPositive() && person.isHighRisk()) {
            return new P1();
        } else if (test.isPositive() && !person.isHighRisk()) {
            return new P2(person, test, numOfDays);
        } else {
            return new Discharge(test);
        }
    }
    
    @Override
    public String toString() {
        return "P1";
    }
}



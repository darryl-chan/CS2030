class DischargedComplete implements Protocol {

    DischargedComplete() {
    }

    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isPositive()) {
            return new Discharge(test);
        } else {
            return new DischargedComplete();
        }
    }

    @Override
    public String toString() {
        return "Discharged: complete";
    }
}



class Discharge implements Protocol {

    private static final String followUp = "Discharged: follow up with doctor";
    private static final String seek = "Discharged: seek medical attention";
    private final boolean seekMedical;

    Discharge(Test test) {
        if (test.isPositive()) {
            this.seekMedical = true;
        } else {
            this.seekMedical = false;
        }
    }

    Discharge() {
        this.seekMedical = true;
    }


    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (this.seekMedical) {
            return new Discharge();
        } else {
            return new Discharge(test);
        }
    }

    @Override
    public String toString() {
        return this.seekMedical ? this.seek : this.followUp;
    }
}

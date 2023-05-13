class Person {

    private final String id;
    private final String status;
    private final boolean highrisk;
    
    private static final int numVaccination = 2;
    private static final String lowRisk = "LOW";
    private static final String highRisk = "HIGH";
    private static final int riskLevel = 8;

    Person(String id, String status, int risk) {
        this.id = id;
        this.status = status;
        
        if (risk >= this.riskLevel) {
            this.highrisk = true;
        } else {
            this.highrisk = false;
        }
    }

    boolean isVaccinated() {
        return this.status.length() >= this.numVaccination;
    }

    boolean isHighRisk() {
        return this.highrisk;
    }

    @Override
    public String toString() {
        String risk;

        if (this.highrisk) {
            risk = this.highRisk;
        } else {
            risk = this.lowRisk;
        }
        
        return String.format("%s/%s/%s", this.id, this.status, risk);
    }
}


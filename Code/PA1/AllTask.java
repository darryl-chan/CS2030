class AllTask extends ParentTask implements DiscreteTask {

    private final int day;
    private final int start;
    private final int end;
    private final String task;

    AllTask(int day, int start, int end, String task) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.task = task;
    }

    public int getDay() {
        return this.day;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }
    
    public String getTask() {
        return this.task;
    }

    public AllTask allCancel() {
        return this;
    }

    @Override
    public String toString() {
        return String.format("Task: %d,%d,%d,%s", this.day, this.start, this.end, this.task);
    }
}

   

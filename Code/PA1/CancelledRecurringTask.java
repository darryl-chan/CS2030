class CancelledRecurringTask extends ParentTask {

    private final AllTask task;

    CancelledRecurringTask(AllTask task) {
        this.task = task.allCancel();
    }

    @Override
    public String toString() {
        return String.format("Recurring %s", this.task.toString());
    }
}


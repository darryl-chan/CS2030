class CancelledTask extends AllTask {

    CancelledTask(int day, int start, int end, String task) {
        super(day, start, end, task);
    }

    @Override
    public String toString() {
        return String.format("%s[cancelled]",super.toString());
    }
}


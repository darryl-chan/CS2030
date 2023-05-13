class RecurringTask extends ParentTask {

    private final Task task;
    private final ImList<AllTask> events;
    private final int recur;
    private final int times;

    RecurringTask(Task task, int recur, int times) {

        ImList<AllTask> events = new ImList<AllTask>();
        int day = task.getDay();

        for (int i = 0; i < times; i++) {
            events = events.add(task.editDay(day + recur * i));
        }

        this.task = task;
        this.events = events;
        this.recur = recur;
        this.times = times;
    }

    RecurringTask(Task task, int recur, int times, ImList<AllTask> events) {
        this.events = events;
        this.task = task;
        this.recur = recur;
        this.times = times;
    }

    public CancelledRecurringTask cancel() {
        return new CancelledRecurringTask(this.task);
    }

    public RecurringTask edit(int start, int end) {
        return new RecurringTask(this.task.edit(start, end), this.recur, this.times);
    }

    public RecurringTask edit(int index, int day, int start, int end) {

        ImList<AllTask> events = this.events;
        AllTask curr = this.task.fullEdit(day, start, end);
        events = events.set(index - 1, curr);
        events = events.sort(new TaskComparator());
        return new RecurringTask(this.task, this.recur, this.times, events);
    }

    public RecurringTask cancel(int index) {
        ImList<AllTask> events = this.events;
        AllTask curr = events.get(index - 1);
        curr = curr.allCancel();
        events = events.set(index - 1, curr);
        return new RecurringTask(this.task, this.recur, this.times, events);
    }


    @Override
    public String toString() {
        String finalString = String.format("Recurring %s\n", this.task.toString());
        for (int i = 1; i <= times; i++) {
            if (i == times) {
                finalString += String.format("#%d:%s", i, this.events.get(i-1).toString());
            } else {
                finalString += String.format("#%d:%s\n", i, this.events.get(i-1).toString());
            }
        }
        return finalString;
    }

    @Override
    public ImList<DiscreteTask> findTask(ImList<DiscreteTask> currEvents , int day) {
        for (AllTask at : this.events) {
            currEvents = at.findTask(currEvents, day);
        }
        return currEvents;
    }
}



class Task extends AllTask{

    Task(int day, int start, int end, String task) {
        super(day, start, end, task);
    }

    public Task edit(int start, int end) {
        return new Task(this.getDay(), start, end, this.getTask());
    }

    public Task editDay(int day) {
        return new Task(day, this.getStart(), this.getEnd(), this.getTask());
    }

    public Task fullEdit(int day, int start, int end) {
        Task currTask = this.editDay(day);
        currTask = currTask.edit(start, end);
        return currTask;
    }

    @Override
    public AllTask allCancel() {
        return this.cancel();
    }

    public CancelledTask cancel() {
        return new CancelledTask(this.getDay(), this.getStart(), this.getEnd(), this.getTask());
    }

    @Override
    public ImList<DiscreteTask> findTask(ImList<DiscreteTask> currEvents , int day) {
        if (this.getDay() == day) {
            currEvents = currEvents.add(this);
        }
        return currEvents;
    }
}


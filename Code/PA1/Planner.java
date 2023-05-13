class Planner {

    private final ImList<ParentTask> events;

    Planner() {
        this.events = new ImList<ParentTask>();
    }

    Planner(ImList<ParentTask> events) {
        this.events = events;
    }

    Planner add(ParentTask task){
        ImList<ParentTask> events = this.events;
        events = events.add(task);
        return new Planner(events);
    }

    Planner cancel() {
        return new Planner();
    }

    void view(View v) {
        v.view(this.events);
    }

    @Override
    public String toString() {
        String finalString = "\n";

        for (int i = 0; i < this.events.size(); i++) {
            ParentTask pt = this.events.get(i);
            if (i == this.events.size() - 1) {
                finalString += pt.toString();
            } else {
                finalString += pt.toString() + "\n";
            }
        }
        return finalString;
    }
}

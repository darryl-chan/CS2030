class DayView implements View {

    private final int day;

    DayView(int day) {
        this.day = day;
    }

    @Override
    public void view(ImList<ParentTask> tasks) {
        ImList<DiscreteTask> all = new ImList<DiscreteTask>();
        
        for (ParentTask pt : tasks) {
            all = pt.findTask(all, this.day);
        }

        all = all.sort(new TaskComparator());
        
        for (DiscreteTask dt: all) {
            System.out.println(dt.toString());
        }
    }

}

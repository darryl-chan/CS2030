import java.util.Comparator;

class TaskComparator implements Comparator<DiscreteTask> {

    public int compare(DiscreteTask a1, DiscreteTask a2) {
        if (a1.getDay() != a2.getDay()) {
            return a1.getDay() - a2.getDay();
        } else {
            return a1.getStart() - a2.getStart();
        }
    }
}

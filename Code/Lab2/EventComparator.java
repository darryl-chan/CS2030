import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    
    public int compare(Event e1, Event e2) {
        if (e1.getTime() < e2.getTime()) {
            return -1;
        } else if (e1.getTime() > e2.getTime()) {
            return 1;
        } else if (e1.getPriority() != e2.getPriority()) {
            return e1.getPriority() - e2.getPriority();
        } else { 
            return e1.getCustomerId() - e2.getCustomerId();                    
        }
    }
}

import java.util.Comparator;

class ServerComparator implements Comparator<Server> {

    public int compare(Server s1, Server s2) {
        return s1.getId() - s2.getId();
    }
}


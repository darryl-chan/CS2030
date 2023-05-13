import java.util.function.Supplier;

class Simulator {

    private final int numOfServers;
    private final int selfCheck;
    private final int qmax;
    private final ImList<Double> arrival;
    private final Supplier<Double> dst;
    private final Supplier<Double> rest;

    Simulator(int n, int s,int qmax, ImList<Double> a, Supplier<Double> dst, Supplier<Double> r) {
        this.numOfServers = n;
        this.selfCheck = s;
        this.qmax = qmax;
        this.arrival = a;
        this.dst = dst;
        this.rest = r;
    }
    
    public String simulate() {
        String finalString = "";
        PQ<Event> pqEvent = new PQ<Event>(new EventComparator());
        ServerList sList = new ServerList(this.numOfServers, this.selfCheck, this.qmax, this.rest);

        for (int i = 0; i < this.arrival.size(); i++) {
            double arrive = this.arrival.get(i);
            Customer newCustomer = new Customer(i + 1, dst);
            pqEvent = pqEvent.add(new ArrivalEvent(newCustomer, arrive));
        }
        
        Pair<Event, PQ<Event>> pair1;
        Pair<PQ<Event>, ServerList> pair2;

        double wait = 0;
        int served = 0;
        int leave = 0;
        
        while (!pqEvent.isEmpty()) {
            pair1 = pqEvent.poll();
            Event currEvent = pair1.first();
            pqEvent = pair1.second();
            finalString += currEvent.toString();
            
            pair2 = currEvent.update(sList, pqEvent);

            sList = pair2.second();
            pqEvent = pair2.first();

            wait = currEvent.updateWait(wait);
            served = currEvent.updateServe(served);
            leave = currEvent.updateLeave(leave);

        }
        String str1 = finalString;
        String str2;
        if (served == 0) {
            str2 = String.format("[%.3f %d %d]", wait, served, leave);
        } else {
            str2 = String.format("[%.3f %d %d]", wait / served, served, leave);
        }
        return str1 + str2;
    }

}
           

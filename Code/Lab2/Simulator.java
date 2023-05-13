class Simulator {

    private final int numOfServers;
    private final ImList<Double> arrivalTimes;
    private final ImList<Double> serviceTimes;

    Simulator(int numOfServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
        this.numOfServers = numOfServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
    }
    
    public String simulate() {
        String finalString = "";
        Pair<Integer,Integer> finalList = new Pair<Integer,Integer>(0,0);        
        PQ<Event> pqEvent = new PQ<Event>(new EventComparator());
        PQ<Server> pqServer = new PQ<Server>(new ServerComparator());

        for (int i = 0; i < this.arrivalTimes.size(); i++) {
            double arrive = this.arrivalTimes.get(i);
            double leave = this.serviceTimes.get(i) + arrive;
            Customer newCustomer = new Customer(i + 1, arrive, leave);
            pqEvent = pqEvent.add(new ArrivalEvent(newCustomer));
        }

        for (int i = 1; i <= this.numOfServers; i++) {
            pqServer = pqServer.add(new Server(i));
        }

        while (!pqEvent.isEmpty()) {
            Pair<Event, PQ<Event>> pair = pqEvent.poll();
            Event currEvent = pair.first();
            pqEvent = pair.second();
            finalString += currEvent.toString() + "\n";

            Event updated = currEvent.serve(pqServer);
            pqServer = updated.updateServer();
           
            if (updated.hasNextEvent()) {
                updated = updated.updateEvent();
                pqEvent = pqEvent.add(updated);
            } else {
                finalList = updated.updateList(finalList);
            }
        }
        return finalString + String.format("[%d %d]",finalList.first(), finalList.second());
    }

}
           

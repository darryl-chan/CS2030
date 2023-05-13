class Shop { 
    private final ImList<Double> arrival;
    private final ImList<Double> end;
    private final ImList<Server> server;

    Shop(int servers, ImList<Double> arrival, ImList<Double> end) {
        this.arrival = arrival;
        this.end = end;
        ImList<Server> server = new ImList<>();

        for (int i = 1; i <= servers; i++) {
            server = server.add(new Server(i));
        }

        this.server = server;
    }

    public ImList<Double> getArrival() {
        return this.arrival;
    }

    public ImList<Double> getEnd() {
        return this.end;
    }

    public ImList<Server> getServer() {
        return this.server;
    }
    
    @Override
    public String toString() {
        ImList<Server> server = this.getServer();
        ImList<Double> arrival = this.getArrival();
        ImList<Double> end = this.getEnd();
        String finalString = "";
        ImList<Integer> customerServed = new ImList<Integer>();
        customerServed = customerServed.add(Integer.valueOf(0)).add(Integer.valueOf(0));

        for (int i = 0; i < arrival.size(); i++) {
            double startTime = arrival.get(i).doubleValue();
            double endTime = end.get(i).doubleValue();
            String stringStartTime = String.format("%.3f", startTime);

            Customer customer = new Customer(i + 1, startTime, endTime + startTime);
            finalString += stringStartTime + " " + customer.toString() + " arrives\n";

            boolean served = false;

            for (int j = 0; j < server.size(); j++) {
                Server currServer = server.get(j);
                server = server.set(j, currServer.service(startTime));
            }

            for (int j = 0; j < server.size(); j++) {
                Server currServer = server.get(j);
                if (currServer.isAvailable()) {
                    server = server.set(j, currServer.serveCustomer(customer));
                    Integer updated = Integer.valueOf(customerServed.get(0).intValue() + 1);
                    customerServed = customerServed.set(0, updated);
                    finalString += stringStartTime + " " + customer.toString();
                    finalString += " served by " + currServer.toString() + "\n";
                    served = true;
                    break;
                }

            }
            if (!served) {
                Integer updated = Integer.valueOf(customerServed.get(1).intValue() + 1);
                customerServed = customerServed.set(1, updated);
                finalString += stringStartTime + " " + customer.toString() + " leaves\n";
            }
        }
        finalString += "[" + customerServed.get(0).toString() + " ";
        finalString += customerServed.get(1).toString() + "]";
        return finalString;
    }
}


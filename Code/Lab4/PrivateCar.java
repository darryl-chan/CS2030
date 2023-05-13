class PrivateCar extends Driver {

    private final ImList<Service> pcList;

    PrivateCar(String name, int min) {
        super(name, min);
        ImList<Service> pcList = new ImList<Service>().add(new JustRide()).add(new ShareARide());
        this.pcList = pcList;
    }

    @Override
    public ImList<Service> getSList() {
        return this.pcList;
    }

    @Override
    public Service findService(Request rq) {
        ImList<Service> sList = this.getSList(); 

        int minFare = rq.computeFare(sList.get(0));
        int index = 0;
        
        for (int i = 1; i < sList.size(); i++) {
            if (minFare > rq.computeFare(sList.get(i))) {
                minFare = rq.computeFare(sList.get(i));
                index = i;
            }
        }
        return sList.get(index);
    }

    @Override
    public String toString() {
        return super.toString() + "PrivateCar";
    }
}

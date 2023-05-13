ImList<Service> serveCruises(ImList<Cruise> cruises) {
  
    ImList<Service> active = new ImList<Service>();
    ImList<Service> expired = new ImList<Service>();
    ImList<Service> finalList = new ImList<Service>();

    int loaderCounter = 1;

    for (Cruise c : cruises) {
        int arrivalTime = c.getArrivalTime();
       
        boolean transfer = !active.isEmpty();
        while (transfer) {
            transfer = false;
            for (int j = 0; j < active.size(); j++) {
                if (active.get(j).retireService(arrivalTime)) {
                    expired = expired.add(active.get(j));
                    active = active.remove(j);
                    transfer = true;
                    break;

                }
            }
        }

        for (int i = 0; i < c.getNumOfLoadersRequired(); i++) {

            if (expired.isEmpty()) {
                Loader loader = new Loader(loaderCounter);
                if (loaderCounter % 3 == 0) {
                    loader = new RecycledLoader(loaderCounter);
                }

                loaderCounter += 1;
                Service newService = new Service(loader, c);
                active = active.add(newService);
                finalList = finalList.add(newService);
            } else {
                Service expiredService = expired.get(0);
                Loader loader = expiredService.getLoader();
                Service newService = new Service(loader, c);
                active = active.add(newService);
                finalList = finalList.add(newService);
                expired = expired.remove(0);
            }
        }

    }
    return finalList;
}

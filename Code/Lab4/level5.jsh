void findBestBooking(Request rq, List<Driver> list) {
    ImList<Booking> booking = new ImList<Booking>();
   
    for (Driver d : list) {
        ImList<Service> service = d.getSList();
        for (Service s : service) {
            booking = booking.add(new Booking(d, rq, s));
        }
    }

    booking = booking.sort(new BookingComparator());

    for (Booking b : booking) {
        System.out.println(b.toString());
    }
}


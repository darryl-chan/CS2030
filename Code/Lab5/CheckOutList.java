import java.util.function.Supplier;

class CheckOutList {

    private final ImList<Server> servers;
    private final int qmax;
    private final int q;
    private final int offset;

    CheckOutList(int numServers, int qmax, int offset, Supplier<Double> rest) {
        this.qmax = qmax;
        this.offset = offset;
        this.q = 0;
        ImList<Server> servers = new ImList<Server>();

        for (int i = 1; i <= numServers; i++) {
            servers = servers.add(new CheckOut(i + offset, 1, rest));
        }
        this.servers = servers;
    }

    CheckOutList(ImList<Server> servers, int qmax, int q, int offset) {
        this.servers = servers;
        this.qmax = qmax;
        this.q = q;
        this.offset = offset;
    }

    public CheckOutList createCList(ImList<Server> servers) {
        return new CheckOutList(servers, this.qmax, this.q, this.offset);
    }

    public CheckOutList checkWait() {
        return new CheckOutList(servers, this.qmax, this.q + 1, this.offset);
    }

    public CheckOutList doneServe() {
        return new CheckOutList(servers, this.qmax, this.q - 1, this.offset);
    }

    public int getQ() {
        return this.q;
    }

    public CheckOutList updateServer(Server s) {
        ImList<Server> servers = this.servers;
        int index = s.getId() - this.offset - 1;
        servers = servers.set(index, s);
        return this.createCList(servers);
    }

    public CheckOutList returnServer(Server s) {
        ImList<Server> servers = this.servers;
        int index = s.getId() - this.offset - 1;
        Server curr = servers.get(index);
        curr = curr.returnServer();
        servers = servers.set(index, curr);
        return this.createCList(servers);
    }

    public Pair<Server, CheckOutList> verify(Server s, double time) {
        ImList<Server> servers = this.servers;
        for (Server c: this.servers) {
            if (c.getAvailable() <= time) {
                c = c.serve();
                int index = c.getId() - this.offset - 1;
                servers = servers.set(index, c);
                CheckOutList cOut = this.createCList(servers);
                cOut = cOut.doneServe();
                return new Pair<Server, CheckOutList>(c, cOut);
            }
        }
        return new Pair<Server, CheckOutList>(s, this);
    }

        
    public CheckOutList updateTime(Server s, double time) {
        ImList<Server> servers = this.servers;
        int index = s.getId() - this.offset - 1;
        s = servers.get(index);
        s = s.updateTime(time);
        servers = servers.set(index, s);
        return this.createCList(servers);
    }

    public double getAvailable(Server s) {
        ImList<Server> servers = this.servers;
        double min = servers.get(0).getAvailable();
        for (Server c: servers) {
            if (c.getAvailable() < min) {
                min = c.getAvailable();
            }
        }
        return min;
    }



    public boolean currAvailable(double time) {
        if (this.servers.size() == 0) {
            return false;
        }
        for (Server c: this.servers) {
            if (c.currAvailable(time)) {
                return true;
            }
        }
        return false;
    }

    public boolean waitAvailable() {

        if (this.servers.size() == 0 || this.q == this.qmax) {
            return false;
        } else {
            return true;
        }
    }

    public Pair<Server, CheckOutList> serve(Customer c, double time) {
        
        ImList<Server> servers = this.servers;
        Server s = servers.get(0);
        CheckOutList cout = this.checkWait();

        if (this.currAvailable(time)) {
            for (int i = 0; i < this.servers.size(); i++) {
                s = this.servers.get(i);
                if (s.currAvailable(time)) {
                    s = s.serve(c);
                    servers = servers.set(i, s);
                    cout = createCList(servers);
                    break;
                }
            }
            return new Pair<Server, CheckOutList>(s, cout); 
        } else {
            return new Pair<Server, CheckOutList>(s, cout);
        }
    }
}






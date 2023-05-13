import java.util.function.Supplier; 

class ServerList {

    private final ImList<Server> servers;
    private final CheckOutList clist;

    ServerList(int numOfServers, int selfCheck, int qmax, Supplier<Double> rest) {
        ImList<Server> servers = new ImList<Server>();
        for (int i = 1; i <= numOfServers; i++) {
            servers = servers.add(new Server(i, qmax + 1, rest));
        }

        this.clist = new CheckOutList(selfCheck, qmax, numOfServers, rest);
        this.servers = servers;
    }


    ServerList(ImList<Server> servers, CheckOutList clist) {
        this.servers = servers;
        this.clist = clist;

    }

    public ServerList createSList(ImList<Server> servers, CheckOutList clist) {
        return new ServerList(servers, clist);
    }

    public ServerList createSList(ImList<Server> servers) {
        return new ServerList(servers, this.clist);
    }

    public ServerList createSList(CheckOutList clist) {
        return new ServerList(this.servers, clist);
    }

    public Pair<Server, ServerList> verify(Server s, double time) {
        int serverId = s.getId();
        if (serverId <= this.servers.size()) {
            return new Pair<Server, ServerList>(s, this);
        } else {
            Pair<Server, CheckOutList> p1 = this.clist.verify(s, time);
            ServerList sList = this.createSList(p1.second());
            return new Pair<Server, ServerList>(p1.first(), sList);
        }
    }

    public ServerList humanReturnServer(Server s) {
        ImList<Server> servers = this.servers;
        int index = s.getId() - 1;
        Server currServer = servers.get(index);
        currServer = currServer.returnServer();
        servers = servers.set(index, currServer);
        return this.createSList(servers);
    }

    public ServerList returnServer(Server s) {
        int serverId = s.getId();
        if (serverId <= this.servers.size()) {
            return this.humanReturnServer(s);
        } else {
            CheckOutList cList = this.clist.returnServer(s);
            return this.createSList(cList);
        }
    }

    public boolean anyAvailable(double time) {
        return (this.currAvailable(time) || this.waitAvailable());
    }

    public boolean currAvailable(double time) {
        return (this.humanAvailable(time) || this.checkAvailable(time));
    }
    
    public boolean humanAvailable(double time) {
        for (Server s: this.servers) {
            if (s.currAvailable(time)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkAvailable(double time) {
        return this.clist.currAvailable(time);
    }

    public boolean waitAvailable() {
        return (this.humanWaitAvailable() || this.checkWaitAvailable());
    }
 
    public boolean humanWaitAvailable() {
        for (Server s: this.servers) {
            if (s.waitAvailable()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWaitAvailable() {
        return this.clist.waitAvailable();
    }

    public Pair<Server, ServerList> serve(Customer c, double time) {
        if (this.humanAvailable(time)) {
            return this.humanServe(c, time);
        } else if (this.checkAvailable(time)) {
            return this.checkServe(c, time);
        } else if (this.humanWaitAvailable()) {
            return this.humanServe(c, time);
        } else {
            return this.checkServe(c, time);
        }
    }
 
    public Pair<Server, ServerList> humanServe(Customer c, double time) {
        ImList<Server> servers = this.servers;
        Server s = servers.get(0);
        ServerList serverList = this.createSList(servers);

        if (this.currAvailable(time)) {
            for (int i = 0; i < this.servers.size(); i++) {
                s = this.servers.get(i);
                if (s.currAvailable(time)) {
                    s = s.serve(c);
                    servers = servers.set(i, s);
                    serverList = this.createSList(servers);
                    break;
                }
            }
        } else {
            for (int i = 0; i < this.servers.size(); i++) {
                s = this.servers.get(i);
                if (s.waitAvailable()) {
                    s = s.serve(c);
                    servers = servers.set(i, s);
                    serverList = this.createSList(servers);
                    break;
                }
            }
        } 
        return new Pair<Server, ServerList>(s, serverList);
    }

    public Pair<Server, ServerList> checkServe(Customer c, double time) {
        Server s;
        if (this.checkAvailable(time)) {
            Pair<Server, CheckOutList> p1 = this.clist.serve(c, time);
            ServerList sList = this.createSList(p1.second());
            return new Pair<Server, ServerList>(p1.first(), sList);
        } else {
            Pair<Server, CheckOutList> p1 = this.clist.serve(c, time);
            ServerList sList = this.createSList(p1.second());
            return new Pair<Server, ServerList>(p1.first(), sList);
        }
    }

    public ServerList updateServer(Server s) {
        int serverId = s.getId();
        if (serverId <= this.servers.size()) {
            return this.humanUpdateServer(s);
        } else {
            CheckOutList cList = this.clist.updateServer(s);
            return this.createSList(cList);
        }
    }

    public ServerList humanUpdateServer(Server s) {
        ImList<Server> servers = this.servers;
        int index = s.getId() - 1;
        servers = servers.set(index, s);
        return this.createSList(servers);
    }

    public double getAvailable(Server s) {
        int serverId = s.getId();
        if (serverId <= this.servers.size()) {
            return this.humanGetAvailable(s);
        } else {
            return this.clist.getAvailable(s);
        }
    }

    public double humanGetAvailable(Server s) {
        int index = s.getId() - 1;
        s = this.servers.get(index);
        return s.getAvailable();
    }

    public ServerList updateTime(Server s, double time) {
        int serverId = s.getId();
        if (serverId <= this.servers.size()) {
            return this.humanUpdateTime(s, time);
        } else {
            CheckOutList cList = this.clist.updateTime(s, time);
            return this.createSList(cList);
        }
    }

    public ServerList humanUpdateTime(Server s, double time) {
        ImList<Server> servers = this.servers;
        int index = s.getId() - 1;
        s = this.servers.get(index);
        s = s.updateTime(time);
        servers = servers.set(index, s);
        return this.createSList(servers);
    }

    public Server getServer(Server s) {
        int index = s.getId() - 1;
        return servers.get(index);
    }

    public Server get(int idx) {
        return servers.get(idx);
    }

    @Override
    public String toString() {
        return servers.toString();
    }

}
 

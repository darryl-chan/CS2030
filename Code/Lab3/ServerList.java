class ServerList {

    private final ImList<Server> servers;

    ServerList(int numOfServers, int qmax) {
        ImList<Server> servers = new ImList<Server>();
        for (int i = 1; i <= numOfServers; i++) {
            servers = servers.add(new Server(i, qmax + 1));
        }
        this.servers = servers;
    }

    ServerList(ImList<Server> servers) {
        this.servers = servers;
    }

    public ServerList returnServer(Server s) {
        ImList<Server> servers = this.servers;
        int index = s.getId() - 1;
        Server currServer = servers.get(index);
        currServer = currServer.returnServer();
        servers = servers.set(index, currServer);
        return new ServerList(servers);
    }

    
    public boolean currAvailable() {
        for (Server s: this.servers) {
            if (s.currAvailable()) {
                return true;
            }
        }
        return false;
    }

    public boolean waitAvailable() {
        for (Server s: this.servers) {
            if (s.waitAvailable()) {
                return true;
            }
        }
        return false;
    }

    public Pair<Server, ServerList> serve(Customer c) {
        Server s;
        ImList<Server> servers = this.servers;
        if (this.currAvailable()) {
            for (int i = 0; i < this.servers.size(); i++) {
                s = this.servers.get(i);
                if (s.currAvailable()) {
                    s = s.serve(c);
                    servers = servers.set(i, s);
                    ServerList serverList = new ServerList(servers);
                    return new Pair<Server, ServerList>(s, serverList);
                }
            }
        } else {
            for (int i = 0; i < this.servers.size(); i++) {
                s = this.servers.get(i);
                if (s.waitAvailable()) {
                    s = s.serve(c);
                    servers = servers.set(i, s);
                    ServerList serverList = new ServerList(servers);
                    return new Pair<Server, ServerList>(s, serverList);
                }
            }
        }
        s = servers.get(0);
        ServerList serverList = new ServerList(servers);
        return new Pair<Server, ServerList>(s, serverList);
    }

    public ServerList updateServer(Server s) {
        ImList<Server> servers = this.servers;
        int index = s.getId() - 1;
        servers = servers.set(index, s);
        return new ServerList(servers);
    }

    public double getAvailable(Server s) {
        int index = s.getId() - 1;
        s = this.servers.get(index);
        return s.getAvailable();
    }

    public ServerList updateTime(Server s, double time) {
        ImList<Server> servers = this.servers;
        int index = s.getId() - 1;
        s = this.servers.get(index);
        s = s.updateTime(time);
        servers = servers.set(index, s);
        return new ServerList(servers);
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


}    //public boolean hasNext
 

class Case {
   
    private final Protocol protocol;
    private final Person person;
    private final Test tests;
    private final ImList<String> history;

    Case(Person person, Test tests, Case c) {
        this.person = person;
        this.tests = tests;
        this.protocol = new P3().next(person, tests, 1);
        if (tests.isValid()) {
            this.history = new ImList<String>().add(tests.toString());
        } else {
            this.history = new ImList<String>();
        } 
    }

    Case(Person person, PCR tests) {
        this.person = person;
        this.tests = tests;
        this.protocol = new P1().next(person, tests, 1);
        if (tests.isValid()) {
            this.history = new ImList<String>().add(tests.toString());
        } else {
            this.history = new ImList<String>();
        }

    }

    Case(Person person, Test tests, ImList<String> history, Protocol protocol) {
        this.protocol = protocol;
        this.person = person;
        this.tests = tests;
        this.history = history;
    }

    public int getDays() {
        return this.history.size();
    }

    Case test(Test tests) {
        if (!tests.isValid()) {
            return this;
        }
        ImList<String> history = this.getTestHistory().add(tests.toString());
        Person p = this.getPerson();
        Protocol protocol = this.getCurrentProtocol().next(p, tests, this.getDays() + 1);
        return new Case(this.getPerson(), tests, history, protocol);
    }

    Person getPerson() {
        return this.person;
    }

    Protocol getCurrentProtocol() {
        return this.protocol;
    }

    ImList<String> getTestHistory() {
        return this.history;
    }

    ImList<Case> getLineage() {
        return new ImList<Case>().add(this);
    }

    ImList<Case> getLineage(ImList<Case> list) {
        return new ImList<Case>().add(this).addAll(list);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.person, this.history, this.protocol);
    }

}




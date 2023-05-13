class ContactCase extends Case {

    private final Case c;

    ContactCase(Person person, Test tests, Case c) {
        super(person, tests, c);
        this.c = c;
    }

    ContactCase(Person person, Test tests, ImList<String> history, Protocol protocol, Case c) {
        super(person, tests, history, protocol);
        this.c = c;
    }


    @Override
    Case test(Test tests) {
        Case curr = super.test(tests);
        Person p = curr.getPerson();
        ImList<String> history = curr.getTestHistory();
        Protocol protocol = curr.getCurrentProtocol();
        return new ContactCase(p, tests, history, protocol, this.c);
    }
            

    @Override
    ImList<Case> getLineage() {
        ImList<Case> list = new ImList<Case>();
        list = list.add(this);
        return this.c.getLineage(list);
    }

    @Override
    ImList<Case> getLineage(ImList<Case> list) {
        list = new ImList<Case>().add(this).addAll(list);
        return this.c.getLineage(list);
    }
}

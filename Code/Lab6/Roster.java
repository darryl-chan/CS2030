import java.util.Optional;

class Roster extends KeyableMap<Student> {

    Roster(String id) {
        super(id);
    }

    Roster(String id, ImmutableMap<String, Student> map) {
        super(id, map);
    }

    public Roster createRoster(ImmutableMap<String, Student> map) {
        return new Roster(this.getKey(), map);
    }

    public Roster put(Student stu) {
        ImmutableMap<String, Student> map = this.getMap();
        map = map.put(stu.getKey(), stu);
        return this.createRoster(map);
    }

    public String getGrade(String name, String mod, String ass) {
        return this.getMap().get(name).flatMap(x -> x.get(mod)).flatMap(x -> x.get(ass)).map(
            x -> x.getGrade()).orElse(String.format(
                    "No such record: %s %s %s", name, mod, ass));
    }

    public Roster add(String name, String mod, String ass, String grade) {
        Assessment assessment = new Assessment(ass, grade);
        Module module = new Module(mod).put(assessment);
        Student student = new Student(name).put(module);
        
        return this.getMap().get(name).map(x -> this.put(x.add(mod, ass, grade))).orElse(
                this.put(student));
    }

    /*
    public Roster add(String name, String mod, String ass, String grade) {
        Assessment assessment = new Assessment(ass, grade);
        Module module = new Module(mod).put(assessment);
        System.out.println("40: " + this.getMap().get(name));
        System.out.println("41: " + this.getMap().get(name).map(x -> x.put(module)));
    
        this.getMap().get(name).map(x -> x.add(mod, ass, grade))

                new Student(name).put(module));
        return this.put(stu);
    }
    */

}


 



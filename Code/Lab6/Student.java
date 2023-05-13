import java.util.Optional;

class Student extends KeyableMap<Module> {

    Student(String id) {
        super(id);
    }

    Student(String id, ImmutableMap<String, Module> map) {
        super(id, map);
    }

    public Student createStudent(ImmutableMap<String, Module> map) {
        return new Student(this.getKey(), map);
    }

    @Override
    public Student put(Module mod) {
        ImmutableMap<String, Module> map = this.getMap();
        map = map.put(mod.getKey(), mod);
        return this.createStudent(map);
    }

    public Student add(String mod, String ass, String grade) {
        Assessment assessment = new Assessment(ass, grade);
        Module module = new Module(mod).put(assessment);

        return this.getMap().get(mod).map(x -> this.put(x.put(assessment))).orElse(
                this.put(module));
    }

    /*
    private final String id;
    private final ImmutableMap<String, Module> map;

    Student(String id) {
        this.id = id;
        this.map = new ImmutableMap<String, Module>();
    }

    Student(String id, ImmutableMap<String, Module> map) {
        this.id = id;
        this.map = map;
    }

    private Student createStudent(ImmutableMap<String, Module> map) {
        return new Student(this.id, map);
    }

    @Override
    public String getKey() {
        return this.id;
    }

    public Student put(Module mod) {
        ImmutableMap<String, Module> map = this.map;
        map = map.put(mod.getKey(), mod);
        return this.createStudent(map);
    }

    public Optional<Module> get(String key) {
        return this.map.get(key);
    }

    @Override
    public String toString() {
        int leading = this.map.values().toString().length();
        String finalString = this.map.values().toString().substring(1, leading - 1);

        return String.format("%s: {%s}", this.id, finalString);
    }
    */
}






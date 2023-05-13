import java.util.Optional;

class Module extends KeyableMap<Assessment> {

    Module(String id) {
        super(id);
    }

    Module(String id, ImmutableMap<String, Assessment> map) {
        super(id, map);
    }

    public Module createModule(ImmutableMap<String, Assessment> map) {
        return new Module(this.getKey(), map);
    }
    
    @Override
    public Module put(Assessment ass) {
        ImmutableMap<String, Assessment> map = this.getMap();
        map = map.put(ass.getKey(), ass);
        return this.createModule(map);
    }
   
}

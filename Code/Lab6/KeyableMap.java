import java.util.Optional;
import java.util.Map;

class KeyableMap<V extends Keyable> implements Keyable {

    private final String id;
    private final ImmutableMap<String, V> map;

    KeyableMap(String id) {
        this.id = id;
        this.map = new ImmutableMap<String, V>();
    }

    KeyableMap(String id, ImmutableMap<String, V> map) {
        this.id = id;
        this.map = map;
    }

    public KeyableMap<V> createNew(ImmutableMap<String, V> map) {
        return new KeyableMap<V>(this.id, map);
    }
    
    @Override
    public String getKey() {
        return this.id;
    }

    public ImmutableMap<String, V> getMap() {
        return this.map;
    }

    public Optional<V> get(String key) {
        return this.getMap().get(key);
    }

    public KeyableMap<V> put(V v1) {
        ImmutableMap<String, V> map = this.getMap();
        map = map.put(v1.getKey(), v1);
        return this.createNew(map);
    }

    @Override
    public String toString() {

        String finalStr = "";

        for (Map.Entry<String, V> e: this.getMap()) {
            finalStr += String.format("%s, ", e.getValue());
        }

        //System.out.println(finalStr);

        int leading = finalStr.length();
        String finalString = (leading != 0) ? finalStr.substring(0, leading - 1 - 1) : finalStr;

        //System.out.println("Debug Natasha case");
        //System.out.println("key: " + this.getKey());

        return String.format("%s: {%s}", this.getKey(), finalString);
    }
}





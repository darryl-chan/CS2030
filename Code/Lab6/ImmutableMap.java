import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

class ImmutableMap<K,V> implements Iterable<Map.Entry<K,V>> {
    private final Map<K,V> map;
    
    ImmutableMap() {
        this.map = new LinkedHashMap<K,V>();
    }

    ImmutableMap<K,V> put(K key, V value) {
        ImmutableMap<K,V> newMap = new ImmutableMap<K,V>();
        newMap.map.putAll(this.map);       
        newMap.map.put(key,value);
        return newMap;
    }

    Set<K> keySet() {
        return this.map.keySet();
    }

    Collection<V> values() {
        return this.map.values();
    }

    Set<Map.Entry<K,V>> entrySet() {
        return this.map.entrySet();
    }

    public Iterator<Map.Entry<K,V>> iterator() {
        return this.entrySet().iterator();
    }

    Optional<V> get(Object key) {
        return Optional.<V>ofNullable(this.map.get(key));
    }

    boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public String toString() {
        return this.map.toString();
    }
}

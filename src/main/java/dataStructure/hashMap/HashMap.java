package dataStructure.hashMap;

import java.util.List;

public interface HashMap<K, V> {
    int size();

    boolean containsKey(K key);

    void put(K key, V value);

    V get(K key);

    void remove(K key);

    List<K> keys();

    List<V> values();

    List<Entry<K, V>> entries();
}

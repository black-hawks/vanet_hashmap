package dataStructure.hashMap;

public interface HashMap<K, V> {
    int size();

    boolean containsKey(K key);
    void put(K key, V value);
    V get(K key);
    void remove(K key);

}

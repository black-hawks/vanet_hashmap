package dataStructure.hashMap;

import java.util.List;

/**
 * The HashMap interface defines the basic operations that can be performed on a hash map data structure.
 *
 * @param <K> the type of keys in this map
 * @param <V> the type of mapped values in this map
 */
public interface HashMap<K, V> {
    /**
     * Returns the number of key-value mappings in this hash map.
     *
     * @return the number of key-value mappings in this hash map
     */
    int size();

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    boolean containsKey(K key);

    /**
     * Associates the specified value with the specified key in this hash map.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    void put(K key, V value);

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    V get(K key);


    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key the key whose mapping is to be removed from the map
     */
    void remove(K key);

    /**
     * Returns a list of the keys contained in this map.
     *
     * @return a list of the keys contained in this map
     */
    List<K> keys();

    /**
     * Returns a list of the values contained in this map.
     *
     * @return a list of the values contained in this map
     */
    List<V> values();

    /**
     * Returns a list of the entries (key-value pairs) contained in this map.
     *
     * @return a list of the entries (key-value pairs) contained in this map
     */
    List<Entry<K, V>> entries();
}

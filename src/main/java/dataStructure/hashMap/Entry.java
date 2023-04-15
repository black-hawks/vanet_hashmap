package dataStructure.hashMap;

/**
 * A key-value entry in a hash map.
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class Entry<K, V> {
    private final K key;
    private final V value;

    /**
     * Constructs a new Entry with the specified key and value.
     *
     * @param key the key for the entry
     * @param value the value for the entry
     */
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key for this entry.
     *
     * @return the key for this entry
     */
    public K getKey() {
        return key;
    }

    /**
     * Returns the value for this entry.
     *
     * @return the value for this entry
     */
    public V getValue() {
        return value;
    }
}


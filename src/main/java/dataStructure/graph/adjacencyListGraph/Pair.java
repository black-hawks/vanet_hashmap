package dataStructure.graph.adjacencyListGraph;

/**
 * A Pair class representing a key-value pair.
 *
 * @param <V> the type of the key
 * @param <K> the type of the value
 */
public class Pair<V, K> {

    /**
     * The key of the pair.
     */
    public V key;

    /**
     * The value of the pair.
     */
    public K value;

    /**
     * Constructs a Pair object with the given key and value.
     *
     * @param key   the key of the pair
     * @param value the value of the pair
     */
    public Pair(V key, K value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key of the pair.
     *
     * @return the key of the pair
     */
    public V getKey() {
        return key;
    }

    /**
     * Returns the value of the pair.
     *
     * @return the value of the pair
     */
    public K getValue() {
        return value;
    }
}

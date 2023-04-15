package dataStructure.hashMap;

/**
 * A class representing a node in a hash map data structure.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
class Node<K, V> {
    /**
     * The key of this node.
     */
    K key;

    /**
     * The value of this node.
     */
    V value;

    /**
     * The next node in the linked list.
     */
    Node<K, V> next;

    /**
     * Constructs a new node with the specified key and value.
     *
     * @param key the key of the new node
     * @param value the value of the new node
     */
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

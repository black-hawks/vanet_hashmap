package dataStructure.hashMap;

import dataStructure.hashMap.hashFunction.HashFunction;
import dataStructure.hashMap.hashFunction.Modulus;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeHashMap is an implementation of the HashMap interface that uses a tree data structure to
 * organize key-value pairs. The keys are required to be comparable, and the implementation
 * assumes that keys do not change while in the map.
 *
 * @param <K> the type of keys maintained by this map (must be comparable)
 * @param <V> the type of mapped values
 */
public class TreeHashMap<K extends Comparable<K>, V> implements HashMap<K, V> {
    /**
     * Default capacity of the map
     */
    public static final int DEFAULT_CAPACITY = 16;

    /**
     * Default load factor of the map
     */
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Default value for whether or not the map should be resizable
     */
    public static final boolean DEFAULT_RESIZABLE = true;
    private Node<K, V>[] table;
    private int size;
    private int capacity;
    private final float loadFactor;
    private final boolean resizable;
    private final HashFunction<K> hashFunction;

    /**
     * Constructs a new, empty tree hash map with the default initial capacity (16).
     */
    public TreeHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new, empty tree hash map with the specified initial capacity.
     *
     * @param capacity the initial capacity of the map
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    public TreeHashMap(int capacity) {
        this(capacity, DEFAULT_RESIZABLE);
    }

    /**
     * Constructs a new, empty tree hash map with the specified initial capacity and
     * resize behavior.
     *
     * @param capacity  the initial capacity of the map
     * @param resizable whether or not the map should be resizable
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    public TreeHashMap(int capacity, boolean resizable) {
        this(capacity, resizable, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a new, empty tree hash map with the specified initial capacity, resize behavior,
     * and load factor.
     *
     * @param capacity    the initial capacity of the map
     * @param resizable   whether or not the map should be resizable
     * @param loadFactor  the load factor of the map
     * @throws IllegalArgumentException if the specified initial capacity is negative or the
     *                                  specified load factor is non-positive or NaN
     */
    public TreeHashMap(int capacity, boolean resizable, float loadFactor) {
        this(capacity, resizable, loadFactor, new Modulus<>());
    }

    /**
     * Constructs a new, empty tree hash map with the specified initial capacity, resize behavior,
     * load factor, and hash function.
     *
     * @param initialCapacity the initial capacity of the map
     * @param resizable       whether or not the map should be resizable
     * @param loadFactor      the load factor of the map
     * @param hashFunction    the hash function to use
     * @throws IllegalArgumentException if the specified initial capacity is negative or the
     *                                  specified load factor is non-positive or NaN
     */
    public TreeHashMap(int initialCapacity, boolean resizable, float loadFactor, HashFunction<K> hashFunction) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;
        this.capacity = initialCapacity;
        this.resizable = resizable;
        this.hashFunction = hashFunction;
        //noinspection unchecked
        table = new Node[initialCapacity];
    }

    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new Node<>(key, value);
            size++;
        } else {
            table[index] = insert(table[index], key, value);
        }
        if (resizable && (float) size / capacity >= loadFactor) {
            resize();
        }
    }

    /**
     * Returns the value associated with the specified key in this hash table, or null if the key is not present.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this hash table contains no mapping for the key
     */
    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = find(table[index], key);
        return node != null ? node.value : null;
    }

    /**
     * Removes the mapping for the specified key from this hash table if present.
     *
     * @param key the key whose mapping is to be removed from the hash table
     */
    public void remove(K key) {
        int index = hash(key);
        table[index] = delete(table[index], key);
    }

    /**
     * Returns the number of key-value mappings in this hash table.
     *
     * @return the number of key-value mappings in this hash table
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this hash table contains a mapping for the specified key.
     *
     * @param key the key whose presence in this hash table is to be tested
     * @return true if this hash table contains a mapping for the specified key
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Returns the hash code value for the given key.
     *
     * @param key the key for which to compute the hash code
     * @return the hash code value for the given key
     */
    private int hash(K key) {
        return this.hashFunction.hash(key, capacity);
    }

    /**
     * Inserts a new key-value pair into the binary search tree rooted at the given node.
     * If the key already exists in the tree, the corresponding value is updated.
     *
     * @param node the root node of the tree
     * @param key the key to insert or update
     * @param value the value to associate with the key
     * @return the root node of the modified tree
     */
    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    /**
     * Searches for the node with the specified key in the binary search tree rooted at the given node.
     *
     * @param node the root node of the tree
     * @param key the key to search for
     * @return the node with the specified key, or null if the key is not found in the tree
     */
    private Node<K, V> find(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return find(node.left, key);
        } else if (cmp > 0) {
            return find(node.right, key);
        } else {
            return node;
        }
    }

    /**
     * Deletes the node with the given key from the tree rooted at the given node.
     * If the key is not found in the tree, the original node is returned.
     *
     * @param node the root of the tree to delete from
     * @param key the key to search for and delete
     * @return the root of the modified tree after the deletion
     */
    private Node<K, V> delete(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            } else {
                Node<K, V> min = node.right;
                while (min.left != null) {
                    min = min.left;
                }
                node.key = min.key;
                node.value = min.value;
                node.right = delete(node.right, min.key);
            }
        }
        return node;
    }

    /**
     * Resizes the tree by doubling the capacity and copying data from
     * old table to new table. If collisions are found, tree is traversed
     * and rehashed the keys to find the new location.
     */
    public void resize() {
        int newCapacity = capacity * 2;
        Node<K, V>[] oldTable = table;
        //noinspection unchecked
        table = new Node[newCapacity];
        capacity = newCapacity;
        size = 0;
        for (Node<K, V> node : oldTable) {
            if (node != null) {
                resizeHelper(node);
            }
        }
    }

    /**
     * Recursively reinserts the key-value pairs in the given node's subtree
     * into the hash table after a resize.
     *
     * @param node the root of the subtree to reinsert
     */
    private void resizeHelper(Node<K, V> node) {
        if (node != null) {
            put(node.key, node.value);
            resizeHelper(node.left);
            resizeHelper(node.right);
        }
    }

    /**
     * Returns a list of all the keys in the hash table, in no particular order.
     *
     * @return a list of all keys in the hash table
     */
    public List<K> keys() {
        List<K> keysList = new ArrayList<>();
        for (Node<K, V> node : table) {
            getKeys(node, keysList);
        }
        return keysList;
    }

    /**
     * Recursively adds the keys of the given node and its subtrees to the given list
     * in ascending order.
     *
     * @param node the root of the subtree to add keys from
     * @param keysList the list to add the keys to
     */
    private void getKeys(Node<K, V> node, List<K> keysList) {
        if (node == null) {
            return;
        }
        getKeys(node.left, keysList);
        keysList.add(node.key);
        getKeys(node.right, keysList);
    }

    /**
     * Returns a list of all the values in the hash table, in no particular order.
     *
     * @return a list of all values in the hash table
     */
    public List<V> values() {
        List<V> valuesList = new ArrayList<>();
        for (Node<K, V> node : table) {
            getValues(node, valuesList);
        }
        return valuesList;
    }

    /**
     * Recursively adds the values of the given node and its subtrees to the given list.
     *
     * @param node the root of the subtree to add values from
     * @param valuesList the list to add the values to
     */
    private void getValues(Node<K, V> node, List<V> valuesList) {
        if (node == null) {
            return;
        }
        getValues(node.left, valuesList);
        valuesList.add(node.value);
        getValues(node.right, valuesList);
    }

    /**
     * Returns a list of all the key-value pairs in the hash table, in no particular order.
     *
     * @return a list of all key-value pairs in the hash table
     */
    public List<Entry<K, V>> entries() {
        List<Entry<K, V>> entriesList = new ArrayList<>();
        for (Node<K, V> node : table) {
            getEntries(node, entriesList);
        }
        return entriesList;
    }

    /**
     * Recursively adds the key-value pairs of the given node and its subtrees to the given list.
     *
     * @param node the root of the subtree to add key-value pairs from
     * @param entriesList the list to add the key-value pairs to
     */
    private void getEntries(Node<K, V> node, List<Entry<K, V>> entriesList) {
        if (node == null) {
            return;
        }
        getEntries(node.left, entriesList);
        entriesList.add(new Entry<>(node.key, node.value));
        getEntries(node.right, entriesList);
    }

    /**
     * A class representing a node in a binary search tree.
     *
     * @param <K> the type of the key stored in the node
     * @param <V> the type of the value stored in the node
     */
    private static class Node<K, V> {
        /**
         * The key stored in the node.
         */
        private K key;

        /**
         * The value stored in the node.
         */
        private V value;

        /**
         * The left child of the node.
         */
        private Node<K, V> left;

        /**
         * The right child of the node.
         */
        private Node<K, V> right;

        /**
         * Constructs a new node with the specified key and value.
         *
         * @param key the key to be stored in the node
         * @param value the value to be stored in the node
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}


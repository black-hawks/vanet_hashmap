package dataStructure.hashMap;

import dataStructure.hashMap.hashFunction.HashFunction;
import dataStructure.hashMap.hashFunction.Modulus;

import java.util.ArrayList;
import java.util.List;

/**
 * A hash map implementation that uses linked lists to handle collisions.
 *
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public class LinkedListHashMap<K, V> implements HashMap<K, V> {
    /**
     * Default capacity of the hash table.
     */
    public static final int DEFAULT_CAPACITY = 16;

    /**
     * Default load factor of the hash table.
     */
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Default resizable property of the hash table.
     */
    public static final boolean DEFAULT_RESIZABLE = true;
    private final float loadFactor;
    private int capacity;
    private int size;
    private final boolean resizable;

    private Node<K, V>[] table;
    private final HashFunction<K> hashFunction;

    /**
     * Constructs a new hash map with the specified initial capacity, resizable property, load factor and hash function.
     *
     * @param initialCapacity the initial capacity of the hash table
     * @param resizable       whether the hash table is resizable
     * @param loadFactor      the load factor of the hash table
     * @param hashFunction    the hash function used to hash keys
     * @throws IllegalArgumentException if the initial capacity is negative or the load factor is non-positive or NaN
     */
    public LinkedListHashMap(int initialCapacity, boolean resizable, float loadFactor, HashFunction<K> hashFunction) {
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
        this.table = new Node[this.capacity];
    }

    /**
     * Constructs a new hash map with the specified capacity and resizable property.
     *
     * @param capacity  the capacity of the hash table
     * @param resizable whether the hash table is resizable
     */
    public LinkedListHashMap(int capacity, boolean resizable) {
        this(capacity, resizable, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a new hash map with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the hash table
     */
    public LinkedListHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_RESIZABLE);
    }

    /**
     * Constructs a new hash map with the default capacity.
     */
    public LinkedListHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new hash map with the specified capacity, resizable property and load factor.
     *
     * @param capacity   the capacity of the hash table
     * @param resizable  whether or not the hash table is resizable
     * @param loadFactor the load factor of the hash table
     * @throws IllegalArgumentException if the load factor is non-positive or NaN
     */
    public LinkedListHashMap(int capacity, boolean resizable, float loadFactor) {
        this(capacity, resizable, loadFactor, new Modulus<>());
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key, false otherwise
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the value is added to end of the linked list.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(K key, V value) {
        int index = hash(key);

        Node<K, V> node = new Node<>(key, value);

        if (table[index] == null) {
            table[index] = node;
            size++;
        } else {
            Node<K, V> temp = table[index];
            while (temp.next != null) {
                if (temp.key.equals(key)) {
                    temp.value = value;
                    return;
                }
                temp = temp.next;
            }
            if (temp.key.equals(key)) {
                temp.value = value;
            } else {
                temp.next = node;
                size++;
            }
        }
        if (resizable && (float) size / capacity >= loadFactor) {
            resize();
        }
    }

    /**
     * Returns the value associated with the specified key in this hash table. If the node's next is not null, it will
     * traverse to the find the element in the linked list.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or {@code null} if this map contains no mapping for the key
     */
    public V get(K key) {
        int index = hash(key);

        if (table[index] != null) {
            Node<K, V> temp = table[index];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    return temp.value;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    /**
     * Removes the mapping for a key from this hash table if it is present.
     *
     * @param key the key whose mapping is to be removed from the map
     */
    public void remove(K key) {
        int index = hash(key);

        if (table[index] != null) {
            Node<K, V> temp = table[index];
            if (temp.key.equals(key)) {
                table[index] = temp.next;
                size--;
            } else {
                Node<K, V> prev = temp;
                temp = temp.next;
                while (temp != null) {
                    if (temp.key.equals(key)) {
                        prev.next = temp.next;
                        size--;
                        return;
                    }
                    prev = temp;
                    temp = temp.next;
                }
            }
        }
    }

    /**
     * Resizes the hash table by creating a new array with double the capacity and
     * rehashing all the elements from the old array into the new one.
     *
     * @throws IllegalStateException if the hash table is not resizable
     */
    private void resize() {
        int newCapacity = capacity * 2;
        @SuppressWarnings("unchecked")
        Node<K, V>[] newTable = new Node[newCapacity];
        for (int i = 0; i < capacity; i++) {
            Node<K, V> curr = table[i];
            while (curr != null) {
                int index = this.hashFunction.hash(curr.key, newCapacity);
                Node<K, V> entry = new Node<>(curr.key, curr.value);
                if (newTable[index] == null) {
                    newTable[index] = entry;
                } else {
                    Node<K, V> tail = newTable[index];
                    while (tail.next != null) {
                        tail = tail.next;
                    }
                    tail.next = entry;
                }
                curr = curr.next;
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    /**
     * This method returns a List of all the keys present in the hash table.
     *
     * @return a List of type K containing all the keys present in the hash table.
     */
    public List<K> keys() {
        List<K> keys = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                keys.add(node.key);
                node = node.next;
            }
        }
        return keys;
    }

    /**
     * This method returns a List of all the values present in the hash table.
     *
     * @return a List of type V containing all the values present in the hash table.
     */
    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                values.add(node.value);
                node = node.next;
            }
        }
        return values;
    }

    /**
     * This method returns a List of all the entries present in the hash table.
     *
     * @return a List of type Generic class called Entry has K Key, V Value containing all the entries present
     * in the hash table.
     */
    public List<Entry<K, V>> entries() {
        List<Entry<K, V>> entries = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                Entry<K, V> entry = new Entry<>(node.key, node.value);
                entries.add(entry);
                node = node.next;
            }
        }
        return entries;
    }

    /**
     * This method calculates the hash value for a given key using the hash function.
     *
     * @param key the key for which the hash value needs to be calculated
     * @return an integer value representing the hash code for the given key.
     */
    private int hash(K key) {
        return this.hashFunction.hash(key, capacity);
    }
}
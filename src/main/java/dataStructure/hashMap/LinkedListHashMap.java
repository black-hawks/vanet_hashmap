package dataStructure.hashMap;

import java.util.ArrayList;
import java.util.List;

public class LinkedListHashMap<K, V> implements HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final float loadFactor;
    private int capacity;
    private int size;
    private Node<K, V>[] table;

    public LinkedListHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;
        this.capacity = initialCapacity;
        //noinspection unchecked
        this.table = new Node[this.capacity];
    }

    public LinkedListHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public LinkedListHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

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
        if ((float) size / capacity >= loadFactor) {
            resize();
        }
    }

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

    private void resize() {
        int newCapacity = capacity * 2;
        @SuppressWarnings("unchecked")
        Node<K, V>[] newTable = new Node[newCapacity];
        for (int i = 0; i < capacity; i++) {
            Node<K, V> curr = table[i];
            while (curr != null) {
                int index = Math.abs(curr.key.hashCode() % newCapacity);
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

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }
}
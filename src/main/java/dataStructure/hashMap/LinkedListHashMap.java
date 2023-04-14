package dataStructure.hashMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

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
        this.table = (Node<K, V>[]) new Node[this.capacity];
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
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];
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

    public Iterator<K> keys() {
        return new KeyIterator();
    }

    public Iterator<V> values() {
        return new ValueIterator();
    }

    public Iterator<Entry<K, V>> entries() {
        return new EntryIterator();
    }

    private class KeyIterator implements Iterator<K> {
        private int currentIndex = -1;
        private Node<K, V> currentNode = null;

        public boolean hasNext() {
            if (currentNode != null && currentNode.next != null) {
                return true;
            }
            for (int i = currentIndex + 1; i < capacity; i++) {
                if (table[i] != null) {
                    return true;
                }
            }
            return false;
        }

        public K next() {
            if (currentNode != null && currentNode.next != null) {
                currentNode = currentNode.next;
            } else {
                do {
                    currentIndex++;
                    if (currentIndex >= capacity) {
                        throw new NoSuchElementException();
                    }
                    currentNode = table[currentIndex];
                } while (currentNode == null);
            }
            return currentNode.key;
        }
    }

    private class ValueIterator implements Iterator<V> {
        private final Iterator<Entry<K, V>> entryIterator = new EntryIterator();

        public boolean hasNext() {
            return entryIterator.hasNext();
        }

        public V next() {
            return entryIterator.next().getValue();
        }
    }

    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int currentIndex = -1;
        private Node<K, V> currentNode = null;

        public boolean hasNext() {
            if (currentNode != null && currentNode.next != null) {
                return true;
            }
            for (int i = currentIndex + 1; i < capacity; i++) {
                if (table[i] != null) {
                    return true;
                }
            }
            return false;
        }

        public Entry<K, V> next() {
            if (currentNode != null && currentNode.next != null) {
                currentNode = currentNode.next;
            } else {
                do {
                    currentIndex++;
                    if (currentIndex >= capacity) {
                        throw new NoSuchElementException();
                    }
                    currentNode = table[currentIndex];
                } while (currentNode == null);
            }
            return new Entry<>(currentNode.key, currentNode.value);
        }
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        Iterator<Entry<K, V>> iterator = entries();
        while (iterator.hasNext()) {
            entrySet.add(iterator.next());
        }
        return entrySet;
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        Iterator<K> iterator = keys();
        while (iterator.hasNext()) {
            keySet.add(iterator.next());
        }
        return keySet;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }
}
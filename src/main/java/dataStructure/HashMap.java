package dataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

class HashMap<K, V> {
    private final int capacity;
    private final Node<K, V>[] table;

    public HashMap(int capacity) {
        this.capacity = capacity;
        //noinspection unchecked
        this.table = (Node<K, V>[]) new Node[capacity];
    }

    public void put(K key, V value) {
        int index = hash(key);

        Node<K, V> node = new Node<>(key, value);

        if (table[index] == null) {
            table[index] = node;
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
            }
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
            } else {
                Node<K, V> prev = temp;
                temp = temp.next;
                while (temp != null) {
                    if (temp.key.equals(key)) {
                        prev.next = temp.next;
                        return;
                    }
                    prev = temp;
                    temp = temp.next;
                }
            }
        }
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


    private int hash(K key) {
        return key.hashCode() % capacity;
    }
}
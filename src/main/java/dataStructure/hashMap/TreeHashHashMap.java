package dataStructure.hashMap;

public class TreeHashHashMap<K extends Comparable<K>, V> implements HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private final Node<K, V>[] table;
    private int size;

    public TreeHashHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public TreeHashHashMap(int capacity) {
        //noinspection unchecked
        table = (Node<K, V>[]) new Node[capacity];
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new Node<>(key, value);
            size++;
        } else {
            table[index] = insert(table[index], key, value);
        }
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = find(table[index], key);
        return node != null ? node.value : null;
    }

    public void remove(K key) {
        int index = hash(key);
        table[index] = delete(table[index], key);
    }

    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

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

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}


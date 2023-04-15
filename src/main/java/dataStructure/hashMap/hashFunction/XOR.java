package dataStructure.hashMap.hashFunction;

public class XOR<K> implements HashFunction<K> {
    @Override
    public int hash(K key, int capacity) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        h ^= (h >>> 7) ^ (h >>> 4);
        return Math.abs(h % capacity);
    }
}

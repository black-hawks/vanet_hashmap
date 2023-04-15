package dataStructure.hashMap.hashFunction;

/**
 * An implementation of the XOR hash function that implements the {@link HashFunction} interface
 *
 * @param <K> the type of keys that will be hashed by this function
 */
public class XOR<K> implements HashFunction<K> {

    /**
     * Computes the hash code for a given key using the XOR hash function.
     *
     * @param key the key to be hashed
     * @param capacity the capacity of the hash table
     * @return the hash code of the key
     */
    @Override
    public int hash(K key, int capacity) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        h ^= (h >>> 7) ^ (h >>> 4);
        return Math.abs(h % capacity);
    }
}

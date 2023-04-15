package dataStructure.hashMap.hashFunction;

/**
 * The Modulus class implements the HashFunction interface to provide a hash function that uses modulus calculation
 * to determine the hash code of a key.
 *
 * @param <K> the type of key to be hashed.
 */
public class Modulus <K> implements HashFunction<K> {

    /**
     * Calculates the hash code of a key using modulus calculation.
     *
     * @param key the key to be hashed.
     * @param capacity the capacity of the hash table.
     * @return the hash code of the key.
     */
    @Override
    public int hash(K key, int capacity) {
        return Math.abs(key.hashCode() % capacity);
    }
}

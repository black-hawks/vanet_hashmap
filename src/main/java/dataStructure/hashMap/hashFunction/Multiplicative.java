package dataStructure.hashMap.hashFunction;

/**
 * The Multiplicative class is an implementation of the HashFunction interface
 * that uses the Multiplicative hash function to generate hash codes.
 *
 * @param <K> the type of the key that is being hashed
 */
public class Multiplicative<K> implements HashFunction<K> {

    /**
     * Generates a hash code for the given key using the Multiplicative hash function.
     *
     * @param key the key to be hashed
     * @param capacity the capacity of the hash table
     * @return the hash code for the given key
     */
    @Override
    public int hash(K key, int capacity) {
        final int prime = 31;
        int result = 1;
        result = prime * result + key.hashCode();
        return Math.abs((int) (capacity * ((result * 0.618033988749895) % 1)));
    }
}

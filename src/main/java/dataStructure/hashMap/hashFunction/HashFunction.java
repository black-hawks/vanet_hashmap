package dataStructure.hashMap.hashFunction;

/**
 * The HashFunction interface defines the contract for an object that generates a hash code for a given key
 * and a specified capacity.
 *
 * @param <K> the type of the key to be hashed
 */
public interface HashFunction<K> {

    /**
     * Generates a hash code for the specified key and capacity.
     *
     * @param key the key to be hashed
     * @param capacity the capacity of the hash table
     * @return the hash code for the specified key
     */
    int hash(K key, int capacity);
}



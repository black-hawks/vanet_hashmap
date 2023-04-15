package dataStructure.hashMap.hashFunction;

public class Modulus <K> implements HashFunction<K> {
    @Override
    public int hash(K key, int capacity) {
        return Math.abs(key.hashCode() % capacity);
    }
}

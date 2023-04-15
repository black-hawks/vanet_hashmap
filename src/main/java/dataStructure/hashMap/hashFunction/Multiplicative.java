package dataStructure.hashMap.hashFunction;

public class Multiplicative<K> implements HashFunction<K> {
    @Override
    public int hash(K key, int capacity) {
        final int prime = 31;
        int result = 1;
        result = prime * result + key.hashCode();
        return Math.abs((int) (capacity * ((result * 0.618033988749895) % 1)));
    }
}

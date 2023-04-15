package dataStructure.hashMap.hashFunction;

public interface HashFunction<K> {
    int hash(K key, int capacity);
}



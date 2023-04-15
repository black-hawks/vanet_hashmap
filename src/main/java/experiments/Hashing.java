package experiments;

import dataStructure.hashMap.hashFunction.*;
import util.GraphGeneration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Hashing class provides methods to generate vehicle data, and calculate collision rates for different hash functions.
 * This class contains a main method that generates vehicle data with varying number of vertices and calculates the collision
 * rate for the Modulus, Multiplicative and XOR hash functions.
 * The getCollisionRate method calculates the collision rate for a given hash function and a list of keys.
 */
public class Hashing {

    /**
     * The main method generates vehicle data with varying number of vertices and calculates the collision
     * rate for the Modulus, Multiplicative and XOR hash functions.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        int[] vertices = {10, 50, 100, 500, 1000, 2500, 5000};
        for (int vertex : vertices) {
            List<Vehicle> vehicles = GraphGeneration.generateVehicleData(vertex);
            double collisionRate = getCollisionRate(new Modulus<>(), vehicles);
            System.out.println("Modulus Hash function has " + collisionRate + " collision rate for " + vehicles.size() + " vehicles");

            collisionRate = getCollisionRate(new Multiplicative<>(), vehicles);
            System.out.println("Multiplicative Hash function has " + collisionRate + " collision rate for " + vehicles.size() + " vehicles");

            collisionRate = getCollisionRate(new XOR<>(), vehicles);
            System.out.println("XOR Hash function has " + collisionRate + " collision rate for " + vehicles.size() + " vehicles");

            System.out.println();
        }
    }

    /**
     * The getCollisionRate method calculates the collision rate for a given hash function and a list of keys.
     *
     * @param hashFunction the hash function to use
     * @param keys         the list of keys to hash
     * @param <K>          the type of the keys
     * @return the collision rate for the given hash function and list of keys
     */
    public static <K> double getCollisionRate(HashFunction<K> hashFunction, List<K> keys) {
        int[] hashValues = new int[keys.size()];

        int i = 0;
        for (K key : keys) {
            hashValues[i++] = hashFunction.hash(key, keys.size());
        }

        int collisionCount = 0;
        Set<Integer> uniqueHashValues = new HashSet<>();
        for (int hashValue : hashValues) {
            if (!uniqueHashValues.add(hashValue)) {
                collisionCount++;
            }
        }

        return (double) collisionCount / keys.size();
    }
}
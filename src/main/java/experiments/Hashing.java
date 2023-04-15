package experiments;

import dataStructure.hashMap.hashFunction.*;
import simulation.Vehicle;
import util.GraphGeneration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hashing {
    public static void main(String[] args) {
        int[] vertices = {10, 50, 100, 500, 1000, 2500, 5000};
        for (int vertex : vertices) {
            List<Vehicle> vehicles = GraphGeneration.generateVehicleData(vertex);
            double collisionRate = getCollisionRate(new Modulus<>(), vehicles);
            System.out.println("Modulus Hash function has " + collisionRate + " rate for " + vehicles.size() + " vehicles");

            collisionRate = getCollisionRate(new Multiplicative<>(), vehicles);
            System.out.println("Multiplicative Hash function has " + collisionRate + " rate for " + vehicles.size() + " vehicles");

            collisionRate = getCollisionRate(new XOR<>(), vehicles);
            System.out.println("XOR Hash function has " + collisionRate + " rate for " + vehicles.size() + " vehicles");

            System.out.println();
        }
    }

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

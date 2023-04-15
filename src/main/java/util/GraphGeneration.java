package util;

import dataStructure.graph.Graph;
import dataStructure.graph.hashMapGraph.HashMapGraph;
import dataStructure.hashMap.Entry;
import dataStructure.hashMap.HashMap;
import simulation.Vehicle;

import java.util.*;

/**
 * A utility class for generating random weighted graphs.
 */
public class GraphGeneration {

    /**
     * The maximum weight for edges in the graph.
     */
    private static final int MAX_WEIGHT = 100;

    private static final int MIN_WEIGHT = 10;
    private static final int MAX_SPEED = 140;

    /**
     * A random object used for generating random values.
     */
    private static final Random random = new Random();

    /**
     * Generates a random weighted graph with a given maximum number of vertices.
     *
     * @param maxVertices number of vertices in the graph.
     * @return The generated graph.
     */
    public static Graph<Vehicle> generateRandomWeightedGraph(Graph<Vehicle> graph, int maxVertices) {
        List<VanetEntry> vanetData = generateVanetData(maxVertices);
        return createGraph(graph, vanetData);
    }

    /**
     * Deletes a given number of vertices from a graph.
     *
     * @param graph       The graph to delete vertices from.
     * @param maxVertices The maximum number of vertices to delete.
     * @return The updated graph.
     */
    public static Graph<Vehicle> deleteVertices(Graph<Vehicle> graph, int maxVertices) {

        // create a hashset to store the randomly selected keys
        Set<Vehicle> randomKeys = new HashSet<>();

        Object[] keys = graph.getVertices().toArray();

        // generate random unique keys until the hashset contains 10 keys
        while (randomKeys.size() < maxVertices) {
            Vehicle v = (Vehicle) keys[random.nextInt(graph.getNumberOfVertices())];
            randomKeys.add(v);
        }

        // delete vertices
        for (Vehicle source : randomKeys) {
            if (graph instanceof HashMapGraph<Vehicle> hashMapGraph) {
                HashMap<Vehicle, Integer> innerMap = hashMapGraph.getAdjacencyMap().get(source);
                for (Entry<Vehicle, Integer> innerEntry : innerMap.entries()) {
                    Vehicle destination = innerEntry.getKey();
                    graph.removeEdge(source, destination);
                }
                graph.removeVertex(source);
            }
        }
        return graph;

    }

    public static Graph<Vehicle> createGraph(
            Graph<Vehicle> graph, List<VanetEntry> vanetData) {
        for (VanetEntry vanetEntry : vanetData) {
            graph.addEdge(
                    vanetEntry.getSourceVehicle(),
                    vanetEntry.getDestinationVehicle(),
                    vanetEntry.getWeight());
        }
        return graph;
    }


    public static List<Vehicle> generateVehicleData(int maxVertices) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < maxVertices; i++) {
            vehicles.add(new Vehicle("V" + (i + 1), random.nextInt(MAX_SPEED)));
        }
        return vehicles;
    }

    public static List<VanetEntry> generateVanetData(int maxVertices) {
        List<Vehicle> vehicles = generateVehicleData(maxVertices);
        return generateEdges(vehicles);
    }

    public static List<VanetEntry> generateVanetData(List<Vehicle> vehicles) {
        return generateEdges(vehicles);
    }

    public static List<VanetEntry> generateEdges(List<Vehicle> vehicles) {
        List<VanetEntry> vanetData = new ArrayList<>();
        int threshold = (int) (vehicles.size() / 1.3);
        for (int j = 0; j < vehicles.size(); j++) {
            for (int i = 0; i < threshold; i++) {
                int index;
                do {
                    index = random.nextInt(vehicles.size() - 1);
                } while (index == j);
                Vehicle destinationVehicle = vehicles.get(index);
                int weight = random.nextInt((MAX_WEIGHT - MIN_WEIGHT) + 1) + MIN_WEIGHT;
                vanetData.add(new VanetEntry(vehicles.get(j), destinationVehicle, weight));
            }
        }
        return vanetData;
    }
}

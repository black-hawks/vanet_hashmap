package util;

import dataStructure.graph.Graph;
import dataStructure.hashMap.Entry;
import dataStructure.hashMap.LinkedListHashMap;
import simulation.Vehicle;

import java.util.*;

/**

 A utility class for generating random weighted graphs.
 */
public class GraphGeneration {

    /**
     The maximum weight for edges in the graph.
     */
    private static final int MAX_WEIGHT = 100;

    /**
     A random object used for generating random values.
     */
    private static Random random = new Random();

    /**
     Generates a random weighted graph with a given maximum number of vertices.
     @param maxVertices number of vertices in the graph.
     @return The generated graph.
     */
    public static Graph<Vehicle> generateRandomWeightedGraph(int maxVertices) {

        Graph<Vehicle > graph;

        //create vertices
        graph = createVertices(maxVertices);

        //creating the edges
        graph = createEdges(graph);

        return graph;
    }

    /**
     Creates a graph with a given maximum number of vertices.
     @param maxVertices number of vertices in the graph.
     @return The generated graph.
     */
    public static Graph<Vehicle> createVertices(int maxVertices){
        Graph<Vehicle> graph = new Graph<>();
        // Create vertices
        for (int i = 0; i < maxVertices; i++) {
            Vehicle V = new Vehicle("V" + (i + 1), random.nextInt(MAX_WEIGHT) + 1);
            graph.addVertex(V);
        }
        return graph;
    }

    /**
     Deletes a given number of vertices from a graph.
     @param graph The graph to delete vertices from.
     @param maxVertices The maximum number of vertices to delete.
     @return The updated graph.
     */
    public static Graph<Vehicle> deleteVertices(Graph<Vehicle> graph, int maxVertices){

        // create a hashset to store the randomly selected keys
        Set<Vehicle> randomKeys = new HashSet<Vehicle>();

        Object[] keys = graph.getAdjacencyMap().keySet().toArray();

        // generate random unique keys until the hashset contains 10 keys
        while (randomKeys.size() < maxVertices) {
            Vehicle v = (Vehicle) keys[random.nextInt(graph.getNumberOfVertices())];
            if (!randomKeys.contains(v)) {
                randomKeys.add(v);
            }
        }

        // delete vertices
        for (Vehicle source : randomKeys){
            LinkedListHashMap<Vehicle, Integer> innerMap = graph.getAdjacencyMap().get(source);
            for (Entry<Vehicle, Integer> innerEntry : innerMap.entrySet()){
                Vehicle destination = innerEntry.getKey();
                graph.removeEdge(source, destination);
            }
            graph.removeVertex(source);
        }
        return graph;

    }

    /**

     Creates edges in a graph with a given threshold for the number of edges per vertex.
     @param graph The graph to create edges in.
     @return The updated graph.
     */
    public static Graph<Vehicle> createEdges(Graph<Vehicle> graph) {

        int numberOfVertices = graph.getNumberOfVertices();
        int threshold = (int)( numberOfVertices/1.3);

        Set<Vehicle> vertices = graph.getAdjacencyMap().keySet();
        List<Vehicle> vehicleList = new ArrayList<>(vertices);

        for (Iterator<Vehicle> it = graph.getAdjacencyMap().keys(); it.hasNext(); ) {
            Vehicle source = it.next();
            Collections.shuffle(vehicleList);

            for (int i = 0; i < threshold; i++) {
                int index = i + random.nextInt(vehicleList.size() - i); // pick a random index from the remaining candidates
                Vehicle destination = vehicleList.get(index);
                int weight = random.nextInt(MAX_WEIGHT) + 1;
                graph.addEdge(source, destination, weight);
            }

        }
        return graph;
    }

}

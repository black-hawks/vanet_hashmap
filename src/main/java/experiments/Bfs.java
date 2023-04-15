package experiments;
import dataStructure.graph.Graph;
import dataStructure.graph.Route;
import dataStructure.graph.adjacencyListGraph.AdjacencyListGraph;
import dataStructure.graph.hashMapGraph.HashMapGraph;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.LinkedListHashMap;
import dataStructure.hashMap.TreeHashMap;
import util.GraphGeneration;
import util.Util;
import util.VanetEntry;
import java.util.List;

/**
 * This class contains experiments related to Breadth First Search algorithm on different graph implementations.
 */
public class Bfs {

    /**
     * Main method to run experiments on Breadth First Search algorithm on different graph implementations.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        int[] vertices = {10, 50, 100, 500, 1000, 2500};
        for (int i = 0; i < vertices.length; i++) {
            List<VanetEntry> vanetData = GraphGeneration.generateVanetData(vertices[i]);

            // Creating HashMap Graph based on Linked List
            HashMap<Vehicle, HashMap<Vehicle, Integer>> linkedListHashMap = new LinkedListHashMap<>(16, false);
            Graph<Vehicle> linkedListHashMapGraph = new HashMapGraph<>(linkedListHashMap);
            linkedListHashMapGraph = GraphGeneration.createGraph(linkedListHashMapGraph, vanetData);

            Vehicle source = Util.pickRandomVehicle(linkedListHashMapGraph);
            Vehicle destination = Util.pickRandomVehicle(linkedListHashMapGraph);

            // Calculating shortest path on HashMap Graph based on Linked List
            System.out.println("To calculate the shortest path from " + source.getVehicleId() + " to " + destination.getVehicleId() +
                    " in Graph with: " + vertices[i] + " vehicles and " + vanetData.size() + " edges:");
            long start = System.currentTimeMillis();
            Route<Vehicle> distances = linkedListHashMapGraph.shortestPath(source, destination);
            Util.printPath(distances);
            System.out.print("HashMap Graph based on Linked List took: " + (System.currentTimeMillis() - start) + "ms");
            System.out.println();

            // Creating HashMap Graph based on Tree
            HashMap<Vehicle, HashMap<Vehicle, Integer>> treeHashMap = new TreeHashMap<>(16, false);
            Graph<Vehicle> treeHashMapGraph = new HashMapGraph<>(linkedListHashMap);
            treeHashMapGraph = GraphGeneration.createGraph(treeHashMapGraph, vanetData);

            // Calculating shortest path on HashMap Graph based on Tree
            start = System.currentTimeMillis();
            distances = treeHashMapGraph.shortestPath(source, destination);
            Util.printPath(distances);
            System.out.println("HashMap Graph based on Tree took: " + (System.currentTimeMillis() - start) + "ms");

            // Creating Graph based on Adjacency List
            Graph<Vehicle> adjacencyListGraph = new AdjacencyListGraph<>();
            adjacencyListGraph = GraphGeneration.createGraph(adjacencyListGraph, vanetData);

            // Calculating shortest path on Graph based on Adjacency List
            start = System.currentTimeMillis();
            adjacencyListGraph.shortestPath(source, destination);
            Util.printPath(distances);
            System.out.println("Graph based on Adjacency List took: " + (System.currentTimeMillis() - start) + "ms");

            System.out.println();
        }
    }
}
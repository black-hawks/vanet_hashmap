package experiments;

import dataStructure.graph.Graph;
import dataStructure.graph.adjacencyListGraph.AdjacencyListGraph;
import dataStructure.graph.hashMapGraph.HashMapGraph;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.LinkedListHashMap;
import dataStructure.hashMap.TreeHashMap;
import util.GraphGeneration;
import util.VanetEntry;
import java.util.List;

/**
 * The AddEdge class contains the main method that generates and compares different graphs
 * based on different data structures for storing graph data.
 * It uses the GraphGeneration utility class to generate Vanet data for each number of vertices.
 */
public class AddEdge {
    /**
     * The main method that generates and compares different graphs based on different data structures
     * for storing graph data. It uses the GraphGeneration utility class to generate Vanet data for each
     * number of vertices.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Array of different number of vertices
        int[] vertices = {10, 50, 100, 500, 1000, 2500, 5000};

        // Loop through each number of vertices and generate graphs based on different data structures
        for (int i = 0; i < vertices.length; i++) {
            List<VanetEntry> vanetData = GraphGeneration.generateVanetData(vertices[i]);
            System.out.println("To store " + vertices[i] + " vehicles and " + vanetData.size() + " edges:");

            // Graph based on LinkedListHashMap
            long start = System.currentTimeMillis();
            HashMap<Vehicle, HashMap<Vehicle, Integer>> linkedListHashMap = new LinkedListHashMap<>();
            Graph<Vehicle> linkedListHashMapGraph = new HashMapGraph<>(linkedListHashMap);
            GraphGeneration.createGraph(linkedListHashMapGraph, vanetData);
            System.out.println("HashMap Graph based on Linked List took: " + (System.currentTimeMillis() - start) + "ms");

            // Graph based on TreeHashMap
            start = System.currentTimeMillis();
            HashMap<Vehicle, HashMap<Vehicle, Integer>> treeHashMap = new TreeHashMap<>();
            Graph<Vehicle> treeHashMapGraph = new HashMapGraph<>(treeHashMap);
            GraphGeneration.createGraph(treeHashMapGraph, vanetData);
            System.out.println("HashMap Graph based on Tree took: " + (System.currentTimeMillis() - start) + "ms");

            // Graph based on AdjacencyListGraph
            start = System.currentTimeMillis();
            Graph<Vehicle> adjacencyListGraph = new AdjacencyListGraph<>();
            GraphGeneration.createGraph(adjacencyListGraph, vanetData);
            System.out.println("Adjacency List Graph took: " + (System.currentTimeMillis() - start) + "ms");

            System.out.println();
        }
    }
}

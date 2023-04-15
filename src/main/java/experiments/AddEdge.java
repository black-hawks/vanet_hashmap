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

public class AddEdge {
    public static void main(String[] args) {
        int[] vertices = {10, 50, 100, 500, 1000, 2500, 5000};
        for (int i = 0; i < vertices.length; i++) {
            List<VanetEntry> vanetData = GraphGeneration.generateVanetData(vertices[i]);
            System.out.println("To store " + vertices[i] + " vehicles and " + vanetData.size() + " edges:");

            long start = System.currentTimeMillis();
            HashMap<Vehicle, HashMap<Vehicle, Integer>> linkedListHashMap = new LinkedListHashMap<>();
            Graph<Vehicle> linkedListHashMapGraph = new HashMapGraph<>(linkedListHashMap);
            GraphGeneration.createGraph(linkedListHashMapGraph, vanetData);
            System.out.println("HashMap Graph based on Linked List took: " + (System.currentTimeMillis() - start) + "ms");

            start = System.currentTimeMillis();
            HashMap<Vehicle, HashMap<Vehicle, Integer>> treeHashMap = new TreeHashMap<>();
            Graph<Vehicle> treeHashMapGraph = new HashMapGraph<>(treeHashMap);
            GraphGeneration.createGraph(treeHashMapGraph, vanetData);
            System.out.println("HashMap Graph based on Tree took: " + (System.currentTimeMillis() - start) + "ms");

            start = System.currentTimeMillis();
            Graph<Vehicle> adjacencyListGraph = new AdjacencyListGraph<>();
            GraphGeneration.createGraph(adjacencyListGraph, vanetData);
            System.out.println("Adjacency List Graph took: " + (System.currentTimeMillis() - start) + "ms");

            System.out.println();
        }
    }

}

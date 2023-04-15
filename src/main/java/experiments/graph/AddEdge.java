package experiments.graph;

import dataStructure.graph.Graph;
import dataStructure.graph.hashMapGraph.HashMapGraph;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.LinkedListHashMap;
import dataStructure.hashMap.TreeHashMap;
import simulation.Vehicle;
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
            HashMap<Vehicle, HashMap<Vehicle, Integer>> linkedListHashMap = new LinkedListHashMap<>(16, false);
            Graph<Vehicle> linkedListHashMapGraph = new HashMapGraph<>(linkedListHashMap);
            linkedListHashMapGraph = GraphGeneration.createGraph(linkedListHashMapGraph, vanetData);
            System.out.println("HashMap Graph based on Linked List took: " + (System.currentTimeMillis() - start) + "ms");

            start = System.currentTimeMillis();
            HashMap<Vehicle, HashMap<Vehicle, Integer>> treeHashMap = new TreeHashMap<>(16, false);
            Graph<Vehicle> treeHashMapGraph = new HashMapGraph<>(linkedListHashMap);
            treeHashMapGraph = GraphGeneration.createGraph(treeHashMapGraph, vanetData);
            System.out.println("HashMap Graph based on Tree took: " + (System.currentTimeMillis() - start) + "ms");

            System.out.println();
        }
    }

}

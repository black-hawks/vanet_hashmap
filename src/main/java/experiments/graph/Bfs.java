package experiments.graph;

import dataStructure.graph.Graph;
import dataStructure.graph.Route;
import dataStructure.graph.adjacencyListGraph.AdjacencyListGraph;
import dataStructure.graph.hashMapGraph.HashMapGraph;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.LinkedListHashMap;
import dataStructure.hashMap.TreeHashMap;
import simulation.Vehicle;
import util.GraphGeneration;
import util.Util;
import util.VanetEntry;

import java.util.List;

public class Bfs {

    public static void main(String[] args) {
        int[] vertices = {10, 50, 100, 500, 1000, 2500};
        for (int i = 0; i < vertices.length; i++) {
            List<VanetEntry> vanetData = GraphGeneration.generateVanetData(vertices[i]);

            HashMap<Vehicle, HashMap<Vehicle, Integer>> linkedListHashMap = new LinkedListHashMap<>(16, false);
            Graph<Vehicle> linkedListHashMapGraph = new HashMapGraph<>(linkedListHashMap);
            linkedListHashMapGraph = GraphGeneration.createGraph(linkedListHashMapGraph, vanetData);


            Vehicle source = Util.pickRandomVehicle(linkedListHashMapGraph);
            Vehicle destination = Util.pickRandomVehicle(linkedListHashMapGraph);


            System.out.println("To calculate  the shortest path from  " + source.getVehicleId() + " to " + destination.getVehicleId() +
                    " in Graph with : " + vertices[i] + " vehicles and " + vanetData.size() + " edges:");
            long start = System.currentTimeMillis();
            Route<Vehicle> distances = linkedListHashMapGraph.shortestPath(source,destination);
            Util.printPath(distances);
            System.out.print("HashMap Graph based on Linked List took: " + (System.currentTimeMillis() - start) + "ms");
            System.out.println();

            HashMap<Vehicle, HashMap<Vehicle, Integer>> treeHashMap = new TreeHashMap<>(16, false);
            Graph<Vehicle> treeHashMapGraph = new HashMapGraph<>(linkedListHashMap);
            treeHashMapGraph = GraphGeneration.createGraph(treeHashMapGraph, vanetData);

            start = System.currentTimeMillis();
            distances = treeHashMapGraph.shortestPath(source,destination);
            Util.printPath(distances);
            System.out.println("HashMap Graph based on Tree took: " + (System.currentTimeMillis() - start) + "ms");

            Graph<Vehicle> adjacencyListGraph = new AdjacencyListGraph<>();
            adjacencyListGraph = GraphGeneration.createGraph(adjacencyListGraph, vanetData);

            start = System.currentTimeMillis();
            adjacencyListGraph.shortestPath(source,destination);
            Util.printPath(distances);
            System.out.println("Graph based on Adjacency List took: " + (System.currentTimeMillis() - start) + "ms");

            System.out.println();
        }
    }

}

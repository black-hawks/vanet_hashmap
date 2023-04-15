package experiments;

import dataStructure.graph.Graph;
import dataStructure.graph.adjacencyListGraph.AdjacencyListGraph;
import dataStructure.graph.hashMapGraph.HashMapGraph;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.LinkedListHashMap;
import dataStructure.hashMap.TreeHashMap;
import simulation.Vehicle;
import util.GraphGeneration;
import util.VanetEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeleteEdge {
    static Random random = new Random();

    public static void main(String[] args) {
        int[] vertices = {10, 50, 100, 500, 1000, 2500, 5000};
        for (int i = 0; i < vertices.length; i++) {
            List<VanetEntry> vanetData = GraphGeneration.generateVanetData(vertices[i]);
            List<VanetEntry> edgesToBeDeleted = getEdgesToBeDeleted(vanetData, (int) (vanetData.size() * 0.33));
            System.out.println("To delete " + edgesToBeDeleted.size() + " edges in graph of " + vertices[i]
                    + " vehicles and " + vanetData.size() + " edges:");


            HashMap<Vehicle, HashMap<Vehicle, Integer>> linkedListHashMap = new LinkedListHashMap<>(16, false);
            Graph<Vehicle> linkedListHashMapGraph = new HashMapGraph<>(linkedListHashMap);
            GraphGeneration.createGraph(linkedListHashMapGraph, vanetData);
            long start = System.nanoTime();
            for (VanetEntry vanetEntry : edgesToBeDeleted) {
                linkedListHashMapGraph.removeEdge(vanetEntry.getSourceVehicle(), vanetEntry.getDestinationVehicle());
            }
            System.out.println("HashMap Graph based on Linked List took: " + ((double) (System.nanoTime() - start) / 1000000) + "ms");

            HashMap<Vehicle, HashMap<Vehicle, Integer>> treeHashMap = new TreeHashMap<>(16, false);
            Graph<Vehicle> treeHashMapGraph = new HashMapGraph<>(treeHashMap);
            GraphGeneration.createGraph(treeHashMapGraph, vanetData);
            start = System.nanoTime();
            for (VanetEntry vanetEntry : edgesToBeDeleted) {
                treeHashMapGraph.removeEdge(vanetEntry.getSourceVehicle(), vanetEntry.getDestinationVehicle());
            }
            System.out.println("HashMap Graph based on Tree took: " + ((double) (System.nanoTime() - start) / 1000000) + "ms");

            Graph<Vehicle> adjacencyListGraph = new AdjacencyListGraph<>();
            GraphGeneration.createGraph(adjacencyListGraph, vanetData);
            start = System.nanoTime();
            for (VanetEntry vanetEntry : edgesToBeDeleted) {
                adjacencyListGraph.removeEdge(vanetEntry.getSourceVehicle(), vanetEntry.getDestinationVehicle());
            }
            System.out.println("Adjacency List Graph took: " + ((double) (System.nanoTime() - start) / 1000000) + "ms");

            System.out.println();
        }
    }

    static List<VanetEntry> getEdgesToBeDeleted(List<VanetEntry> vanetData, int numberOfEdges) {
        List<VanetEntry> edgesToBeDeleted = new ArrayList<>();
        for (int i = 0; i < numberOfEdges; i++) {
            edgesToBeDeleted.add(vanetData.get(random.nextInt(numberOfEdges)));
        }
        return edgesToBeDeleted;
    }
}

package experiments;
import dataStructure.graph.Graph;
import dataStructure.graph.adjacencyListGraph.AdjacencyListGraph;
import dataStructure.graph.hashMapGraph.HashMapGraph;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.LinkedListHashMap;
import dataStructure.hashMap.TreeHashMap;
import util.GraphGeneration;
import util.VanetEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class to test the performance of deleting edges from different types of graphs.
 */
public class DeleteEdge {
    // A random number generator
    static Random random = new Random();

    /**
     * The main method of the class
     *
     * @param args the command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        // An array with the number of vertices to be used in each test
        int[] vertices = {10, 50, 100, 500, 1000, 2500, 5000};
        for (int i = 0; i < vertices.length; i++) {
            // Generate the VANET data
            List<VanetEntry> vanetData = GraphGeneration.generateVanetData(vertices[i]);
            // Select the edges to be deleted
            List<VanetEntry> edgesToBeDeleted = getEdgesToBeDeleted(vanetData, (int) (vanetData.size() * 0.33));
            System.out.println("To delete " + edgesToBeDeleted.size() + " edges in graph of " + vertices[i]
                    + " vehicles and " + vanetData.size() + " edges:");

            // Test the performance of deleting edges from a graph based on a linked list hash map
            HashMap<Vehicle, HashMap<Vehicle, Integer>> linkedListHashMap = new LinkedListHashMap<>(16, false);
            Graph<Vehicle> linkedListHashMapGraph = new HashMapGraph<>(linkedListHashMap);
            GraphGeneration.createGraph(linkedListHashMapGraph, vanetData);
            long start = System.nanoTime();
            for (VanetEntry vanetEntry : edgesToBeDeleted) {
                linkedListHashMapGraph.removeEdge(vanetEntry.getSourceVehicle(), vanetEntry.getDestinationVehicle());
            }
            System.out.println("HashMap Graph based on Linked List took: " + ((double) (System.nanoTime() - start) / 1000000) + "ms");

            // Test the performance of deleting edges from a graph based on a tree hash map
            HashMap<Vehicle, HashMap<Vehicle, Integer>> treeHashMap = new TreeHashMap<>(16, false);
            Graph<Vehicle> treeHashMapGraph = new HashMapGraph<>(treeHashMap);
            GraphGeneration.createGraph(treeHashMapGraph, vanetData);
            start = System.nanoTime();
            for (VanetEntry vanetEntry : edgesToBeDeleted) {
                treeHashMapGraph.removeEdge(vanetEntry.getSourceVehicle(), vanetEntry.getDestinationVehicle());
            }
            System.out.println("HashMap Graph based on Tree took: " + ((double) (System.nanoTime() - start) / 1000000) + "ms");

            // Test the performance of deleting edges from an adjacency list graph
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
    /**
     * This method takes a list of VanetEntry objects and an integer number of edges to be deleted, and returns
     * a list of VanetEntry objects representing edges that will be deleted from the graph.
     * @param vanetData A list of VanetEntry objects representing edges in the graph
     * @param numberOfEdges An integer number of edges to be deleted from the graph
     * @return A list of VanetEntry objects representing edges to be deleted from the graph
     */

    static List<VanetEntry> getEdgesToBeDeleted(List<VanetEntry> vanetData, int numberOfEdges) {
        List<VanetEntry> edgesToBeDeleted = new ArrayList<>();
        for (int i = 0; i < numberOfEdges; i++) {
            edgesToBeDeleted.add(vanetData.get(random.nextInt(numberOfEdges)));
        }
        return edgesToBeDeleted;
    }
}
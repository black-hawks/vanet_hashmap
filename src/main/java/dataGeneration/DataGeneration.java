package dataGeneration;

import dataStructure.graph.Graph;
import dataStructure.hashMap.Entry;
import dataStructure.hashMap.HashMap;
import simulation.Vehicle;

import java.util.Iterator;
import java.util.Random;


public class DataGeneration {

    private static final int MAX_WEIGHT = 100;

    public static Graph<Vehicle> generateRandomWeightedGraph(int maxVertices) {
        Graph<Vehicle > graph = new Graph<Vehicle>();
        Random random = new Random();

        // Create vertices
        for (int i = 0; i < maxVertices; i++) {
            Vehicle V = new Vehicle("V" + (i + 1), random.nextInt(MAX_WEIGHT) + 1);
            graph.addVertex(V);
        }

        // Create edges
        for (Iterator<Vehicle> it = graph.getAdjacencyMap().keys(); it.hasNext(); ) {
            Vehicle source = it.next();
            for (Iterator<Vehicle> iter = graph.getAdjacencyMap().keys(); iter.hasNext(); ) {
                Vehicle destination = iter.next();
                if (source != destination) {

                    int weight = random.nextInt(MAX_WEIGHT) + 1;
                    graph.addEdge(source,destination, weight);

                }
            }
        }

        return graph;
    }
}

package util;

import dataStructure.graph.Graph;
import dataStructure.graph.hashMapGraph.HashMapGraph;
import dataStructure.graph.Route;
import dataStructure.hashMap.Entry;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.LinkedListHashMap;
import simulation.Vehicle;

import java.util.Random;

/**

 Utility class for various graph operations
 */
public class Util {

    /**
     * Print the graph in the form of adjacency list
     * @param graph the graph to be printed
     */
    public static void printGraph(HashMapGraph<Vehicle> graph){
        System.out.println("Random Weighted HashMapGraph:");
        for (Entry<Vehicle, HashMap<Vehicle, Integer>> entry : graph.getAdjacencyMap().entries()) {
            Vehicle key = entry.getKey();
            System.out.print(key.getVehicleId() + " : ");
            HashMap<Vehicle, Integer> innerMap = entry.getValue();
            for(Entry<Vehicle, Integer> innerEntry : innerMap.entries()){
                System.out.print( "< " + innerEntry.getKey().getVehicleId() + " , "+ innerEntry.getValue() + " > ");
            }
            System.out.println();
        }
    }

    /**
     * Pick a random key from the graph
     * @param graph the graph to pick from
     * @return a randomly picked key
     */
    public static Vehicle pickRandomVehicle(Graph<Vehicle> graph){
        Object[] keys = graph.getVertices().toArray();
        Random rand = new Random();
        Vehicle randomVehicle = (Vehicle) keys[rand.nextInt(keys.length)];

        return randomVehicle;
    }

    /**
     * Print the shortest path distances and paths for each key in the graph
     * @param distances the shortest path distances and paths for each key
     */
    public static void printShortestPath(LinkedListHashMap<Vehicle, Route<Vehicle>> distances){
        for(Entry<Vehicle, Route<Vehicle>> innerEntry : distances.entries()){
            System.out.print( innerEntry.getKey().getVehicleId() + " , ");
            printPath(innerEntry.getValue());
            System.out.println();
        }
    }

    /**
     * Print the path
     * @param route the path to be printed
     */
    public static void printPath(Route<Vehicle> route){
        System.out.print("Shortest Path  : ");
        for(Vehicle v : route.getPath()){
            System.out.print(v.getVehicleId() + ", ");
        }
        System.out.println();
        System.out.println("Shortest Path : " + route.getDistance());
    }
}

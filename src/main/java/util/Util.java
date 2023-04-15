package util;

import dataStructure.graph.Graph;
import dataStructure.graph.hashMapGraph.HashMapGraph;
import dataStructure.graph.Route;
import dataStructure.hashMap.Entry;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.LinkedListHashMap;
import experiments.Vehicle;
import java.util.Random;

/**
 * This class contains various utility methods for working with graphs.
 */
public class Util {

    /**
     * Prints the given graph in the form of an adjacency list.
     *
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
     * Picks a random key from the given graph.
     *
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
     * Prints the shortest path distances and paths for each key in the given map.
     *
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
     * Prints the given path.
     *
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
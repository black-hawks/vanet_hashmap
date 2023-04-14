package util;

import dataStructure.graph.Graph;
import dataStructure.graph.Route;
import dataStructure.hashMap.Entry;
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
    public static void printGraph(Graph<Vehicle> graph){
        System.out.println("Random Weighted Graph:");
        for (Entry<Vehicle, LinkedListHashMap<Vehicle, Integer>> entry : graph.getAdjacencyMap().entrySet()) {
            Vehicle key = entry.getKey();
            System.out.print(key.getVehicleId() + " : ");
            LinkedListHashMap<Vehicle, Integer> innerMap = entry.getValue();
            for(Entry<Vehicle, Integer> innerEntry : innerMap.entrySet()){
                System.out.print( "< " + innerEntry.getKey().getVehicleId() + " , "+ innerEntry.getValue() + " > ");
            }
            System.out.println();
        }
    }

    /**
     * Pick a random vertex from the graph
     * @param graph the graph to pick from
     * @return a randomly picked vertex
     */
    public static Vehicle pickRandomVehicle(Graph<Vehicle> graph){
        Object[] keys = graph.getAdjacencyMap().keySet().toArray();
        Random rand = new Random();
        Vehicle randomVehicle = (Vehicle) keys[rand.nextInt(keys.length)];

        return randomVehicle;
    }

    /**
     * Print the shortest path distances and paths for each vertex in the graph
     * @param distances the shortest path distances and paths for each vertex
     */
    public static void printShortestPath(LinkedListHashMap<Vehicle, Route<Vehicle>> distances){
        for(Entry<Vehicle, Route<Vehicle>> innerEntry : distances.entrySet()){
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
        System.out.print(route.getDistance() +" :");
        for(Vehicle v : route.getPath()){
            System.out.print(v.getVehicleId() + ", ");
        }
    }
}

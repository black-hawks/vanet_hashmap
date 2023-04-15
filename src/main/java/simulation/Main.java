package simulation;

import dataStructure.graph.hashMapGraph.HashMapGraph;
import dataStructure.graph.hashMapGraph.Route;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.TreeHashMap;
import util.GraphGeneration;
import util.Util;

import java.util.Random;

/**
 * The Main class simulates a scenario where vehicles in a range send messages to each other
 * using a shortest path algorithm.
 */
public class Main {

    /**
     * Simulates the message sending scenario for a given number of vertices.
     *
     * @param numberOfVertices the number of vehicles in the range
     */
    public static void simulate(int numberOfVertices) {

        HashMapGraph<Vehicle> graph;
        Random random = new Random();
        int delCount = (int) (numberOfVertices / 1.25);


        System.out.println();
        System.out.println("Simulating for code when there are " + numberOfVertices + " Vehicles in the range");
        HashMap<Vehicle, HashMap<Vehicle, Integer>> hashMap = new TreeHashMap<>();
        //HashMapGraph Generation
        graph = GraphGeneration.generateRandomWeightedGraph(hashMap, numberOfVertices);

        //picking two random Vehicle
        Vehicle randomVehicle = Util.pickRandomVehicle(graph);
        Vehicle destinationVehicle = Util.pickRandomVehicle(graph);

        //Shortest path from a given source to destination;
        System.out.println("Message is sent from " + randomVehicle.getVehicleId() + " to " + destinationVehicle.getVehicleId() + " destination in the shortest path");
        Route<Vehicle> path = graph.shortestPath(randomVehicle, destinationVehicle);
        Util.printPath(path);


        //Deleting Vertices from the HashMapGraph
        graph = GraphGeneration.deleteVertices(graph, delCount);


        //pick a random key from the hashmap
        randomVehicle = Util.pickRandomVehicle(graph);
        destinationVehicle = Util.pickRandomVehicle(graph);

        //Shortest path from a given source to destination;
        System.out.println();
        System.out.println("Message is sent from " + randomVehicle.getVehicleId() + " to " + destinationVehicle.getVehicleId() + " destination in the shortest path");
        path = graph.shortestPath(randomVehicle, destinationVehicle);
        Util.printPath(path);

        System.out.println();

    }

    /**
     * The main method that executes the simulation for different numbers of vertices.
     */
    public static void main(String[] args) {

        simulate(100);
        simulate(500);
        simulate(1000);
        simulate(1500);
        simulate(2500);
        simulate(5000);

    }
}

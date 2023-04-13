package simulation;

import util.GraphGeneration;
import java.util.*;
import dataStructure.graph.*;
import util.Util;

/**

 The Main class simulates a scenario where vehicles in a range send messages to each other
 using a shortest path algorithm.
 */
public class Main {

    /**
     * Simulates the message sending scenario for a given number of vertices.
     * @param numberOfVertices the number of vehicles in the range
     */
    public static void simulate(int numberOfVertices){

        Graph<Vehicle> graph;
        Random random = new Random();
        int delCount = (int)(numberOfVertices/1.25);


        System.out.println();
        System.out.println("Simulating for code when there are "+ numberOfVertices + " Vehicles in the range");

        //Graph Generation
        graph = GraphGeneration.generateRandomWeightedGraph(numberOfVertices);

        //picking two random Vehicle
        Vehicle randomVehicle = Util.pickRandomVehicle(graph);
        Vehicle destinationVehicle = Util.pickRandomVehicle(graph);

        //Shortest path from a given source to destination;
        System.out.println("Message is sent from "+randomVehicle.getVehicleId() +" to "+destinationVehicle.getVehicleId() +" destination in the shortest path");
        Route<Vehicle> path = graph.shortestPath(randomVehicle,destinationVehicle);
        Util.printPath(path);


        //Deleting Vertices from the Graph
        graph = GraphGeneration.deleteVertices(graph,delCount);


        //pick a random key from the hashmap
        randomVehicle = Util.pickRandomVehicle(graph);
        destinationVehicle = Util.pickRandomVehicle(graph);

        //Shortest path from a given source to destination;
        System.out.println();
        System.out.println("Message is sent from "+randomVehicle.getVehicleId() +" to "+destinationVehicle.getVehicleId() +" destination in the shortest path");
        path = graph.shortestPath(randomVehicle,destinationVehicle);
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

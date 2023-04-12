package Simulation;

import dataStructure.hashMap.*;

import java.util.Iterator;

import dataStructure.graph.*;

public class Main {

    public static void main(String[] args) {

        Graph<Vehicle, Integer> graph = new Graph<Vehicle, Integer>();

        HashMap<Vehicle, HashMap<Vehicle, Integer>> hashMap;
        Vehicle V1 = new Vehicle("V1", 50);
        Vehicle V2 = new Vehicle("V2", 60);
        Vehicle V3 = new Vehicle("V3", 90);
        Vehicle V4 = new Vehicle("V4", 70);
        Vehicle V5 = new Vehicle("V5", 80);

        HashMap<Vehicle, HashMap<Vehicle, Integer>> hashMapValues;
        // hashMapValues.

        graph.addVertex(V1);
        graph.addVertex(V5);
        graph.addVertex(V3);
        graph.addVertex(V4);
        graph.addVertex(V2);

        graph.addEdge(V1, V5, 6);
        graph.addEdge(V1, V2, 7);
        graph.addEdge(V1, V4, 6);
        graph.addEdge(V1, V3, 5);

        graph.addEdge(V5, V4, 1);
        graph.addEdge(V5, V2, 10);

        graph.addEdge(V2, V3, 2);
        graph.addEdge(V2, V4, 9);

        graph.addEdge(V4, V3, 4);

//        Iterator<Entry<Vehicle, HashMap<Vehicle, Integer>>> iterator = graph.getAdjacencyMap().entries();
//        while (iterator.hasNext()) {
//            Entry<Vehicle, HashMap<Vehicle, Integer>> entry = iterator.next();
//            System.out.println(entry.getKey().getVehicleId());
//            Iterator<Entry <Vehicle, Integer>> innerMapIterator = entry.getValue().entries();
//            while(innerMapIterator.hasNext()){
//                System.out.println(entry.getKey().getVehicleId() + ": " + entry.getValue());
//            }
//        }

        for (Entry<Vehicle, HashMap<Vehicle, Integer>> entry : graph.getAdjacencyMap().entrySet()) {
            Vehicle key = entry.getKey();
            System.out.print(key.getVehicleId() + " : ");
            HashMap<Vehicle, Integer> innerMap = entry.getValue();
            for(Entry<Vehicle, Integer> innerEntry : innerMap.entrySet()){
                System.out.print( "< " + innerEntry.getKey().getVehicleId() + " , "+ innerEntry.getValue() + " > ");
            }
            System.out.println();
        }
    }

//    public static void BFS(Graph graph, Vehicle v) {
//
//    }

}

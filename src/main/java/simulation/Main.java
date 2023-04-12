package simulation;

import dataGeneration.DataGeneration;
import dataStructure.hashMap.*;

import java.util.*;

import dataStructure.graph.*;
import dataStructure.hashMap.HashMap;

public class Main {

    public static void main(String[] args) {

        Graph<Vehicle> graph = new Graph<>();
        Random random = new Random();



        //Task is to generate the random vehicle ID and speed - write a function to do this

        graph = DataGeneration.generateRandomWeightedGraph(10);

        // Print graph
        System.out.println("Random Weighted Graph:");
        for (Entry<Vehicle, HashMap<Vehicle, Integer>> entry : graph.getAdjacencyMap().entrySet()) {
            Vehicle key = entry.getKey();
            System.out.print(key.getVehicleId() + " : ");
            HashMap<Vehicle, Integer> innerMap = entry.getValue();
            for(Entry<Vehicle, Integer> innerEntry : innerMap.entrySet()){
                System.out.print( "< " + innerEntry.getKey().getVehicleId() + " , "+ innerEntry.getValue() + " > ");
            }
            System.out.println();
        }

        List<Vehicle> vID = new ArrayList<>();
        for (Iterator<Vehicle> it = graph.getAdjacencyMap().keys(); it.hasNext(); ) {
            Vehicle source = it.next();
            vID.add(source);

        }

        //pick a random key from the hashmap
        Object[] keys = graph.getAdjacencyMap().keySet().toArray();
        Random rand = new Random();
        Vehicle sourceVehicle = (Vehicle) keys[rand.nextInt(keys.length)];

        //Bfs
        HashMap<Vehicle, Integer> bfs = bfs(graph,sourceVehicle);

//        //printing shortest path weight from specific node to all the other nodes
//        System.out.println("Shortest path from the "+sourceVehicle.getVehicleId() + " to all the other Vehicles");
//        for (Entry<Vehicle, Integer> distance : bfs.entrySet()) {
//            System.out.println( "< " + distance.getKey().getVehicleId() + " , "+ distance.getValue() + " > ");;
//        }
    }

        public static HashMap<Vehicle, Integer> bfs( Graph<Vehicle> graph, Vehicle source) {
        HashMap<Vehicle, Integer> distances = new HashMap<Vehicle, Integer>();
        Queue<Vehicle> queue = new LinkedList<>();
        Set<Vehicle> visited = new HashSet<>();
        HashMap<Vehicle, Vehicle> parents = new HashMap<Vehicle, Vehicle>();

        queue.offer(source);
        visited.add(source);
        distances.put(source, 0);
        parents.put(source, null);


        while (!queue.isEmpty()) {
            Vehicle current = queue.poll();
//            System.out.print(current.getVehicleId() + " ");

            HashMap<Vehicle, Integer> innerMap = graph.getAdjacencyMap().get(current);
            for (Entry<Vehicle, Integer> innerEntry : innerMap.entrySet()) {
                Vehicle neighbor = innerEntry.getKey();
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    distances.put(neighbor, distances.get(current) + innerEntry.getValue());
                    parents.put(neighbor, current);
                } else if (distances.get(neighbor) > distances.get(current) + innerEntry.getValue()) {
                    distances.put(neighbor, distances.get(current) + innerEntry.getValue());
                    parents.put(neighbor, current);
                }
            }
            // Set distances of unvisited vertices to null
            for (Entry<Vehicle, Integer> innerEntry : innerMap.entrySet()) {
                if (!visited.contains(innerEntry.getKey())) {
                    distances.put(innerEntry.getKey(), null);
                }
            }
        }

        System.out.println();

            System.out.println("Shortest path from the " + source.getVehicleId() + " to all the other Vehicles:");

            for (Entry<Vehicle, Integer> innerEntry : distances.entrySet()) {
                Vehicle vh = innerEntry.getKey();
                System.out.print("<" + vh.getVehicleId() + ", " + innerEntry.getValue() + "> : ");

                List<Vehicle> path = new ArrayList<>();
                Vehicle current = vh;
                while (current != null) {
                    path.add(current);
                    current = parents.get(current);
                }
                Collections.reverse(path);
                for(Vehicle v: path){
                    System.out.print(v.getVehicleId() + " ,");
                }
                System.out.println();
            }
        return distances;
    }

}



//        Code for the testing purpose

//        HashMap<Vehicle, HashMap<Vehicle, Integer>> hashMap;
//        Vehicle V1 = new Vehicle("V1", 50);
//        Vehicle V2 = new Vehicle("V2", 60);
//        Vehicle V3 = new Vehicle("V3", 90);
//        Vehicle V4 = new Vehicle("V4", 70);
//        Vehicle V5 = new Vehicle("V5", 80);
//
//        graph.addVertex(V1);
//        graph.addVertex(V5);
//        graph.addVertex(V3);
//        graph.addVertex(V4);
//        graph.addVertex(V2);
//
//        graph.addEdge(V1, V5, 6);
//        graph.addEdge(V1, V2, 7);
//        graph.addEdge(V1, V4, 6);
//        graph.addEdge(V1, V3, 5);
//
//        graph.addEdge(V5, V4, 1);
//        graph.addEdge(V5, V2, 10);
//
//        graph.addEdge(V2, V3, 2);
//        graph.addEdge(V2, V4, 9);
//
//        graph.addEdge(V4, V3, 4);
//
////        Iterator<Entry<Vehicle, HashMap<Vehicle, Integer>>> iterator = graph.getAdjacencyMap().entries();
////        while (iterator.hasNext()) {
////            Entry<Vehicle, HashMap<Vehicle, Integer>> entry = iterator.next();
////            System.out.println(entry.getKey().getVehicleId());
////            Iterator<Entry <Vehicle, Integer>> innerMapIterator = entry.getValue().entries();
////            while(innerMapIterator.hasNext()){
////                System.out.println(entry.getKey().getVehicleId() + ": " + entry.getValue());
////            }
////        }
//
//        for (Entry<Vehicle, HashMap<Vehicle, Integer>> entry : graph.getAdjacencyMap().entrySet()) {
//            Vehicle key = entry.getKey();
//            System.out.print(key.getVehicleId() + " : ");
//            HashMap<Vehicle, Integer> innerMap = entry.getValue();
//            for(Entry<Vehicle, Integer> innerEntry : innerMap.entrySet()){
//                System.out.print( "< " + innerEntry.getKey().getVehicleId() + " , "+ innerEntry.getValue() + " > ");
//            }
//            System.out.println();
//        }
//


//        System.out.println("Shortest Path from "+sourceVehicle.getVehicleId() +" to "+destinationVehicle.getVehicleId() +" destination");
//        for(Vehicle v : shortestPath){
//            System.out.print(v.getVehicleId()+" ->");
//        }


// 1000 -
//we will get BFS and DFS

// remove- 100

// 900
// 4100


//
//    public static void bfs( Graph<Vehicle> graph, Vehicle source) {
//        HashMap<Vehicle, Integer> distances = new HashMap<Vehicle, Integer>();
//        Queue<Vehicle> queue = new LinkedList<>();
//        Set<Vehicle> visited = new HashSet<>();
//        HashMap<Vehicle, Vehicle> parents = new HashMap<Vehicle, Vehicle>();
//
//        queue.offer(source);
//        visited.add(source);
//        distances.put(source, 0);
//        parents.put(source, null);
//
//
//        while (!queue.isEmpty()) {
//            Vehicle current = queue.poll();
//            System.out.print(current.getVehicleId() + " ");
//
//            HashMap<Vehicle, Integer> innerMap = graph.getAdjacencyMap().get(current);
//            for (Entry<Vehicle, Integer> innerEntry : innerMap.entrySet()) {
//                Vehicle neighbor = innerEntry.getKey();
//                if (!visited.contains(neighbor)) {
//                    queue.offer(neighbor);
//                    visited.add(neighbor);
//                    distances.put(neighbor, distances.get(current) + innerEntry.getValue());
//                    parents.put(neighbor, current);
//                } else if (distances.get(neighbor) > distances.get(current) + innerEntry.getValue()) {
//                    distances.put(neighbor, distances.get(current) + innerEntry.getValue());
//                    parents.put(neighbor, current);
//                }
//            }
//            // Set distances of unvisited vertices to null
//            for (Entry<Vehicle, Integer> innerEntry : innerMap.entrySet()) {
//                if (!visited.contains(innerEntry.getKey())) {
//                    distances.put(innerEntry.getKey(), null);
//                }
//            }
//        }
//
//        System.out.println();
//
//        System.out.println("Shortest path from the "+source.getVehicleId() + " to all the other Vehicles");
//
//        for (Entry<Vehicle, Integer> innerEntry : distances.entrySet()) {
//            System.out.println( "< " + innerEntry.getKey().getVehicleId() + " , "+ innerEntry.getValue() + " > ");;
//        }
//    }

//    int randomIndex = random.nextInt(vID.size());
//        Vehicle randomVehicle = vID.get(randomIndex);


//    public void bfs(Graph<Vehicle, Integer> graph, Vehicle source) {
//        boolean visited[] = new boolean[graph.getNumberOfVertices()];
//        Queue<Vehicle> queue = new LinkedList<>();
//        queue.add(source);
//        visited[];
//
//        while (!queue.isEmpty()) {
//            int current = queue.poll();
//            System.out.print(current + " ");
//
//            List<Edge> edges = adjacencyMap.get(current);
//            for (Edge edge : edges) {
//                int destination = edge.destination;
//                if (!visited.contains(destination)) {
//                    visited.add(destination);
//                    queue.add(destination);
//                }
//            }
//        }
//    }
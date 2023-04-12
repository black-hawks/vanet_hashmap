package dataStructure.graph;

import dataStructure.hashMap.Entry;
import dataStructure.hashMap.HashMap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Graph<K> {
    private final HashMap<K, HashMap<K, Integer>> adjacencyMap;
    private int numberOfVertices;

    public Graph() {
        adjacencyMap = new HashMap<>();
        numberOfVertices = 0;
    }

    public void addVertex(K vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            adjacencyMap.put(vertex, new HashMap<>());
            numberOfVertices++;
        }
    }

    public void addEdge(K source, K destination, Integer weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyMap.get(source).put(destination, weight);
        adjacencyMap.get(destination).put(source,weight);
    }

    public void removeVertex(K vertex) {
        if (adjacencyMap.containsKey(vertex)) {
            adjacencyMap.remove(vertex);
            numberOfVertices--;

        }
    }

    public void removeEdge(K source, K destination) {
        if (adjacencyMap.containsKey(source) && adjacencyMap.get(source).containsKey(destination)) {
            adjacencyMap.get(source).remove(destination);
            adjacencyMap.get(destination).remove(source);
        }
    }

    public HashMap<K, HashMap<K, Integer>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public int getNumberOfVertices(){
        return numberOfVertices;
    }

    public HashMap<K, Integer> bfs( K source) {
        HashMap<K, Integer> distances = new HashMap<K, Integer>();
        Queue<K> queue = new LinkedList<>();
        Set<K> visited = new HashSet<>();
        HashMap<K, K> parents = new HashMap<K, K>();

        queue.offer(source);
        visited.add(source);
        distances.put(source, 0);
        parents.put(source, null);


        while (!queue.isEmpty()) {
            K current = queue.poll();

            HashMap<K, Integer> innerMap = adjacencyMap.get(current);
            for (Entry<K, Integer> innerEntry : innerMap.entrySet()) {
                K neighbor = innerEntry.getKey();
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    distances.put(neighbor,( (int)distances.get(current)+ (int) innerEntry.getValue()));
                    parents.put(neighbor, current);
                } else if (distances.get(neighbor) > distances.get(current) + innerEntry.getValue()) {
                    distances.put(neighbor, distances.get(current) + innerEntry.getValue());
                    parents.put(neighbor, current);
                }
            }

            // Set distances of unvisited vertices to null
            for (Entry<K, java.lang.Integer> innerEntry : innerMap.entrySet()) {
                if (!visited.contains(innerEntry.getKey())) {
                    distances.put(innerEntry.getKey(), null);
                }
            }
        }

        return distances;
    }
}

package dataStructure.graph;

import dataStructure.hashMap.HashMap;

public class Graph<K, V> {
    private final HashMap<K, HashMap<K, V>> adjacencyMap;

    public Graph() {
        adjacencyMap = new HashMap<>();
    }

    public void addVertex(K vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            adjacencyMap.put(vertex, new HashMap<>());
        }
    }

    public void addEdge(K source, K destination, V weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyMap.get(source).put(destination, weight);
        adjacencyMap.get(destination).put(source,weight);
    }

    public void removeVertex(K vertex) {
        if (adjacencyMap.containsKey(vertex)) {
            adjacencyMap.remove(vertex);
        }
    }

    public void removeEdge(K source, K destination) {
        if (adjacencyMap.containsKey(source) && adjacencyMap.get(source).containsKey(destination)) {
            adjacencyMap.get(source).remove(destination);
            adjacencyMap.get(destination).remove(source);
        }
    }

    public HashMap<K, HashMap<K, V>> getAdjacencyMap() {
        return adjacencyMap;
    }
}

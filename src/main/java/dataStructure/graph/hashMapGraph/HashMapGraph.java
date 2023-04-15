package dataStructure.graph.hashMapGraph;


import dataStructure.graph.Graph;
import dataStructure.graph.Route;
import dataStructure.hashMap.Entry;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.LinkedListHashMap;
import dataStructure.hashMap.TreeHashMap;

import java.util.*;

/**
 * A graph implementation using HashMap to store the adjacency map.
 *
 * @param <K> the type of vertex keys in the graph, must implement Comparable interface
 */
public class HashMapGraph<K extends Comparable<K>> implements Graph<K> {

    /**
     * The adjacency map of the graph. Each key is a vertex in the graph and the value is a map
     * of adjacent vertices and their edge weights.
     */
    private final HashMap<K, HashMap<K, Integer>> adjacencyMap;


    /**
     * Constructs a new graph with the given adjacency map.
     *
     * @param hashMap the adjacency map of the graph
     */
    public HashMapGraph(HashMap<K, HashMap<K, Integer>> hashMap) {
        adjacencyMap = hashMap;
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(K vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            adjacencyMap.put(vertex, createHashMap());
        }
    }

    /**
     * Adds an undirected edge to the graph between the given source and destination vertices with
     * the given weight.
     *
     * @param source      the source vertex of the edge
     * @param destination the destination vertex of the edge
     * @param weight      the weight of the edge
     */
    public void addEdge(K source, K destination, Integer weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyMap.get(source).put(destination, weight);
        adjacencyMap.get(destination).put(source, weight);
    }

    /**
     * Removes a vertex from the graph.
     *
     * @param vertex the vertex to remove
     */
    public void removeVertex(K vertex) {
        if (adjacencyMap.containsKey(vertex)) {
            adjacencyMap.remove(vertex);

        }
    }

    /**
     * Removes an undirected edge from the graph between the given source and destination vertices.
     *
     * @param source      the source vertex of the edge
     * @param destination the destination vertex of the edge
     */
    public void removeEdge(K source, K destination) {
        if (adjacencyMap.containsKey(source) && adjacencyMap.get(source).containsKey(destination)) {
            adjacencyMap.get(source).remove(destination);
            adjacencyMap.get(destination).remove(source);
        }
    }

    /**
     * Returns the adjacency map of the graph.
     *
     * @return the adjacency map of the graph
     */
    public HashMap<K, HashMap<K, Integer>> getAdjacencyMap() {
        return adjacencyMap;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices in the graph
     */
    public int getNumberOfVertices() {
        return adjacencyMap.size();
    }

    /**
     * Performs breadth-first search on the graph starting from the given source vertex and returns
     * a map of vertices and their shortest routes from the source.
     *
     * @param source the source vertex of the search
     * @return a map of vertices and their shortest routes from the source
     */
    public HashMap<K, Route<K>> bfs(K source) {
        HashMap<K, Route<K>> distances = new LinkedListHashMap<>();
        Queue<K> queue = new LinkedList<>();
        Set<K> visited = new HashSet<>();
        //HashMap<K, K> parents = new LinkedListHashMap<>();

        queue.offer(source);
        visited.add(source);
        distances.put(source, new Route(0, new ArrayList<>()));
        //parents.put(source, null);

        while (!queue.isEmpty()) {
            K current = queue.poll();
            HashMap<K, Integer> innerMap = adjacencyMap.get(current);

            for (Entry<K, Integer> innerEntry : innerMap.entries()) {
                K neighbor = innerEntry.getKey();

                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    int distance = distances.get(current).getDistance() + innerEntry.getValue();
                    List<K> path = new ArrayList<>(distances.get(current).getPath());
                    path.add(current);
                    distances.put(neighbor, new Route(distance, path));
                    //parents.put(neighbor, current);
                } else if (distances.get(neighbor).getDistance() > distances.get(current).getDistance() + innerEntry.getValue()) {
                    int distance = distances.get(current).getDistance() + innerEntry.getValue();
                    List<K> path = new ArrayList<>(distances.get(current).getPath());
                    path.add(current);
                    distances.put(neighbor, new Route(distance, path));
                    //parents.put(neighbor, current);
                }
            }
        }

        for(Entry<K, Route<K>> distance: distances.entries()){
            K current = distance.getKey();
            distance.getValue().getPath().add(current);
        }
        return distances;
    }

    /**
     * Finds the shortest path from the source vertex to the destination vertex using Breadth-First Search algorithm.
     *
     * @param source the starting vertex
     * @param destination the ending vertex
     * @return a Route object representing the shortest path from source to destination
     */
    public Route<K> shortestPath(K source, K destination) {
        // Call bfs to get distances and parents maps
        HashMap<K, Route<K>> distances = bfs(source);
        return (getParents(distances, destination));
    }

    /**
     * Returns a list of vertices in the graph.
     *
     * @return a list of vertices in the graph
     */
    @Override
    public List<K> getVertices() {
        return getAdjacencyMap().keys();
    }

    /**
     * Returns the Route object for a given destination vertex.
     *
     * @param distances a HashMap containing distances and parent vertices
     * @param destination the destination vertex for which the Route object is to be returned
     * @return the Route object for the given destination vertex
     */
    private Route<K> getParents(HashMap<K, Route<K>> distances, K destination) {
        return distances.get(destination);
    }

    /**
     * Creates a new HashMap based on the type of adjacencyMap.
     *
     * @return a new HashMap based on the type of adjacencyMap
     * @throws IllegalArgumentException if the type of adjacencyMap is invalid
     */
    private HashMap<K, Integer> createHashMap() {
        if (adjacencyMap instanceof LinkedListHashMap<K, HashMap<K, Integer>>) {
            return new LinkedListHashMap<>();
        } else if (adjacencyMap instanceof TreeHashMap<K, HashMap<K, Integer>>) {
            return new TreeHashMap<>();
        } else {
            throw new IllegalArgumentException("Invalid HashMap type");
        }
    }

}


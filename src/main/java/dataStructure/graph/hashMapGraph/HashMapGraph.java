package dataStructure.graph.hashMapGraph;


import dataStructure.graph.Graph;
import dataStructure.graph.Route;
import dataStructure.hashMap.Entry;
import dataStructure.hashMap.HashMap;
import dataStructure.hashMap.LinkedListHashMap;
import dataStructure.hashMap.TreeHashMap;

import java.util.*;

/**
 * A class representing an undirected graph.
 *
 * @param <K> the type of the vertices in the graph
 */

public class HashMapGraph<K extends Comparable<K>> implements Graph<K> {
    /**
     * The adjacency map of the graph.
     */
    private final HashMap<K, HashMap<K, Integer>> adjacencyMap;

    /**
     * Constructs a new empty graph.
     */
    public HashMapGraph(HashMap<K, HashMap<K, Integer>> hashMap) {
        adjacencyMap = hashMap;
    }

    /**
     * Adds a vertex to the graph if it doesn't already exist.
     *
     * @param vertex the vertex to be added
     */
    public void addVertex(K vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            adjacencyMap.put(vertex, createHashMap());
        }
    }

    /**
     * Adds an edge between two vertices with the given weight.
     * If the vertices don't exist, they are added to the graph.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @param weight      the weight of the edge
     */
    public void addEdge(K source, K destination, Integer weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyMap.get(source).put(destination, weight);
        adjacencyMap.get(destination).put(source, weight);
    }

    /**
     * Removes a vertex from the graph, including all its edges.
     *
     * @param vertex the vertex to be removed
     */
    public void removeVertex(K vertex) {
        if (adjacencyMap.containsKey(vertex)) {
            adjacencyMap.remove(vertex);

        }
    }

    /**
     * Removes an edge between two vertices, if it exists.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
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
     Computes and returns the shortest paths from the given source vertex to all other vertices in the graph,
     using the Breadth-First Search (BFS) algorithm.
     The distances are represented by Route objects containing the distance and the path of vertices.
     @param source the source vertex
     @return a HashMap containing the shortest routes from the source to each vertex
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
     * Returns the shortest path between a source vertex and a destination vertex in the graph.
     *
     * @param source      the source vertex
     * @param destination the destination vertex
     * @return the shortest path between the source and destination vertices as a Route object
     */
    public Route<K> shortestPath(K source, K destination) {
        // Call bfs to get distances and parents maps
        HashMap<K, Route<K>> distances = bfs(source);
        return (getParents(distances, destination));
    }

    /**
     * @return
     */
    @Override
    public List<K> getVertices() {
        return getAdjacencyMap().keys();
    }

    /**
     * Helper method to extract the shortest path from the distances map.
     *
     * @param distances   a HashMap containing the distances and paths for all vertices from the source vertex
     * @param destination the vertex to extract the path for
     * @return the shortest path for the specified vertex as a Route object
     */
    private Route<K> getParents(HashMap<K, Route<K>> distances, K destination) {
        return distances.get(destination);
    }

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


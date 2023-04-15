package dataStructure.graph.hashMapGraph;


import dataStructure.graph.Graph;
import dataStructure.graph.Route;
import dataStructure.hashMap.Entry;
import dataStructure.hashMap.LinkedListHashMap;
import java.util.*;

/**

 A class representing an undirected graph.

 @param <K> the type of the vertices in the graph
 */

public class HashMapGraph<K> implements Graph<K> {
    /**

     The adjacency map of the graph.
     */
    private final LinkedListHashMap<K, LinkedListHashMap<K, Integer>> adjacencyMap;
    /**

     The number of vertices in the graph.
     */
    private int numberOfVertices;

    /**

     Constructs a new empty graph.
     */
    public HashMapGraph() {
        adjacencyMap = new LinkedListHashMap<>();
        numberOfVertices = 0;
    }

    /**

     Adds a key to the graph if it doesn't already exist.
     @param vertex the key to be added
     */
    public void addVertex(K vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            adjacencyMap.put(vertex, new LinkedListHashMap<>());
            numberOfVertices++;
        }
    }
    /**

     Adds an edge between two vertices with the given weight.
     If the vertices don't exist, they are added to the graph.
     @param source the source key
     @param destination the destination key
     @param weight the weight of the edge
     */
    public void addEdge(K source, K destination, Integer weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyMap.get(source).put(destination, weight);
        adjacencyMap.get(destination).put(source,weight);
    }

    /**

     Removes a key from the graph, including all its edges.
     @param vertex the key to be removed
     */
    public void removeVertex(K vertex) {
        if (adjacencyMap.containsKey(vertex)) {
            adjacencyMap.remove(vertex);
            numberOfVertices--;

        }
    }

    /**

     Removes an edge between two vertices, if it exists.
     @param source the source key
     @param destination the destination key
     */
    public void removeEdge(K source, K destination) {
        if (adjacencyMap.containsKey(source) && adjacencyMap.get(source).containsKey(destination)) {
            adjacencyMap.get(source).remove(destination);
            adjacencyMap.get(destination).remove(source);
        }
    }

    /**

     Returns the adjacency map of the graph.
     @return the adjacency map of the graph
     */
    public LinkedListHashMap<K, LinkedListHashMap<K, Integer>> getAdjacencyMap() {
        return adjacencyMap;
    }

    /**

     Returns the number of vertices in the graph.
     @return the number of vertices in the graph
     */
    public int getNumberOfVertices(){
        return numberOfVertices;
    }

    /**

     Computes and returns the shortest paths from the given source key to all other vertices in the graph,
     using the Breadth-First Search (BFS) algorithm.

     The distances are represented by Route objects containing the distance and the path of vertices.
     @param source the source key
     @return a HashMap containing the shortest routes from the source to each key
     */
    public LinkedListHashMap<K, Route<K>> bfs(K source) {
        LinkedListHashMap<K, Route<K>> distances = new LinkedListHashMap<>();
        Queue<K> queue = new LinkedList<>();
        Set<K> visited = new HashSet<>();
        LinkedListHashMap<K, K> parents = new LinkedListHashMap<>();

        queue.offer(source);
        visited.add(source);
        distances.put(source, new Route<>(0, new ArrayList<>()));
        parents.put(source, null);

        while (!queue.isEmpty()) {
            K current = queue.poll();
            LinkedListHashMap<K, Integer> innerMap = adjacencyMap.get(current);

            for (Entry<K, Integer> innerEntry : innerMap.entries()) {
                K neighbor = innerEntry.getKey();

                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    int distance = distances.get(current).getDistance() + innerEntry.getValue();
                    List<K> path = new ArrayList<>(distances.get(current).getPath());
                    path.add(current);
                    distances.put(neighbor, new Route<>(distance, path));
                    parents.put(neighbor, current);
                } else if (distances.get(neighbor).getDistance() > distances.get(current).getDistance() + innerEntry.getValue()) {
                    int distance = distances.get(current).getDistance() + innerEntry.getValue();
                    List<K> path = new ArrayList<>(distances.get(current).getPath());
                    path.add(current);
                    distances.put(neighbor, new Route<>(distance, path));
                    parents.put(neighbor, current);
                }
            }
        }

        // Add the path from the source to each node to the distances map
        for (Entry<K, K> entry : parents.entries()) {
            K node = entry.getKey();
            List<K> path = new ArrayList<>();
            while (node != null) {
                path.add(0, node);
                node = parents.get(node);
            }
            distances.get(entry.getKey()).getPath().addAll(path);
        }
        return distances;
    }

    /**

     Returns the shortest path between a source key and a destination key in the graph.
     @param source the source key
     @param destination the destination key
     @return the shortest path between the source and destination vertices as a Route object
     */
    public Route<K> shortestPath(K source, K destination) {
        // Call bfs to get distances and parents maps
        LinkedListHashMap<K, Route<K>> distances = bfs(source);
        return (getParents(distances, destination));
    }

    /**

     Helper method to extract the shortest path from the distances map.
     @param distances a HashMap containing the distances and paths for all vertices from the source key
     @param destination the key to extract the path for
     @return the shortest path for the specified key as a Route object
     */
    private Route<K> getParents(LinkedListHashMap<K, Route<K>> distances, K destination) {
       return distances.get(destination);
    }

}


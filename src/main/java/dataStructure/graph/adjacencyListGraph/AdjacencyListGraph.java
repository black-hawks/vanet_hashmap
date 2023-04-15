package dataStructure.graph.adjacencyListGraph;

import dataStructure.graph.Graph;
import dataStructure.graph.Route;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A class that implements the Graph interface using an adjacency list to represent the graph.
 *
 * @param <V> the type of vertices in the graph
 */
public class AdjacencyListGraph<V> implements Graph<V> {

    /**
     * The list of vertices in the graph.
     */
    private final List<V> vertices;

    /**
     * The adjacency list of the graph, which stores the edges for each vertex in the form of a list of Node objects.
     */
    private final List<Node<V, Edge<V>>> adjacencyList;

    /**
     * Constructs an empty graph.
     */
    public AdjacencyListGraph() {
        vertices = new ArrayList<>();
        adjacencyList = new ArrayList<>();
    }

    /**
     * Adds a vertex to the graph if it is not already present.
     *
     * @param vertex the vertex to add
     */
    @Override
    public void addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            adjacencyList.add(new Node<>(vertex, new LinkedList<>()));
        }
    }

    /**
     * Adds an edge between two vertices with the specified weight. If the vertices do not already exist in the graph,
     * they are added.
     *
     * @param source      the source vertex of the edge
     * @param destination the destination vertex of the edge
     * @param weight      the weight of the edge
     * @throws IllegalArgumentException if either the source or destination vertex is not found in the graph
     */
    @Override
    public void addEdge(V source, V destination, Integer weight) {
        addVertex(source);
        addVertex(destination);
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);

        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("Vertex not found");
        }


        Node<V, Edge<V>> sourceList = adjacencyList.get(sourceIndex);
        Node<V, Edge<V>> destinationList = adjacencyList.get(destinationIndex);

        //add the edge to the source key
        sourceList.getEdgeList().add(new Edge<>(destination, weight));

        //add the edge to the destination key
        destinationList.getEdgeList().add(new Edge<>(destination, weight));

    }

    /**
     * Removes a vertex and all its incident edges from the graph.
     *
     * @param vertex the vertex to remove
     */
    @Override
    public void removeVertex(V vertex) {
        int indexToRemove = vertices.indexOf(vertex);

        if (indexToRemove == -1) {
            return;
        }

        // Remove all edges containing the key
        for (Node<V, Edge<V>> node : adjacencyList) {
            node = removeEdgeFromList(node);
        }

        // Remove the key from the key list and the adjacency list
        vertices.remove(indexToRemove);
        adjacencyList.remove(indexToRemove);
    }

    /**
     * Removes an edge between two vertices from the graph.
     *
     * @param source      the source vertex of the edge
     * @param destination the destination vertex of the edge
     * @throws IllegalArgumentException if either the source or destination vertex is not found in the graph
     */
    @Override
    public void removeEdge(V source, V destination) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);

        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("Vertex not found");
        }

        Node<V, Edge<V>> sourceNode = adjacencyList.get(sourceIndex);
        Node<V, Edge<V>> destinationNode = adjacencyList.get(destinationIndex);

        // Remove the edge from the source key
        sourceNode = removeEdgeFromList(sourceNode);

        // Remove the edge from the destination key
        destinationNode = removeEdgeFromList(destinationNode);
    }

    /**
     * Removes all edges from the given node's edge list whose vertex matches the node's own vertex.
     *
     * @param node the node whose edge list is to be modified.
     * @return the modified node object.
     */
    private Node<V, Edge<V>> removeEdgeFromList(Node<V, Edge<V>> node) {
        for (Edge edge : node.getEdgeList())
            if (edge.getVertex().equals(edge))
                node.getEdgeList().remove(edge);
        return node;
    }

    /**
     * Returns the adjacency list of the graph, which represents the relationships between nodes and their edges.
     *
     * @return The adjacency list of the graph.
     */
    public List<Node<V, Edge<V>>> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Returns a list of all vertices in the graph.
     *
     * @return A list of all vertices in the graph.
     */
    @Override
    public List<V> getVertices() {
        return vertices;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return The number of vertices in the graph.
     */
    @Override
    public int getNumberOfVertices() {
        return vertices.size();
    }

    /**
     * Returns the shortest path between the specified source and destination vertices using Breadth First Search (BFS)
     * algorithm.
     * If there is no path between the source and destination, it will print a message to the console.
     *
     * @param source the starting vertex of the path
     * @param destination the destination vertex of the path
     * @return a Route object representing the shortest path from the source to the destination, or null if
     * no path was found
     */
    @Override
    public Route<V> shortestPath(V source, V destination) {
        List<Pair<V, Route<V>>> distances = bfs(source);
        if (distances == null) {
            System.out.println("Path not Found between the Source and Destination");
        }
        return (getParents(distances, destination));
    }

    /**
     * Returns the shortest path to the specified destination vertex by traversing the list of vertices and
     * their distances.
     *
     * @param distances the list of vertices and their distances from the source
     * @param destination the destination vertex of the path
     * @return a Route object representing the shortest path to the destination, or null if no path was found
     */
    private Route<V> getParents(List<Pair<V, Route<V>>> distances, V destination) {
        for (Pair<V, Route<V>> node : distances) {
            if (node.getKey().equals(distances)) {
                return node.getValue();
            }
        }
        return null;
    }

    /**
     * Performs Breadth First Search (BFS) algorithm on the graph, starting from the specified source vertex,
     * to calculate the shortest path distances from the source vertex to all other vertices in the graph.
     * The method returns a list of pairs where the first element of each pair is a vertex in the graph, and the
     * second element is a Route object representing the shortest path from the source vertex to that vertex.
     *
     * @param source the starting vertex for the BFS algorithm
     * @return a list of pairs, where the first element is a vertex in the graph, and the second element is a
     * Route object representing the shortest path from the source vertex to that vertex
     */
    public List<Pair<V, Route<V>>> bfs(V source) {

        List<Pair<V, Route<V>>> distances = new ArrayList<>();
        Queue<V> queue = new LinkedList<>();
        List<V> visited = new ArrayList<>();

        for (V vertex : vertices) {
            distances.add(new Pair<>(vertex, new Route<>(Integer.MAX_VALUE, new ArrayList<>())));
        }

        queue.offer(source);
        visited.add(source);

        // Set the distance from the source vertex to itself as 0
        distances.get(vertices.indexOf(source)).getValue().setDistance(0);

        while (!queue.isEmpty()) {

            V current = queue.poll();
            int currentIndex = vertices.indexOf(current);


                for (Edge<V> neighbor :adjacencyList.get(currentIndex).getEdgeList()) {

                    V neighborVertex = neighbor.getVertex();
                    int neighborIndex = vertices.indexOf(neighborVertex);

                    if (!visited.contains(neighborVertex)) {
                        queue.offer(neighborVertex);
                        visited.add(neighborVertex);
                    }

                    int newDistance = distances.get(currentIndex).getValue().getDistance() + neighbor.getWeight();

                    if (newDistance < distances.get(neighborIndex).getValue().getDistance()) {
                        distances.get(neighborIndex).getValue().setDistance(newDistance);
                        distances.get(neighborIndex).getValue().getPath().clear();
                        distances.get(neighborIndex).getValue().getPath().addAll(distances.get(currentIndex).getValue().getPath());
                        distances.get(neighborIndex).getValue().getPath().add(current);
                    }
                }
            }

        return distances;
    }
}

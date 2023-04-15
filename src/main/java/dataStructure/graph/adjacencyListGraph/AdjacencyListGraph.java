package dataStructure.graph.adjacencyListGraph;

import dataStructure.graph.Graph;
import dataStructure.graph.Route;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AdjacencyListGraph<V> implements Graph<V> {

    private List<V> vertices;

    private List<Node<V,Edge<V>>> adjacencyList;


    public AdjacencyListGraph() {
        vertices = new ArrayList<>();
        adjacencyList = new ArrayList<>();
    }

    @Override
    public void addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            adjacencyList.add(new Node<>(vertex, new LinkedList<>()));
        }
    }

    /**
     * @param source
     * @param destination
     * @param weight
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
     * @param vertex
     */
    @Override
    public void removeVertex(V vertex) {
        int indexToRemove = vertices.indexOf(vertex);

        if (indexToRemove == -1) {
            throw new IllegalArgumentException("Vertex not found");
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
     * @param source
     * @param destination
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

    private Node<V, Edge<V>> removeEdgeFromList(Node<V, Edge<V>> node){
        for(Edge edge : node.getEdgeList())
            if (edge.getVertex().equals(edge))
                node.getEdgeList().remove(edge);
        return node;
    }

    public List<Node<V,Edge<V>>> getAdjacencyList(){
        return adjacencyList;
    }


    /**
     * @return
     */
    @Override
    public List<V> getVertices() {
        return vertices;
    }

    /**
     * @return
     */
    @Override
    public int getNumberOfVertices() {
       return vertices.size();
    }

    /**
     * @param source
     * @param destination
     * @return
     */
    @Override
    public Route<V> shortestPath(V source, V destination) {
        List<Pair<V, Route<V>>> distances = bfs(source);
        if(distances == null){
            System.out.println("Path not Found between the Source and Destination");
        }
        return (getParents(distances, destination));
    }

    private Route<V> getParents(List<Pair<V, Route<V>>> distances, V destination ) {
        for(Pair<V, Route<V>> node : distances){
            if(node.getKey().equals(distances)){
                return node.getValue();
            }
        }
        return null;
    }
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

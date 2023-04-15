package dataStructure.graph.adjacencyListGraph;

import dataStructure.graph.Graph;
import dataStructure.graph.hashMapGraph.Route;
import dataStructure.hashMap.LinkedListHashMap;
import simulation.Vehicle;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyListGraph<V> implements Graph<V> {

    private List<V> vertices;
    private List<LinkedList<Edge<V>>> adjacencyList;

    public AdjacencyListGraph() {
        vertices = new ArrayList<>();
        adjacencyList = new ArrayList<>();
    }

    @Override
    public void addVertex(V vertex) {
        vertices.add(vertex);
        adjacencyList.add(new LinkedList<>());
    }

    /**
     * @param source
     * @param destination
     * @param weight
     */
    @Override
    public void addEdge(V source, V destination, Integer weight) {

    }

    /**
     * @param vertex
     */
    @Override
    public void removeVertex(V vertex) {

    }

    /**
     * @param source
     * @param destination
     */
    @Override
    public void removeEdge(V source, V destination) {

    }

}

package dataStructure.graph.adjacencyListGraph;

import dataStructure.graph.Graph;

import java.util.LinkedList;
import java.util.ArrayList;
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

        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);

        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("Vertex not found");
        }

        LinkedList<Edge<V>> sourceList = adjacencyList.get(sourceIndex);
        LinkedList<Edge<V>> destinationList = adjacencyList.get(destinationIndex);

        //add the edge to the source vertex
        sourceList.add(new Edge<>(destination, weight));

        //add the edge to the destination vertex
        destinationList.add(new Edge<>(source, weight));

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

        // Remove all edges containing the vertex
        for (LinkedList<Edge<V>> list : adjacencyList) {
            list = removeEdgeFromList(list);
        }

        // Remove the vertex from the vertex list and the adjacency list
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

        LinkedList<Edge<V>> sourceList = adjacencyList.get(sourceIndex);
        LinkedList<Edge<V>> destinationList = adjacencyList.get(destinationIndex);

        // Remove the edge from the source vertex
        sourceList = removeEdgeFromList(destinationList);

        // Remove the edge from the destination vertex
       destinationList = removeEdgeFromList(destinationList);
    }

    private LinkedList<Edge<V>> removeEdgeFromList(LinkedList<Edge<V>> list){
        for(Edge edge : list)
            if (edge.getVertex().equals(edge))
                list.remove(edge);
        return list;
    }

    public void printAdjacencyList() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.print(vertices.get(i) + " -> ");
            LinkedList<Edge<V>> edgeList = adjacencyList.get(i);
            for (int j = 0; j < edgeList.size(); j++) {
                Edge<V> edge = edgeList.get(j);
                System.out.print(edge.getVertex() + "(" + edge.getWeight() + ")");
                if (j != edgeList.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}

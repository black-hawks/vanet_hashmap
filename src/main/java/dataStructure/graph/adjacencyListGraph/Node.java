package dataStructure.graph.adjacencyListGraph;

import java.util.List;

public class Node<V, E> {
    private V vertex;
    private List<E> edgeList;

    public Node(V vertex, List<E> edgeList){
        this.vertex = vertex;
        this.edgeList = edgeList;
    }

    public V getVertex() {
        return vertex;
    }

    public List<E> getEdgeList() {
        return edgeList;
    }

}

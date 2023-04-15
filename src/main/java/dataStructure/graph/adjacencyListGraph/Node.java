package dataStructure.graph.adjacencyListGraph;

import java.util.List;

/**
 * Represents a node in a graph.
 *
 * @param <V> the type of vertex associated with this node
 * @param <E> the type of edge associated with this node
 */
public class Node<V, E> {
    /**
     * The vertex associated with this node.
     */
    private final V vertex;

    /**
     * The list of edges associated with this node.
     */
    private final List<E> edgeList;

    /**
     * Constructs a new node with the specified vertex and list of edges.
     *
     * @param vertex the vertex associated with this node
     * @param edgeList the list of edges associated with this node
     */
    public Node(V vertex, List<E> edgeList){
        this.vertex = vertex;
        this.edgeList = edgeList;
    }

    /**
     * Returns the vertex associated with this node.
     *
     * @return the vertex associated with this node
     */
    public V getVertex() {
        return vertex;
    }

    /**
     * Returns the list of edges associated with this node.
     *
     * @return the list of edges associated with this node
     */
    public List<E> getEdgeList() {
        return edgeList;
    }

}

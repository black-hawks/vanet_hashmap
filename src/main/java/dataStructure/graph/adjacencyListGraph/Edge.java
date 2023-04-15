package dataStructure.graph.adjacencyListGraph;

/**
 * Represents an edge in a graph.
 *
 * @param <V> the type of vertex associated with this edge
 */
public class Edge<V> {

    /**
     * The vertex associated with this edge.
     */
    private final V vertex;

    /**
     * The weight of this edge.
     */
    private final int weight;

    /**
     * Constructs a new edge with the specified vertex and weight.
     *
     * @param vertex the vertex associated with this edge
     * @param weight the weight of this edge
     */
    public Edge(V vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    /**
     * Returns the vertex associated with this edge.
     *
     * @return the vertex associated with this edge
     */
    public V getVertex() {
        return vertex;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public int getWeight() {
        return weight;
    }
}

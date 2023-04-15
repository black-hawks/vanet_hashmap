package dataStructure.graph.adjacencyListGraph;

public class Edge<V> {

    private V vertex;
    private int weight;

    public Edge(V vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    public V getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }
}

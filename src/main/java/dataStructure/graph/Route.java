package dataStructure.graph;

import java.util.List;

/**
 * A class representing a route between two nodes in a graph, consisting of a distance and a path.
 *
 * @param <K> the type of the nodes in the graph
 */
public class Route<K> {
    /**
     * The distance of the route between two nodes.
     */
    private int distance;

    /**
     * The path taken for the route between two nodes.
     */
    private List<K> path;

    /**
     * Constructs a new Route with the given distance and path.
     *
     * @param distance the distance of the route
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Sets the distance of the route.
     *
     * @param path sets the path
     */
    public void setPath(List<K> path) {
        this.path = path;
    }

    /**
     * Constructs a new Route with the given distance and path.
     *
     * @param distance the distance of the route
     * @param path the path taken for the route
     */
    public Route(int distance, List<K> path){
        this.distance = distance;
        this.path = path;
    }

    /**
     * Returns the distance of the route.
     *
     * @return the distance of the route
     */
    public int getDistance() {
        return distance;
    }


    /**
     * Returns the path taken for the route.
     *
     * @return the path taken for the route
     */
    public List<K> getPath() {
        return path;
    }
}
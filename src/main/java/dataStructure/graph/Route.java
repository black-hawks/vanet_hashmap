package dataStructure.graph;

import java.util.List;
/**
 A class representing a route in a graph.
 @param <K> the type of the vertices in the route
 */
public class Route<K> {

    /**
     The distance of the route.
     */
    private int distance;

    /**
     The path of vertices in the route.
     */
    private List<K> path;

    /**
     Constructs a new Route object with the given distance and path.
     @param distance the distance of the route
     @param path the path of vertices in the route
     */
    public Route(int distance, List<K> path){
        this.distance = distance;
        this.path = path;
    }

    /**
     Returns the distance of the route.
     @return the distance of the route
     */
    public int getDistance() {
        return distance;
    }

    /**
     Returns the path of vertices in the route.
     @return the path of vertices in the route
     */
    public List<K> getPath() {
        return path;
    }
}
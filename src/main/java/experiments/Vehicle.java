package experiments;

/**
 * The Vehicle class represents a vehicle that can send and receive messages in the simulation.
 */
public class Vehicle implements Comparable<Vehicle> {

    private final String vehicleId;
    private final int speed;

    /**
     * Constructs a new Vehicle object with the given ID.
     *
     * @param vehicleId the ID of the vehicle
     */
    public Vehicle(String vehicleId) {
        this(vehicleId, 0);
    }

    /**
     * Constructs a new Vehicle object with the given ID and speed.
     *
     * @param vehicleId the ID of the vehicle
     * @param speed     the speed of the vehicle
     */
    public Vehicle(String vehicleId, int speed) {
        this.vehicleId = vehicleId;
        this.speed = speed;
    }

    /**
     * Returns the ID of the vehicle.
     *
     * @return the ID of the vehicle
     */
    public String getVehicleId() {
        return this.vehicleId;
    }

    /**
     * Returns the speed of the vehicle.
     *
     * @return the speed of the vehicle
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Returns a string representation of the Vehicle object.
     *
     * @return a string representation of the Vehicle object
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", speed=" + speed +
                '}';
    }

    /**
     * Compares this Vehicle object to the specified object for order.
     *
     * @param o the object to be compared
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(Vehicle o) {
        if (this == o) {
            return 0;
        }
        return this.getVehicleId().compareTo(o.vehicleId);
    }
}

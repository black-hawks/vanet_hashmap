package simulation;

/**

 The Vehicle class represents a vehicle that can send and receive messages in the simulation.
 */
public class Vehicle {

    private String vehicleId;
    private int speed;

    /**
     * Constructs a new Vehicle object with the given ID and speed.
     * @param vehicleId the ID of the vehicle
     * @param speed the speed of the vehicle
     */
    public Vehicle(String vehicleId, int speed){
        this.vehicleId = vehicleId;
        this.speed = speed;
    }

    /**
     * Returns the ID of the vehicle.
     * @return the ID of the vehicle
     */
    public String getVehicleId(){
        return this.vehicleId;
    }

    /**
     * Returns the speed of the vehicle.
     * @return the speed of the vehicle
     */
    public int getSpeed(){
        return this.speed;
    }
    
}

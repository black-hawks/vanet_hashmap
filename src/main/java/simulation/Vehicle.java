package simulation;

public class Vehicle {

    private String vehicleId;
    private int speed;

    public Vehicle(String vehicleId, int speed){
        this.vehicleId = vehicleId;
        this.speed = speed;
    }

    public String getVehicleId(){
        return this.vehicleId;
    }

    public int getSpeed(){
        return this.speed;
    }
    
}

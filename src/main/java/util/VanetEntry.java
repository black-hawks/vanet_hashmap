package util;

import simulation.Vehicle;

public class VanetEntry {
    private final Vehicle sourceVehicle;
    private final Vehicle destinationVehicle;
    private final Integer weight;

    public VanetEntry(Vehicle sourceVehicle, Vehicle destinationVehicle, Integer weight) {
        this.sourceVehicle = sourceVehicle;
        this.destinationVehicle = destinationVehicle;
        this.weight = weight;
    }

    public Vehicle getSourceVehicle() {
        return sourceVehicle;
    }

    public Vehicle getDestinationVehicle() {
        return destinationVehicle;
    }

    public Integer getWeight() {
        return weight;
    }
}
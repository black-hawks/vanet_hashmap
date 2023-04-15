package util;

import experiments.Vehicle;

/**
 * This class represents an entry in a Vehicular Ad-hoc Network (VANET).
 * It contains information about the source vehicle, destination vehicle, and weight of the entry.
 */
public class VanetEntry {

    /**
     * The source vehicle of the VANET entry.
     */
    private final Vehicle sourceVehicle;

    /**
     * The destination vehicle of the VANET entry.
     */
    private final Vehicle destinationVehicle;

    /**
     * The weight of the VANET entry.
     */
    private final Integer weight;

    /**
     * Constructs a new VANET entry with the given source vehicle, destination vehicle, and weight.
     *
     * @param sourceVehicle the source vehicle of the entry
     * @param destinationVehicle the destination vehicle of the entry
     * @param weight the weight of the entry
     */
    public VanetEntry(Vehicle sourceVehicle, Vehicle destinationVehicle, Integer weight) {
        this.sourceVehicle = sourceVehicle;
        this.destinationVehicle = destinationVehicle;
        this.weight = weight;
    }

    /**
     * Returns the source vehicle of the VANET entry.
     *
     * @return the source vehicle of the entry
     */
    public Vehicle getSourceVehicle() {
        return sourceVehicle;
    }

    /**
     * Returns the destination vehicle of the VANET entry.
     *
     * @return the destination vehicle of the entry
     */
    public Vehicle getDestinationVehicle() {
        return destinationVehicle;
    }

    /**
     * Returns the weight of the VANET entry.
     *
     * @return the weight of the entry
     */
    public Integer getWeight() {
        return weight;
    }
}
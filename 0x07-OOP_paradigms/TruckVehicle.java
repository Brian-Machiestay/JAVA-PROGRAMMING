/**
 * This interface declares methods specific to Trucks. It is intended to be implemented by Truck objects
 */
public interface TruckVehicle {

    void setCargoCapacity(int capacity);

    int getCargoCapacity();

    void setTransmissionType(String type);

    String getTransmissionType();
}

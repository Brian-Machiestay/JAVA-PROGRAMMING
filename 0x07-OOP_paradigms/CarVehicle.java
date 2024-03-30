/**
 * This interface declares methods that are specific to Cars. It is meant to be implemented
 * by Car classes.
 */
public interface CarVehicle {

    void setDoors(int doors);

    int getNumberOfDoors();

    void setFuelType(String fuelType);

    String getFuelType();

}

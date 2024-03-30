/**
 * This class implements the Vehicle and TruckVehicle interface by
 * providing methods for setting and getting the capacity and transmission of a truck.
 * These extra features are specific to trucks
 */
public class Truck implements Vehicle, TruckVehicle {

    private final String make, model, year;
    private int capacity;
    private String transmissionType;
    public final String ID;

    public Truck(String ID, String make, String model, String year) {
        this.ID = ID;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getYear() {
        return this.year;
    }

    public void setCargoCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCargoCapacity() {
        return this.capacity;
    }

    public void setTransmissionType(String type) {
        if (!type.equals("manual") && !type.equals("automatic"))
            throw new Error("This transmission type is not allowed");
        this.transmissionType = type;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public String getID() {
        return this.ID;
    }

    public String toString() {
        StringBuilder repr = new StringBuilder("ID: " + this.ID + "\n");
        repr.append("Vehicle Type: Truck\n");
        repr.append("Make: ").append(this.make).append("\n");
        repr.append("Model: ").append(this.model).append("\n");
        repr.append("Year: ").append(this.year).append("\n");
        repr.append("Transmission Type: ").append(this.transmissionType).append("\n");
        repr.append("Cargo Capacity: ").append(this.capacity).append("\n");
        return repr.toString();
    }
}

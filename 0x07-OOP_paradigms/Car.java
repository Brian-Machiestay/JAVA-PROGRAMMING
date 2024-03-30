/**
 * This class defines a car object. It implements CarVehicle and Vehicle interfaces which
 * provide method declaration for general and specific features of cars
 */
public class Car implements CarVehicle, Vehicle {

    private int doorNumber;
    private String fuelType;
    private final String make, model, year;
    public final String ID;

    public Car(String ID, String make, String model, String year) {
        this.ID = ID;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public void setDoors(int doors) {
        this.doorNumber = doors;
    }

    public void setFuelType(String fuelType) {
        for (String type : new String[]{"petrol", "diesel", "electric"}) {
            if (fuelType.equals(type)) {
                this.fuelType = fuelType;
                return;
            }
        }
        throw new Error("This fuel type is not allowed");
    }

    public int getNumberOfDoors() {
        return this.doorNumber;
    }

    public String getFuelType() {
        return fuelType;
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

    public String getID() {
        return this.ID;
    }

    public String toString() {
        StringBuilder repr = new StringBuilder("ID: " + this.ID + "\n");
        repr.append("Vehicle Type: Car\n");
        repr.append("Make: ").append(this.make).append("\n");
        repr.append("Model: ").append(this.model).append("\n");
        repr.append("Year: ").append(this.year).append("\n");
        repr.append("Number of Doors: ").append(this.doorNumber).append("\n");
        repr.append("Fuel Type: ").append(this.fuelType).append("\n");
        return repr.toString();
    }
}

/**
 * This class implements the Vehicle and MotorVehicle interfaces by providing the
 * implementation of general vehicle features as well as features specific to Motorcycles
 */
public class MotorCycle implements Vehicle, MotorVehicle {

    private final String make, model, year;
    private String type;
    private int wheelsNumber;
    public final String ID;

    public MotorCycle(String ID, String make, String model, String year) {
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

    public void setWheelsNumber(int num) {
        this.wheelsNumber = num;
    }

    public int getWheelsNumber() {
        return wheelsNumber;
    }

    public void setMotorType(String type) {
        for (String mtrType : new String[]{"sport", "cruiser", "off-road"})
            if (mtrType.equals(type)) {
                this.type = type;
                return;
            }
        throw new Error("This motorcyclye type is not allowed");
    }

    public String getMotorType() {
        return this.type;
    }

    public String getID() {
        return this.ID;
    }

    public String toString() {
        StringBuilder repr = new StringBuilder("ID: " + this.ID + "\n");
        repr.append("Vehicle Type: Motorcycle\n");
        repr.append("Make: ").append(this.make).append("\n");
        repr.append("Model: ").append(this.model).append("\n");
        repr.append("Year: ").append(this.year).append("\n");
        repr.append("Motor Type: ").append(this.type).append("\n");
        repr.append("Number of Wheels: ").append(this.wheelsNumber).append("\n");
        return repr.toString();
    }
}

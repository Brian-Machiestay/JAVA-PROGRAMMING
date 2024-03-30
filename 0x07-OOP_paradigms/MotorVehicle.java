/**
 * This interface declares specific methods that defines features specific to Motorcycles.
 * It is intended to be implemented by Motorcycle classes
 */
public interface MotorVehicle {

    void setWheelsNumber(int num);

    int getWheelsNumber();

    void setMotorType(String type);

    String getMotorType();

}

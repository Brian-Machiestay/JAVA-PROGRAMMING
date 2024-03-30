/**
 * This interface defines the common features that all kinds of vehicles have
 * including the make, model and Year. The ID uniquely identifies a vehicle as there
 * can be several vehicles with the same details
 */
public interface Vehicle {

    String getMake();

    String getModel();

    String getYear();

    String getID();
}

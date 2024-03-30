import java.util.Scanner;

/**
 * This class provides an interactive interface for creating vehicles, updating existing vehicle
 * information and viewing the details of a vehicle
 */
public class VehicleSystem {

    public static int option() {
        System.out.print("Welcome to the Vehicle Management System\n" +
                "Please choose a number corresponding to what you want to do\n" +
                "1. Add new a new Vehicle\n" +
                "2. Update Vehicle Details\n" +
                "3. View Vehicle Details\n" +
                "4. Exit\n>>> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        while (option > 4 || option < 0) {
            System.out.print("You entered a wrong value. Enter a number between 1 and 4 inclusive\n>>> ");
            option = scanner.nextInt();
        }
        return option;
    }

    public static void main(String[] args) {
        int option;
        Scanner scanner = new Scanner(System.in);
        Vehicle[] vehicles = new Vehicle[20];
        int count = 0;
        do {
            option = option();
            switch (option) {
                case 1: {
                    try {
                        System.out.print("Please enter the ID of the new vehicle >>> ");
                        String ID = scanner.next();
                        boolean found = false;
                        for (Vehicle vl : vehicles) {
                            if (vl != null && vl.getID().equals(ID)) {
                                System.out.println("A vehicle with this ID already exists");
                                found = !found;
                            }
                        }
                        if (found) break;
                        System.out.print("Please choose the type of Vehicle\n    a. Car\n    b. Motorcycle\n    c. Truck\n    >>> ");
                        String vehicleType = scanner.next().strip();
                        if (!vehicleType.equals("a") && !vehicleType.equals("b") && !vehicleType.equals("c")) {
                            System.out.println("invalid option");
                            break;
                        }
                        System.out.print("Please enter the make of the vehicle >>> ");
                        String make = scanner.next();
                        System.out.print("Please enter the model of the vehicle >>> ");
                        String model = scanner.next();
                        System.out.print("Please enter year of the vehicle >>> ");
                        String year = scanner.next();
                        switch (vehicleType) {
                            case "a": {
                                Vehicle car = new Car(ID, make, model, year);
                                vehicles[count] = car;
                                count++;
                                break;
                            }
                            case "b": {
                                Vehicle cycle = new MotorCycle(ID, make, model, year);
                                vehicles[count] = cycle;
                                count++;
                                break;
                            }
                            case "c": {
                                Vehicle truck = new Truck(ID, make, model, year);
                                vehicles[count] = truck;
                                count++;
                                break;
                            }
                        }
                        System.out.println("Vehicle added to the System successfully");
                    } catch (Exception e) {
                        System.out.println("invalid input");
                    }
                    break;
                }
                case 2: {
                    try {
                        System.out.print("Please enter the ID of the vehicle to update >>> ");
                        String ID = scanner.next();
                        Vehicle vehicle = null;
                        for (Vehicle vehi : vehicles) {
                            if (vehi != null && vehi.getID().equals(ID)) vehicle = vehi;
                        }
                        if (vehicle == null) {
                            System.out.println("There is no vehicle with this ID");
                            break;
                        }
                        System.out.println("For the ff, leave blank if you do not wish to change the corresponding detail and press ENTER");

                        if (vehicle instanceof Car) {
                            System.out.print("Enter the new fuel type, must be one of (petrol, diesel, electric) >>> ");
                            String fuelType = scanner.next().strip();
                            System.out.print("Enter the number of doors >>> ");
                            int doors = scanner.nextInt();
                            ((Car) vehicle).setDoors(doors);
                            ((Car) vehicle).setFuelType(fuelType);
                        } else if (vehicle instanceof MotorCycle) {
                            System.out.print("Enter the wheels number of this motorcycle >>> ");
                            int wheels = scanner.nextInt();
                            System.out.print("Enter the motor type, must be one of (sport, cruiser, off-road) >>> ");
                            String motorType = scanner.next();
                            ((MotorCycle) vehicle).setMotorType(motorType);
                            ((MotorCycle) vehicle).setWheelsNumber(wheels);
                        } else if (vehicle instanceof Truck) {
                            System.out.print("Enter the capacity of this cargo >>> ");
                            int capacity = scanner.nextInt();
                            System.out.print("Enter the transmission type of this cargo, must be one of (manual, automatic) >>> ");
                            String transmissionType = scanner.next();
                            ((Truck) vehicle).setCargoCapacity(capacity);
                            ((Truck) vehicle).setTransmissionType(transmissionType);
                        }
                        System.out.println("Vehicle updated Successfully");
                    } catch (Exception e) {
                        System.out.println("invalid input");
                        throw e;
                    }
                    break;
                }
                case 3: {
                    try {
                        System.out.print("Please enter the ID of the vehicle to view >>> ");
                        String ID = scanner.next();
                        Vehicle vehicle = null;
                        for (Vehicle vehi : vehicles) {
                            if (vehi != null && vehi.getID().equals(ID)) vehicle = vehi;
                        }
                        if (vehicle == null) {
                            System.out.println("There is no vehicle with this ID");
                            break;
                        }
                        System.out.println(vehicle);

                    } catch (Exception e) {
                        System.out.println("invalid input");
                        throw e;
                    }
                    break;
                }
            }
        } while (option > 0 && option < 4);
    }
}

import java.util.Objects;
import java.util.Scanner;

/**
 * A student record management system. Allows adding new student,
 * updating student records and viewing records based on ID
 */
public class Records {
    private static int totalStudents;

    private static Student[] allStudents = new Student[40];

    private static class Student {
        String name;
        String ID;
        int age;
        double grade;

        Student(String name, String id, int age, double grade) {
            this.name = name;
            this.ID = id;
            this.age = age;
            this.grade = grade;
        }
    }

    public static int option() {
        System.out.print("Welcome to the Student Management System\n" +
                "Please choose a number corresponding to what you want to do\n" +
                "1. Add new student\n" +
                "2. Update a student record\n" +
                "3. View student record\n" +
                "4. Exit\n>>> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        while (option > 4 || option < 0) {
            System.out.print("You entered a wrong value. Enter a number between 1 and 4 inclusive\n>>> ");
            option = scanner.nextInt();
        }
        return option;
    }

    public static void addNewStudent(String ID, String name, int age, double grade) {
        for (int i = 0; i < Records.totalStudents; i++) {
            if (Records.allStudents[i] != null && Records.allStudents[i].ID.equals(ID)) {
                throw new Error("ID already exists");
            }
        }
        Records.Student newStudent = new Records.Student(name, ID, age, grade);
        Records.allStudents[totalStudents] = newStudent;
        Records.totalStudents += 1;
    }

    public static boolean updateStudent(String ID, String name, int age, double grade) {
        boolean found = false;
        for (int i = 0; i < Records.totalStudents; i++) {
            if (Records.allStudents[i] != null && Records.allStudents[i].ID.equals(ID)) {
                Records.allStudents[i].name = name;
                Records.allStudents[i].age = age;
                Records.allStudents[i].grade = grade;
                found = !found;
            }
        }
        return found;
    }

    public static String viewStudent(String ID) {
        String studentInfo = null;
        for (int i = 0; i < Records.totalStudents; i++) {
            if (Records.allStudents[i] != null && Records.allStudents[i].ID.equals(ID)) {
                studentInfo = "Name: " + Records.allStudents[i].name + '\n';
                studentInfo += "Age: " + Records.allStudents[i].age + '\n';
                studentInfo += "Grade: " + Records.allStudents[i].grade + '\n';
            }
        }

        return studentInfo;
    }


    public static void main(String[] args) {

        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            option = option();
            switch (option) {
                case 1: {
                    try {
                        System.out.print("Please enter the ID of the new student >>> ");
                        String ID = scanner.next();
                        System.out.print("Please enter the name of the new student >>> ");
                        String name = scanner.next();
                        System.out.print("Please enter the age of the new student >>> ");
                        int age = scanner.nextInt();
                        System.out.print("Please enter the grade of the new student >>> ");
                        double grade = scanner.nextDouble();
                        try {
                            addNewStudent(ID, name, age, grade);
                            System.out.println("New student added successfully");
                        } catch (Error e) {
                            System.out.println("ID already exists");
                        }
                    } catch (Exception e) {
                        System.out.println("invalid input");
                    }
                    break;
                }
                case 2: {
                    try {
                        System.out.print("Please enter the ID of the student to update >>> ");
                        String ID = scanner.next();
                        System.out.print("Please enter the name of the student to update >>> ");
                        String name = scanner.next();
                        System.out.print("Please enter the age of the student to update >>> ");
                        int age = scanner.nextInt();
                        System.out.print("Please enter the grade of the student to update >>> ");
                        double grade = scanner.nextDouble();
                        boolean found = updateStudent(ID, name, age, grade);
                        if (!found) System.out.println("The student ID was not found");
                        else System.out.println("Student record updated successfully");
                    } catch (Exception e) {
                        System.out.println("invalid input");
                        throw e;
                    }
                    break;
                }
                case 3: {
                    try {
                        System.out.print("Please enter the ID of student to view >>> ");
                        String ID = scanner.next();
                        String studentInfo = viewStudent(ID);
                        System.out.println(Objects.requireNonNullElse("!!!Below is the record found for this ID:\n" + studentInfo, "There is no student with this ID in the records"));
                    } catch (Exception e) {
                        System.out.println("invalid input");
                    }
                    break;
                }
            }
        } while (option > 0 && option < 4);
    }
}

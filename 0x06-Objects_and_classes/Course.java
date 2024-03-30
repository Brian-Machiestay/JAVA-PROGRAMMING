/**
 * The course class implements instance methods for creating a course, updating the totalenrolled students
 * and also getter and setter methods for retrieving and updating some course details
 */
public class Course {
    private final String code;
    private final String name;
    private int maxCapacity;
    private static int totalEnrolledStudents;

    public Course(String code, String name, int maxCapacity) {
        this.code = code;
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static void updateTotalEnrolledStudents() {
        totalEnrolledStudents += 1;
    }

    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    public String toString() {
        return "" + this.name;
    }
}

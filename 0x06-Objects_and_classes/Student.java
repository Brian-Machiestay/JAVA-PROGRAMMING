import java.util.Arrays;

/**
 * The Student class also implements instance methods for enrolling a student in a course and
 * assigning grades. It also implements getter and setter methods for retrieving and updating
 * the details of a sudent
 */
public class Student {
    private String name;
    private final String ID;
    private int countCourses;
    public Course[] enrolledCourses = new Course[5];
    private float[] grades = new float[5];
    public static Student[] students = new Student[50];
    private static int count;

    public Student(String name, String ID) {
        for (Student stu : students) {
            if (stu != null && stu.getID().equals(ID)) throw new Error("ID already exists");
            //System.out.println("was here");
        }
        this.name = name;
        this.ID = ID;
        Arrays.fill(grades, -1);
        if (count >= 50) throw new Error("cannot enroll more than 50 students");
        students[count] = this;
        count += 1;
    }

    public String getID() {
        return this.ID;
    }

    public String getCourses() {
        StringBuilder courses = new StringBuilder("[");
        for (Course cs : enrolledCourses) courses.append(cs.getCode());
        courses.append("]");
        return courses.toString();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void enrollCourse(Course course) {
        if (countCourses == enrolledCourses.length) throw new Error("Maximum enrollment reached");
        for (Course cs : enrolledCourses) {
            if (cs != null && cs.getCode() == course.getCode())
                throw new Error("Student is already enrolled in this course");
        }
        this.enrolledCourses[countCourses] = course;
        countCourses += 1;
    }

    public void assignGrade(Course course, float grade) {
        boolean found = false;
        for (int i = 0; i < enrolledCourses.length; i++) {
            if (enrolledCourses[i] != null && enrolledCourses[i].getCode() == course.getCode()) {
                grades[i] = grade;
                found = !found;
            }
        }
        if (!found) throw new Error("Student is not enrolled in this course");
    }

    public float[] getGrades() {
        return this.grades;
    }

    public String toString() {
        return name + '-' + ID;
    }
}

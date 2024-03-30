import java.util.Scanner;

/**
 * The courseManagement class implements static methods for adding courses, enrolling students,
 * assigning grades and calculating the overall grades. It also implements helper functions that makes
 * interactions with the administrator interface easier.
 */
public class CourseManagement {
    private static Course[] courses = new Course[20];
    private static float[] overallCourseGrades = new float[40];
    private static int countCourse;
    private static int countGrades;

    public static void addCourse(String code, String name, int capacity) {
        for (Course cos : courses) {
            if (cos != null && cos.getCode().equals(code)) throw new Error("This course has already been created");
        }
        Course cs = new Course(code, name, capacity);
        if (countCourse < courses.length) {
            courses[countCourse] = cs;
            countCourse += 1;
        } else throw new Error("Cannot create more than 20 courses");
    }

    public static void enrollStudent(Student stu, Course cs) {
        stu.enrollCourse(cs);
        Course.updateTotalEnrolledStudents();
    }

    public static void assignGrade(Student stu, Course cos, float grade) {
        stu.assignGrade(cos, grade);
    }

    public static float calculateOverallGrades(Student stu) {
        float overall;
        if (countGrades >= overallCourseGrades.length) {
            throw new Error("There are only 20 slots");
        }

        float total = 0;
        int count = 0;
        for (float grade : stu.getGrades()) {
            if (grade >= 0) {
                total += grade;
                count += 1;
            }
        }

        overall = total / count;
        overallCourseGrades[countGrades] = overall;
        countGrades += 1;
        return overall;
    }

    public static Course[] getCourses() {
        return courses;
    }

    public static int option() {
        System.out.print("Welcome to the Student Management System\n" +
                "Please choose a number corresponding to what you want to do\n" +
                "1. Add new a new course\n" +
                "2. Enroll students\n" +
                "3. Assign grade\n" +
                "4. Calculate overall grade\n" +
                "5. Exit\n>>> ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        while (option > 5 || option < 0) {
            System.out.print("You entered a wrong value. Enter a number between 1 and 4 inclusive\n>>> ");
            option = scanner.nextInt();
        }
        return option;
    }

    public static void main(String[] args) {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            option = option();
            switch (option) {
                case 1: {
                    try {
                        System.out.print("Please enter the ID of the new course >>> ");
                        String ID = scanner.next();
                        System.out.print("Please enter the title of the new course >>> ");
                        String name = scanner.next();
                        System.out.print("Please enter the maximum capacity of this course >>> ");
                        int capacity = scanner.nextInt();
                        try {
                            addCourse(ID, name, capacity);
                            System.out.println("New course added successfully");
                        } catch (Error e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (Exception e) {
                        System.out.println("invalid input");
                    }
                    break;
                }
                case 2: {
                    try {
                        System.out.print("Please enter the ID of the student to enroll >>> ");
                        String ID = scanner.next();
                        System.out.print("Please enter the name of the student to enroll >>> ");
                        String name = scanner.next();
                        System.out.print("Please enter the ID of the course to enroll this student in >>> ");
                        String code = scanner.next();
                        Student stu = new Student(name, ID);
                        Course course = null;
                        for (Course cos : courses) {
                            if (cos != null && cos.getCode().equals(code)) course = cos;
                        }
                        if (course != null) {
                            try {
                                enrollStudent(stu, course);
                                System.out.println("Student successfully enrolled in course: " + code);
                            } catch (Error e) {
                                System.out.println(e.getMessage());
                            }
                        } else System.out.println("There is no course with this code in the registry");
                    } catch (Exception e) {
                        System.out.println("invalid input");
                        throw e;
                    }
                    break;
                }
                case 3: {
                    try {
                        System.out.print("Please enter the ID of student to assign a grade >>> ");
                        String studentID = scanner.next();
                        System.out.print("Please enter the ID of the course whose grade to assign >>> ");
                        String courseID = scanner.next();
                        System.out.print("Please enter the grade to assign >>> ");
                        float grade = scanner.nextFloat();
                        Student student = null;
                        Course course = null;
                        for (Student stu : Student.students) {
                            if (stu != null && stu.getID().equals(studentID)) {
                                student = stu;
                            }
                        }
                        if (student == null) {
                            System.out.println("This student is not in the registry");
                            break;
                        }
                        for (Course cse : getCourses()) {
                            if (cse != null && cse.getCode().equals(courseID)) {
                                course = cse;
                            }
                        }
                        if (course == null) {
                            System.out.println("This course is not in the registry");
                            break;
                        }
                        try {
                            assignGrade(student, course, grade);
                            System.out.println("Grade successfully assigned to student");
                        } catch (Error e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (Exception e) {
                        System.out.println("invalid input");
                        throw e;
                    }
                    break;
                }
                case 4: {
                    try {
                        System.out.print("Please enter the ID of the student >>> ");
                        String ID = scanner.next();
                        Student student = null;
                        for (Student stu : Student.students) if (stu != null && stu.getID().equals(ID)) student = stu;
                        if (student == null) {
                            System.out.println("This student is not in the registry");
                            break;
                        }
                        try {
                            System.out.println("The overall grade for this student is " + calculateOverallGrades(student));
                        } catch (Error e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (Exception e) {
                        System.out.println("invalid input");
                        throw e;
                    }
                    break;
                }
            }
        } while (option > 0 && option < 5);
    }
}

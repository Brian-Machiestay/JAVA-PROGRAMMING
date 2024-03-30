import javax.swing.*;

/**
 * This is the main class that creates the window and aggregates all other interfaces
 */
public class StudentManagementSystem {

    private JFrame window;
    private StudentManagement studentUI;
    private CoursesManagement courseUI;

    public StudentManagementSystem() {
        JFrame window = new JFrame("Student Management System");
        this.window = window;
        studentUI = initDisplay();
        window.setJMenuBar(addMenuBar());
        window.add(studentUI);
        window.setSize(700, 400);
        window.setLocation(100, 100);
        window.setVisible(true);
    }

    private JMenuBar addMenuBar() {
        JMenu menu = new JMenu("Menu");
        JMenuItem students = new JMenuItem("Student Management");
        students.addActionListener(e -> {
            //window.removeAll();
            clear();
            studentUI = initDisplay();
            window.add(studentUI);
            window.validate();
        });
        JMenuItem course = new JMenuItem("Course Management");
        course.addActionListener(e -> {
            clear();
            courseUI = coursesManagementUI();
            window.add(courseUI);
            window.validate();
        });
        menu.add(students);
        menu.add(course);
        JMenuBar bar = new JMenuBar();
        bar.add(menu);
        return bar;
    }


    private StudentManagement initDisplay() {
        StudentManagement panel = new StudentManagement();
        return panel;
    }

    private CoursesManagement coursesManagementUI() {
        CoursesManagement panel = new CoursesManagement();
        return panel;
    }

    private void clear() {
        /*
        for (Component cc : window.getComponents()) {
            System.out.println(cc.getHeight());
        }

         */
        if (studentUI != null) window.remove(studentUI);
        if (courseUI != null) window.remove(courseUI);
        window.validate();
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
    }
}

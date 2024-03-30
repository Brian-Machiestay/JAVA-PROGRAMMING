import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CoursesManagement class implement course Management and its features
 * including adding a course, enrolling students in courses and assigning grade
 */
public class CoursesManagement extends JPanel implements ActionListener {

    public static Course[] courses = new Course[20];
    JPanel form = createCourseForm();
    JPanel enroll;
    JPanel gradeform;
    private static int count;

    public CoursesManagement() {
        setLayout(new GridLayout(3, 1));
        //JPanel lbPanel = new JPanel()
        JLabel title = new JLabel("Course Management");
        setLayout(new GridLayout(3, 1));
        add(title);
        add(addButtonsPanel());
        add(form);
    }

    private JPanel addButtonsPanel() {
        JPanel btnPanel = new JPanel();
        JButton add = new JButton("Add Course");
        JButton enroll = new JButton("Enroll Students");
        JButton assign = new JButton("Assign Grade");
        add.addActionListener(this);
        enroll.addActionListener(this);
        assign.addActionListener(this);
        btnPanel.add(add);
        btnPanel.add(enroll);
        btnPanel.add(assign);
        return btnPanel;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add Course": {
                // System.out.println("were going to add a new student");
                updateGUI();
                form = createCourseForm();
                add(form);
                validate();
                break;
            }
            case "Enroll Students": {
                updateGUI();
                enroll = createEnrollStudentForm();
                add(enroll);
                validate();
                break;
            }
            case "Assign Grade": {
                updateGUI();
                gradeform = createGradeForm();
                add(gradeform);
                validate();
                break;
            }
        }
    }

    private JPanel createGradeForm() {
        JPanel grade = new JPanel(new GridLayout(6, 1));
        JComboBox<Student> st = new JComboBox<>();
        for (Student stu : StudentManagement.students) if (stu != null) st.addItem(stu);
        JComboBox<Course> css = new JComboBox<>();
        JPanel gradePn = new JPanel(new GridLayout(1, 2));
        JLabel gradeLb = new JLabel("Grade");
        JTextField gradeFn = new JTextField();
        JLabel errorLb = new JLabel("");
        //JLabel errLB = new JLabel("");
        gradePn.add(gradeLb);
        gradePn.add(gradeFn);
        JButton assignBtn = new JButton("assign");
        assignBtn.addActionListener(e -> {
            try {
                if (st.getSelectedIndex() == -1 || css.getSelectedIndex() == -1)
                    throw new Error("Please select a student and course");
                if (gradeFn.getText().strip().equals("")) throw new Error("Fields cannot be empty");

                float val = Float.parseFloat(gradeFn.getText());
                Student ss = StudentManagement.students[st.getSelectedIndex()];
                Course cc = ss.enrolledCourses[css.getSelectedIndex()];
                ss.assignGrade(cc, val);
                errorLb.setForeground(Color.BLUE);
                errorLb.setText("Grade assigned successfully");
            } catch (Error | Exception ee) {
                errorLb.setForeground(Color.RED);
                errorLb.setText(ee.getMessage());
            }
        });

        st.addActionListener(e -> {
            int i = st.getSelectedIndex();
            System.out.println(StudentManagement.students[i]);
            for (Course cs : StudentManagement.students[i].enrolledCourses) {
                if (cs != null) css.addItem(cs);
            }
            grade.add(css);
            grade.add(gradePn);
            grade.add(assignBtn);
            grade.add(errorLb);
            validate();
        });

        grade.add(st);
        grade.add(new JLabel(""));
        return grade;
    }

    private void updateGUI() {
        Component form = getComponent(2);
        //System.out.println(form);
        if (form != null) remove(form);
        validate();
    }

    private JPanel createCourseForm() {
        JPanel mainForm = new JPanel(new GridLayout(3, 1));
        JPanel courseForm = new JPanel(new GridLayout(2, 6));
        JLabel idLabel = new JLabel("Course ID: ");
        JTextField id = new JTextField();
        JLabel nameLabel = new JLabel("Course name: ");
        JTextField name = new JTextField();
        JLabel maxCpLb = new JLabel("Max capacity: ");
        JTextField maxCp = new JTextField();
        JButton create = new JButton("Add Course");
        JLabel errorLabel = new JLabel("");
        create.addActionListener(e -> {
            String newCseName = name.getText();
            String newCseId = id.getText();
            String max = maxCp.getText();
            try {
                if (newCseId.strip().equals("") || newCseName.strip().equals("") || max.strip().equals(""))
                    throw new Error("Fields cannot be empty");
                addCourse(newCseId, newCseName, max);
                errorLabel.setText("Course added Successfully");
                errorLabel.setForeground(new Color(0, 200, 0, 100));
                validate();
            } catch (Error err) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText(err.getMessage());
                form.validate();
            }
        });

        courseForm.add(idLabel);
        courseForm.add(nameLabel);
        courseForm.add(maxCpLb);
        courseForm.add(id);
        courseForm.add(name);
        courseForm.add(maxCp);

        mainForm.add(courseForm);
        mainForm.add(create);
        mainForm.add(errorLabel);

        return mainForm;
    }

    private JPanel createEnrollStudentForm() {
        JPanel main = new JPanel(new GridLayout(5, 1));
        JComboBox<Course> cs = new JComboBox<>();
        for (Course css : courses) if (css != null) cs.addItem(css);
        JComboBox<Student> stt = new JComboBox<>();
        JButton enrollBtn = new JButton("Enroll");
        JLabel errorLb = new JLabel("");


        enrollBtn.addActionListener(eee -> {
            String selected = stt.getSelectedItem() + "";
            selected = selected.split("-")[1];

            try {
                for (Student ss : StudentManagement.students) {
                    if (ss != null && ss.getID().equals(selected)) {
                        ss.enrollCourse(courses[cs.getSelectedIndex()]);
                    }
                }
                errorLb.setForeground(Color.BLUE);
                errorLb.setText("Student enrolled in course successfully");
            } catch (Error e) {
                errorLb.setForeground(Color.RED);
                errorLb.setText(e.getMessage());
            }
            validate();
        });

        cs.addActionListener(e -> {
            int i = cs.getSelectedIndex();

            for (Student s : StudentManagement.students) {
                if (s != null) {
                    boolean found = false;
                    for (Course c : s.enrolledCourses) {
                        if (c != null && c.getCode().equals(courses[i].getCode())) found = !found;
                    }
                    if (!found) {
                        stt.addItem(s);
                    }
                }
            }
            main.add(stt);
            main.add(enrollBtn);
            main.add(errorLb);
            validate();
        });

        main.add(cs);
        main.add(new JLabel(""));
        return main;
    }

    private void addCourse(String id, String name, String max) {
        int mmax = Integer.parseInt(max);
        Course cs = new Course(id, name, mmax);
        courses[count] = cs;
        count++;
    }

    /*
    public void destroy() {
        remove(this);
    }
    */
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class defines the StudentManagement interface
 * and all its functionalities including, adding, updating and viewing student info
 */
public class StudentManagement extends JPanel implements ActionListener {

    private JPanel form = createStudentForm();
    public static Student[] students = Student.students;
    private static int count;
    private JPanel updateForm;

    public StudentManagement() {
        setLayout(new GridLayout(3, 1));
        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel("Student Management");
        labelPanel.add(label);
        add(label);
        add(addButtonsPanel());
        add(form);
        //Student std1 = new Student("Kofi kaakyire", "ID-001");
        //Student std2 = new Student("Ama give up", "Ama-001");
        //students[0] = std1;
        //students[1] = std2;
    }

    private JPanel addButtonsPanel() {
        JPanel btnPanel = new JPanel();
        JButton add = new JButton("Add Student");
        JButton update = new JButton("Update Student");
        JButton view = new JButton("View Student Details");
        add.addActionListener(this);
        update.addActionListener(this);
        view.addActionListener(this);
        btnPanel.add(add);
        btnPanel.add(update);
        btnPanel.add(view);
        return btnPanel;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add Student": {
                // System.out.println("were going to add a new student");
                updateGUI();
                form = createStudentForm();
                add(form);
                validate();
                break;
            }
            case "Update Student": {
                updateGUI();
                updateForm = createUpdateForm();
                add(updateForm);
                // System.out.println("done");
                validate();
                break;
            }

            case "View Student Details": {
                System.out.println("were in details");
                updateGUI();
                JPanel details = new JPanel(new GridLayout(count + 2, 2));
                JLabel lbTitle = new JLabel("Student ID");
                JLabel nmTitle = new JLabel("Student Name");
                details.add(lbTitle);
                details.add(nmTitle);
                details.add(new JLabel(""));
                details.add(new JLabel(""));
                for (Student stu : students) {
                    if (stu != null) {
                        JLabel lb = new JLabel(stu.getID());
                        lb.setBackground(Color.blue);
                        JLabel nm = new JLabel(stu.getName());
                        nm.setBackground(Color.CYAN);
                        details.add(lb);
                        details.add(nm);
                    }
                }
                add(details);
                validate();
                break;
            }
        }
    }

    private void updateGUI() {
        Component form = this.getComponent(2);
        //System.out.println(form);
        this.remove(form);
        validate();
    }

    private JPanel createStudentForm() {
        JPanel mainForm = new JPanel(new GridLayout(2, 1));
        JPanel form = new JPanel();

        form.setLayout(new GridLayout(1, 5));
        JLabel idLabel = new JLabel("Student ID: ");
        JTextField id = new JTextField();
        JLabel nameLabel = new JLabel("Student name: ");
        JTextField name = new JTextField();
        JButton create = new JButton("Enroll");
        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        create.addActionListener(e -> {
            String newStuName = name.getText();
            String newStuId = id.getText();

            try {
                addStudent(newStuId, newStuName);
                if (newStuName.strip().equals("") || newStuId.strip().equals(""))
                    throw new Error("Fields cannot be empty");
                errorLabel.setText("Student added Successfully");
                errorLabel.setForeground(new Color(0, 200, 0, 100));
            } catch (Error err) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText(err.getMessage());
                form.validate();
            }
        });

        form.add(idLabel);
        form.add(id);
        form.add(nameLabel);
        form.add(name);
        form.add(create);
        form.add(errorLabel);
        mainForm.add(form);
        mainForm.add(errorLabel);
        return mainForm;
    }

    private JPanel createUpdateForm() {
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(2, 1));
        Student[] studs = new Student[count];
        if (count >= 0) System.arraycopy(students, 0, studs, 0, count);
        for (Student ss : studs) if (ss != null) System.out.println(ss);
        //System.out.println(studs);
        JComboBox<Student> studentList = new JComboBox<>(studs);
        JPanel details = new JPanel(new GridLayout(1, 3));
        studentList.addActionListener(e -> {
            //System.out.println(Arrays.toString(studs));
            details.removeAll();
            int sel = studentList.getSelectedIndex();
            JLabel nameLabel = new JLabel("Student name: ");
            JTextField name = new JTextField(studs[sel].getName());
            JButton done = new JButton("update");
            done.addActionListener(action -> {
                String newName = name.getText();
                System.out.println(newName);
                students[sel].setName(newName);
                details.validate();
            });
            details.add(nameLabel);
            details.add(name);
            details.add(done);
            form.add(details);
            form.validate();
        });
        //studentList.setSelectedIndex(0);
        form.add(studentList);

        return form;
    }
    /*
    public void destroy() {
        remove(this);
        validate();
    }

     */

    private void addStudent(String id, String name) {
        new Student(name, id);
        count += 1;
    }
}

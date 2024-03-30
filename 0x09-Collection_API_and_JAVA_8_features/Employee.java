import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The employee class defines attributes associated with each employee object including name, age, department
 * and salary. public methods are defined to retrieve these attributes.
 * <p>
 * The main routine uses java's stream, collection and Funtion APIs to read data and process them with improved speed.
 * This might not be apparent on a small dataset but the real power is revealed if the dataset is very large.
 */
public class Employee {

    private final int age, salary;
    private final String name, department;

    public Employee(String name, int age, String department, int salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public static void main(String[] args) {
        Employee[] emps = new Employee[]{
                new Employee("Emma", 20, "IT", 10000),
                new Employee("Patrick", 24, "Mathematics", 7000),
                new Employee("Brian", 31, "Hardware systems", 7700),
                new Employee("Anthony", 45, "Humanities", 4000),
                new Employee("Kingsley", 51, "Social Sciences", 3500),
                new Employee("Lucas", 28, "Human Resource", 5000),
                new Employee("Alvin", 35, "Accounting", 8600),
                new Employee("Dorcas", 38, "Disciplinary", 4500),
                new Employee("Elsie", 53, "Physics", 6000),
        };

        Function<Employee, String> nameDepart = (employee -> employee.getName() + '-' + employee.getDepartment());

        ArrayList<Employee> employees = new ArrayList<>(Arrays.asList(emps));

        ArrayList<String> concat = (ArrayList<String>) employees.parallelStream().map(nameDepart).collect(Collectors.toList());

        double aveSalaries = employees.parallelStream().mapToInt(Employee::getSalary).sum() / (double) employees.size();

        double aveSalaries30 = employees.stream().filter(ee -> ee.getAge() > 30).mapToInt(Employee::getSalary).sum() / (double) employees.size();

        System.out.println("This is the list of concatenated employees and their departments");
        for (String ss : concat) System.out.println(ss);

        System.out.println("The average salary of all employees is: " + aveSalaries);

        System.out.println("The average salary of employees older than 30 is: " + aveSalaries30);

    }
}

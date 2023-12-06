package petProject1.task2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Employee> employeeList = makingTeam();
        employeeList.forEach(System.out::println);
        System.out.println("________________________________________________________________________");
        gettingDepartments(employeeList);
        salaryIncrease(employeeList);
        separatingByDepartments(employeeList);

    }

    public static List<Employee> makingTeam() {
        return List.of(
                new Employee("Igor", 59, 2750, "Management"),
                new Employee("Tatiana", 60, 700, "Extract Documents"),
                new Employee("Valentina", 54, 670, "Extract Documents"),
                new Employee("Oleg", 48, 800, "Logistics"),
                new Employee("Dmitriy", 20, 900, "Logistics"),
                new Employee("Ivan", 26, 3200, "IT"),
                new Employee("Alexander", 32, 3700, "IT"),
                new Employee("Alex", 28, 3500, "IT"),
                new Employee("Petr", 20, 900, "Production"),
                new Employee("Gleb", 25, 950, "Production"),
                new Employee("Konstantin", 40, 1700, "Sales"),
                new Employee("Vladimir", 35, 2000, "Sales"),
                new Employee("Ivan", 28, 2100, "Sales"),
                new Employee("Alla", 30, 1500, "Sales"),
                new Employee("Olga", 33, 2400, "Sales"));
    }

    private static void gettingDepartments(List<Employee> employeeList){
        employeeList.stream()
                .map(Employee::getDepartment)
                .distinct()
                .forEach(System.out::println);
        System.out.println("________________________________________________________________________");
    }

    private static void salaryIncrease(List<Employee> employeeList) {
        employeeList.stream()
                .filter(employee -> employee.getSalary() <= 1000)
                .forEach(employee -> employee.setSalary(employee.getSalary() * 1.2));
        System.out.println();
        employeeList.forEach(System.out::println);
        System.out.println("________________________________________________________________________");
    }

    private static void separatingByDepartments(List<Employee> employeeList){
        Map<String, List<Employee>> sepByDep = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        sepByDep.entrySet()
                .iterator()
                .forEachRemaining(System.out::println);
        System.out.println("________________________________________________________________________");
    }

}

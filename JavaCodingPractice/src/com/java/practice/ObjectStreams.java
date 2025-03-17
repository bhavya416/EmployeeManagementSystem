package com.java.practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectStreams {

	public static void main(String[] args) {

		List<Employee> emplist = Arrays.asList(new Employee("Alice", "E001", 60000, "IT", 5),
				new Employee("Bob", "E002", 75000, "HR", 8), new Employee("Charlie", "E003", 50000, "IT", 2),
				new Employee("David", "E004", 90000, "Finance", 10), new Employee("Eve", "E005", 55000, "HR", 3),
				new Employee("Frank", "E006", 65000, "Finance", 6), new Employee("Grace", "E007", 80000, "IT", 9),
				new Employee("Hank", "E008", 45000, "Marketing", 1), new Employee("Ivy", "E009", 70000, "Marketing", 7),
				new Employee("Mac", "E010", 95000, "IT", 12), new Employee("Jack", "E011", 95000, "IT", 12),
				new Employee("jackson", "E012", 50000, "IT", 2));

		partitionEmployeeS(emplist);
		highestSalary(emplist);
		groupByDept(emplist);
		employeesExUnder3(emplist);
		avgSalaryByDept(emplist);
		sortEmpByName(emplist);
		UniqueDepts(emplist);
		employeeCountInEachDept(emplist);
		empWithLongestName(emplist);
		sumofSalariesWithExabove6(emplist);
		finalReview(emplist);

	}

	private static void partitionEmployeeS(List<Employee> emplist) {
		Map<Boolean, List<Employee>> map = emplist.stream()
				.collect(Collectors.partitioningBy(s -> s.getSalary() > 50000, Collectors.toList()));
		System.out.println("partitionEmployeeS  " + map);
	}

	private static void highestSalary(List<Employee> emplist) {

		double maxSalary = emplist.stream().max(Comparator.comparingDouble(Employee::getSalary)).map(e -> e.getSalary())
				.orElse(null);
		List<Employee> emp = emplist.stream().filter(e -> e.salary == maxSalary).collect(Collectors.toList());

		System.out.println("highestSalary:: " + emp);

	}

	private static void groupByDept(List<Employee> emplist) {
		Map<String, List<Employee>> map = emplist.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		System.out.println("groupByDept:: " + map);
	}

	private static void employeesExUnder3(List<Employee> emplist) {
		List<Employee> emp = emplist.stream().filter(e -> e.getExperience() < 3).collect(Collectors.toList());
		System.out.println("employeesExUnder3:: " + emp);
	}

	private static void avgSalaryByDept(List<Employee> emplist) {
		Map<String, Double> map = emplist.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println("avgSalaryByDept:: " + map);
		Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
	}

	private static void sortEmpByName(List<Employee> emplist) {
		List<Employee> empl = emplist.stream().sorted(Comparator.comparing(Employee::getName))
				.collect(Collectors.toList());
		System.out.println("sortEmpByName:: " + empl);
	}

	private static void UniqueDepts(List<Employee> emplist) {
		List<String> dept = emplist.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
		System.out.println("UniqueDepts:: " + dept);
	}

	private static void employeeCountInEachDept(List<Employee> emplist) {
		Map<String, Long> emp = emplist.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println("employeeCountInEachDept:: " + emp);
	}

	private static void empWithLongestName(List<Employee> emplist) {

		String name = emplist.stream().map(Employee::getName).max(Comparator.comparing(String::length)).orElse(null);
		List<Employee> emp = emplist.stream().filter(s -> s.getName().length() == name.length())
				.collect(Collectors.toList());
		System.out.println("empWithLongestName:: " + emp);
	}

	private static void sumofSalariesWithExabove6(List<Employee> emplist) {

		double d = emplist.stream().filter(e -> e.getExperience() > 6)
				.collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println("sumofSalariesWithExabove6:: " + d);
	}

	private static void finalReview(List<Employee> employees) {

//		1.Calculate the average salary of employees who are 30 years old or older.
//		2.check if there is any employee with a salary greater than 100,000.
//		3. check if there is no employee with a salary below 50,000.
		System.out.println(employees.stream().filter(e -> e.getExperience() >= 30)
				.collect(Collectors.averagingDouble(e -> e.getSalary())));

		System.out.println(employees.stream().anyMatch(e -> e.getSalary() > 100000));

		System.out.println(employees.stream().anyMatch(e -> e.getSalary() < 50000));

	}

}

class Employee {

	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", salary=" + salary + ", department=" + department
				+ ", experience=" + experience + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Employee(String name, String id, double salary, String department, int experience) {
		super();
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.department = department;
		this.experience = experience;
	}

	String name;
	String id;
	double salary;
	String department;
	int experience;
}

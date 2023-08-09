package main.java;

import java.util.List;
import java.util.Scanner;

import main.java.dbManager.CourseDBManager;
import main.java.dbManager.DepartmentDBManger;
import main.java.dbManager.StudentDBManger;
import main.java.model.Course;
import main.java.model.Department;
import main.java.model.Student;

public class Main {

	StudentDBManger stDBManagement = new StudentDBManger();
	DepartmentDBManger deptDBManger = new DepartmentDBManger();
	CourseDBManager courseDBManager = new CourseDBManager();

//--------------------------------------------------------------------
	// DEPARTMENT MANAGEMENT
	public void departmentManagement() {
		boolean exit=false;
		while(exit == false) {
		System.out.println("---------- WELECOME TO DEPARTMENT MANAGEMENT----------");
		System.out.println("Select from following: ");
		System.out.println("1- Add Department");
		System.out.println("2- Update Department");
		System.out.println("3- Delete Department");
		System.out.println("4- Get Department");
		System.out.println("5- Get All Department");
		System.out.println("6- Exit");
		Scanner sc = new Scanner(System.in);
		System.out.print("Choice : ");
		int choice = sc.nextInt();

		switch (choice) {

		case 1: {

			deptDBManger.addDepartment(addDept());

			break;
		}
		case 2: {
			deptDBManger.updateDepartment(updateDept());
			break;
		}
		case 3: {
			deptDBManger.deleteDepartment(deleteDept());
			break;
		}
		case 4: {
			deptDBManger.getDepartmentInfo(getDeptInfo());
			break;
		}
		case 5: {
			getAllDept();
			break;
		}
		case 6: {
			exit=true;
			break;
		}
		default: {
			System.out.println("Invalid Choice!!!");
		}

		}
		}
	}

	// adding department
	public Department addDept() {
		Scanner sc = new Scanner(System.in);
		Department dept = new Department();

		System.out.println("************************");
		System.out.println("Enter department ID: ");
		dept.setId(sc.nextInt());
		System.out.println("Enter department Name: ");
		dept.setName(sc.next());

		return dept;
	}

	// deleting department
	public Integer deleteDept() {
		System.out.println("************************");
		System.out.println("Enter department ID you want to Delete: ");
		Scanner sc = new Scanner(System.in);
		Integer id = sc.nextInt();

		return id;
	}

	// updating department
	public Department updateDept() {
		System.out.println("************************");
		System.out.println("Enter department ID you want to Update: ");
		Scanner sc = new Scanner(System.in);
		Integer id = sc.nextInt();

		Department d = deptDBManger.getDepartment(id);
		d.setId(id);
		System.out.println("Enter  Department Name: ");
		sc.nextLine();
		d.setName(sc.nextLine());

		return d;

	}

	// printing all department Info

	public void getAllDept() {
		List<Department> list = deptDBManger.getAllDepartment();
		for (Department d : list) {
			System.out.println("---------");
			System.out.println("ID : " + d.getId());
			System.out.println("Name : " + d.getName());
		}
	}

	// printing all department Info
	public Integer getDeptInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("************************");
		System.out.println("Enter Department ID: ");

		return sc.nextInt();
	}

	// --------------------------------------------------------------------
	// STUDENT MANAGEMENT
	public void studentManagement() {
		boolean exit=false;
		while(exit == false) {
		System.out.println("\n---------- WELECOME TO STUDENT MANAGEMENT----------");
		System.out.println("Select from following: ");
		System.out.println("1- Add Student");
		System.out.println("2- Update Student");
		System.out.println("3- Delete Student");
		System.out.println("4- Get Student Info");
		System.out.println("5- Get All Student Info");
		System.out.println("6- Exit");
		Scanner sc = new Scanner(System.in);
		System.out.print("Choice : ");
		int choice = sc.nextInt();

		switch (choice) {

		case 1: {

			stDBManagement.addStudent(add());

			break;
		}
		case 2: {
			stDBManagement.updateStudent(update());
			break;
		}
		case 3: {
			stDBManagement.deleteStudent(deleteStudent());
			break;
		}
		case 4: {
			stDBManagement.getStudentInfo(getStudentInfo());
			break;
		}
		case 5: {
			stDBManagement.getAllStudentInfo();
			break;
		}
		case 6: {
			exit=true;
			break;
		}
		default: {
			System.out.println("Invalid Choice!!!");
		}

		}
		}
	}

	public Student add() {
		Scanner sc = new Scanner(System.in);
		Student st = new Student();
		System.out.println("************************");
		System.out.println("Enter student ID: ");
		st.setId(sc.nextInt());
		System.out.println("Enter student name: ");
		st.setName(sc.next());
		System.out.println("Enter student age: ");
		st.setAge(sc.nextInt());
		System.out.println("Enter student email: ");
		st.setEmail(sc.next());
		
		boolean exit=false;
		Department d = new Department();
		while(exit==false) {
		System.out.println("Enter Department ID from following: ");

		List<Department> list = deptDBManger.getAllDepartment();
		System.out.println("    ID  NAME");
		System.out.println("    --------");
		for (Department dept : list) {
			System.out.print("    "+dept.getId() + " ");
			System.out.println("    "+dept.getName());
		}
		Integer ch = sc.nextInt();
		

		for (Department dpt : list) {
			if (dpt.getId() == ch) {
				d.setId(ch);
				exit=true;
			}else if(dpt.getId() == ch && (list.indexOf(dpt)==list.size()-1)){
				System.out.println("It doesn't exist, try again");
			}
		}
		}
		st.setDepartment(d);

		return st;
	}

	// Update student
	public Student update() {
		Scanner sc = new Scanner(System.in);

		System.out.println("************************");
		System.out.println("Enter the student ID you want to Update.");
		Integer id = sc.nextInt();

		Student st = stDBManagement.getStudent(id);
		st.setId(id);
		System.out.println("Enter the new Name: ");
		sc.nextLine();
		st.setName(sc.nextLine());

		return st;
	}

	// Get Single student Info
	public Integer getStudentInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("************************");
		System.out.println("Enter Student ID: ");

		return sc.nextInt();
	}

	// deleting department
	public Integer deleteStudent() {
		System.out.println("************************");
		System.out.println("Enter student ID you want to Delete: ");
		Scanner sc = new Scanner(System.in);
		Integer id = sc.nextInt();

		return id;
	}
// --------------------------------------------------------------------
	// COURSE MANAGEMENT

	public void courseManagement() {
		boolean exit=false;
		while(exit == false) {
		System.out.println("\n---------- WELECOME TO COURSE MANAGEMENT----------");
		System.out.println("Select from following: ");
		System.out.println("1- Add Course");
		System.out.println("2- Update Course");
		System.out.println("3- Delete Course");
		System.out.println("4- Get Course Info");
		System.out.println("5- Get All Courses Info");
		System.out.println("6- Exit");
		Scanner sc = new Scanner(System.in);
		System.out.print("Choice : ");
		int choice = sc.nextInt();

		switch (choice) {

		case 1: {
			courseDBManager.addCourse(addCourse());
			break;
		}
		case 2: {
			courseDBManager.updateCourse(updateCourse());
			break;
		}
		case 3: {
			courseDBManager.deleteCourse(deleteCourse());
			break;
		}
		case 4: {
			courseDBManager.getCourseInfo(getCourseInfo());
			break;
		}
		case 5: {
			getAllCourses();
			break;
		}
		case 6: {
			exit=true;
			break;
		}
		default: {
			System.out.println("Invalid choice!!!");
		}

		}
		}
	}

	// ADD
	public Course addCourse() {
		Scanner sc = new Scanner(System.in);
		Course c = new Course();

		System.out.println("************************");
		System.out.println("Enter course ID: ");
		c.setId(sc.nextInt());
		System.out.println("Enter course Name: ");
		c.setName(sc.next());

		return c;

	}

	// Update

	public Course updateCourse() {
		Scanner sc = new Scanner(System.in);

		System.out.println("************************");
		System.out.println("Enter the course ID you want to Update.");
		Integer id = sc.nextInt();

		Course c = courseDBManager.getCourse(id);
		c.setId(id);
		System.out.println("Enter the new Course Name: ");
		sc.nextLine();
		c.setName(sc.nextLine());

		return c;
	}

	// Delete Course
	public Integer deleteCourse() {
		System.out.println("************************");
		System.out.println("Enter course ID you want to Delete: ");
		Scanner sc = new Scanner(System.in);
		Integer id = sc.nextInt();

		return id;
	}

	// Get Single course Info
	public Integer getCourseInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("************************");
		System.out.println("Enter Course ID: ");

		return sc.nextInt();
	}

	// Get all courses Info

	public void getAllCourses() {
		List<Course> list = courseDBManager.getAllCourses();
		for (Course d : list) {
			System.out.println("---------");
			System.out.println("ID : " + d.getId());
			System.out.println("Name : " + d.getName());
		}
	}
	// ----------------------- MAIN Method Start here

	public static void main(String[] args) {

		boolean exit=false;
		while(exit==false) {
			System.out.println();
		System.out.println(" __________________________________________");
		System.out.println("|  WELECOME TO COLLEGE MANAGEMENT SYSTEM   |");
		System.out.println("|__________________________________________|");
		System.out.println("What do you want to manage:");
		System.out.println("1- Student Management");
		System.out.println("2- Department Management");
		System.out.println("3- Course Management");
		System.out.println("4- Exit");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.print("Choice : ");
		int choice = sc.nextInt();
		Main m = new Main();
		switch (choice) {

		case 1: {
			m.studentManagement();
			break;
		}
		case 2: {
			m.departmentManagement();
			break;
		}
		case 3: {
			m.courseManagement();
			break;
		}
		case 4: {
			exit=true;
			break;
		}
		default: {
			System.out.println("Invalid Choice!!!");
		}

		}
	}
	}

}

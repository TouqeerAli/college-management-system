package main.java.dbManager;

import main.java.model.Department;
import main.java.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import main.java.dbConnection.DBConnection;

public class StudentDBManger {

	private final String INSERT_QUERY = "INSERT INTO student (st_id,st_name,st_age,st_email,dept_id) VALUES (?, ?, ?,?,?)";
	private final String UPDATE_QUERY = "UPDATE student  SET  st_name = ? , st_age = ?, st_email = ?,dept_id=? "
			+ "WHERE st_id=?";
	private final String DELETE_QUERY = "DELETE FROM student WHERE st_id=?";
	private final String SELECT_BY_ID_QUERY = "SELECT st_id,st_name, st_age, st_email,dept_id FROM student WHERE st_id= ?";
	private final String SELECT_QUERY_WITH_JOIN_FOR_SINGLE_RECORD = "SELECT s.st_id,s.st_name, s.st_age, s.st_email,s.dept_id,d.dept_name"
			+ " FROM student s inner join department d on s.dept_id=d.dept_id where s.st_id=?";
	private final String SELECT_QUERY_WITH_JOIN = "SELECT s.st_id,s.st_name, s.st_age, s.st_email,s.dept_id,d.dept_name\r\n"
			+ "FROM student s inner join department d on s.dept_id=d.dept_id;";
	private final String SELECT_QUERY_COURSE = "SELECT s.st_id,sc.c_id,c.c_name,sc.marks FROM student s inner join student_course sc on s.st_id=sc.st_id "
			+ "inner join course c on c.c_id=sc.c_id where  s.st_id=?";

	// Add Student
	public void addStudent(Student student) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
			ps.setInt(1, student.getId());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getAge());
			ps.setString(4, student.getEmail());

			ps.setInt(5, student.getDepartment().getId());
			ps.execute();
			System.out.println("Student added.");
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Update Student

	public void updateStudent(Student student) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getAge());
			ps.setString(3, student.getEmail());

			ps.setInt(4, student.getDepartment().getId());
			ps.setInt(5, student.getId());
			ps.execute();
			con.close();
			System.out.println("Student Updated.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Get Student

	public Student getStudent(Integer id) {
		Connection con = DBConnection.getConnection();
		Student st = new Student();

		try {
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY);
			ps.setInt(1, id);
			ResultSet rst = ps.executeQuery();

			while (rst.next()) {
				st.setId(rst.getInt("st_id"));
				st.setName(rst.getString("st_name"));
				st.setAge(rst.getInt("st_age"));
				st.setEmail(rst.getString("st_email"));
				Department d = new Department();
				d.setId(rst.getInt("dept_id"));
				st.setDepartment(d);
			}
			return st;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// Get Student Info
	public void getStudentInfo(Integer id) {
		Connection con = DBConnection.getConnection();
		Student d = new Student();
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_QUERY_WITH_JOIN_FOR_SINGLE_RECORD);
			PreparedStatement ps1 = con.prepareStatement(SELECT_QUERY_COURSE);
			ps.setInt(1, id);
			ResultSet rst = ps.executeQuery();
			while (rst.next()) {
				System.out.println("	" + rst.getString("st_name"));
				System.out.println("-------------------------");
				System.out.println("ID  		: " + rst.getInt("st_id"));
				System.out.println("Age  		: " + rst.getInt("st_age"));
				System.out.println("Email  		: " + rst.getString("st_email"));
				System.out.println("Department ID   : " + rst.getString("dept_id"));
				System.out.println("Department Name	: " + rst.getString("dept_name"));

				ps1.setInt(1, id);
				ResultSet rst1 = ps1.executeQuery();
				int count = 1;
				System.out.println("\nCourses	enrolled:");
				System.out.println("________________________");
				while (rst1.next()) {

					System.out.println("Course No   : " + count);
					System.out.println("Course ID   : " + rst1.getInt("sc.c_id"));
					System.out.println("Course Name : " + rst1.getString("c.c_name"));
					System.out.println("Marks       : " + rst1.getInt("sc.marks"));
					System.out.println("====================");
					System.out.println();
					count++;
				}

			}
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Get All Student Info
	public void getAllStudentInfo() {
		Connection con = DBConnection.getConnection();
		Student d = new Student();
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_QUERY_WITH_JOIN);
			PreparedStatement ps1 = con.prepareStatement(SELECT_QUERY_COURSE);

			ResultSet rst = ps.executeQuery();

			while (rst.next()) {
				System.out.println("	" + rst.getString("st_name"));
				System.out.println("-------------------------");
				System.out.println("ID  		: " + rst.getInt("st_id"));
				System.out.println("Age  		: " + rst.getInt("st_age"));
				System.out.println("Email  		: " + rst.getString("st_email"));
				System.out.println("Department ID   : " + rst.getString("dept_id"));
				System.out.println("Department Name	: " + rst.getString("dept_name"));
				int columnValue = rst.getInt("st_id");
				ps1.setInt(1, columnValue);
				ResultSet rst1 = ps1.executeQuery();
				int count = 1;
				System.out.println("\nCourses	enrolled:");
				System.out.println("________________________");
				while (rst1.next()) {

					System.out.println("Course No   : " + count);
					System.out.println("Course ID   : " + rst1.getInt("sc.c_id"));
					System.out.println("Course Name : " + rst1.getString("c.c_name"));
					System.out.println("Marks       : " + rst1.getInt("sc.marks"));
					System.out.println("====================");
					System.out.println();
					count++;
				}

			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Delete Student

	public void deleteStudent(Integer id) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(DELETE_QUERY);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			System.out.println("Student deleted.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

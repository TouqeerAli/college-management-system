package main.java.dbManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.dbConnection.DBConnection;
import main.java.model.Department;

public class DepartmentDBManger {
	private final String SELECT_QUERY = "SELECT * FROM department";
	private final String INSERT_QUERY = "INSERT INTO department(dept_id, dept_name) VALUES (?, ?)";
	private final String DELETE_QUERY = "DELETE FROM department WHERE dept_id=?";
	private final String UPDATE_QUERY = "UPDATE Department SET dept_name=? WHERE dept_id=?";
	private final String SELECT_BY_ID_QUERY = "SELECT dept_id,dept_name FROM department WHERE dept_id= ?";

	public List<Department> getAllDepartment() {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(SELECT_QUERY);
			ResultSet rst = ps.executeQuery();

			List<Department> deptList = new ArrayList<>();

			while (rst.next()) {
				Department dept = new Department();
				dept.setId(rst.getInt("dept_id"));
				dept.setName(rst.getString("dept_name"));

				deptList.add(dept);
			}
			return deptList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// Get Department

	public Department getDepartment(Integer id) {
		Connection con = DBConnection.getConnection();
		Department d = new Department();
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY);
			ps.setInt(1, id);
			ResultSet rst = ps.executeQuery();
			while (rst.next()) {
				d.setId(rst.getInt("dept_id"));
				d.setName(rst.getString("dept_name"));
			}
			con.close();
			return d;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	// Get Single Department Info

	public void getDepartmentInfo(Integer id) {
		Connection con = DBConnection.getConnection();
		// Department d = new Department();
		try {
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_QUERY);
			ps.setInt(1, id);
			ResultSet rst = ps.executeQuery();
			while (rst.next()) {
				System.out.println("ID : " + rst.getInt("dept_id"));
				System.out.println("Name : " + rst.getString("dept_name"));

			}
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Add Department

	public void addDepartment(Department department) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
			ps.setInt(1, department.getId());
			ps.setString(2, department.getName());

			ps.execute();

			con.close();
			System.out.println("Department Added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Delete Department

	public void deleteDepartment(Integer id) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(DELETE_QUERY);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			System.out.println("Department deleted.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Update Department
	public void updateDepartment(Department department) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
			ps.setString(1, department.getName());
			ps.setInt(2, department.getId());
			ps.execute();
			ps.close();
			System.out.println("Department Updated.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

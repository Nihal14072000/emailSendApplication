package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class emailSendDao {

	@Autowired
	private DbConfig dbConf;


	public void save(Employee employee) {
		String query = "insert into demo (name, role,mail_body,id) values (?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dbConf.dataSource().getConnection();
			ps = con.prepareStatement(query);
			int id = getAll().size();
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getRole());
			ps.setString(3, employee.getMessageBody());
			ps.setInt(4, id + 1);
			
			/*execute insert*/
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public Employee getById(int id) {
		String query = "select name, role from demo where id = ?";
		Employee emp = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dbConf.dataSource().getConnection();
			ps = con.prepareStatement(query);
			System.out.print(ps);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				emp = new Employee();
				emp.setId(id);
				emp.setName(rs.getString("name"));
				// emp.setRole(rs.getString("role"));
				System.out.println("Employee Found::" + emp);
			} else {
				System.out.println("No Employee found with id=" + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	public void update(Employee employee) {
		String query = "update demo set name=?, role=?, messagebody=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dbConf.dataSource().getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getRole());
			ps.setString(3, employee.getMessageBody());
			ps.setInt(4, employee.getId());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Employee updated with id=" + employee.getId());
			} else
				System.out.println("No Employee found with id=" + employee.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteById(int id) {
		String query = "delete from demo where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dbConf.dataSource().getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Employee deleted with id=" + id);
			} else
				System.out.println("No Employee found with id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// @Override
	public List<Employee> getAll() {
		String query = "select id, name, role from demo";
		List<Employee> empList = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dbConf.dataSource().getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setRole(rs.getString("role"));
				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

	public List<Employee> getByMailId(String mailId) {
		String query = "select id, name, role from demo where login_id = ?";
		List<Employee> empList = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dbConf.dataSource().getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setRole(rs.getString("role"));
				empList.add(emp);
				return empList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

}
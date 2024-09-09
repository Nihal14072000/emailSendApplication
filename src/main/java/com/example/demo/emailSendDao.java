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
	
	public List<User> getAllUsers() {
		String query = "select id from sys_app_user";
		List<User> userList = new ArrayList<User>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dbConf.dataSource().getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User emp = new User();
				emp.setId(rs.getInt("id"));
				userList.add(emp);
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
		return userList;
	}

	public User getByMailId(User user) {
		String query = "select id, login_id,first_name,last_name, role from sys_app_user where login_id = ? and password=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = new User();
		try {
			con = dbConf.dataSource().getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getMailId());
			ps.setString(2, user.getPassword());
			System.out.println(ps);
			rs = ps.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				u.setMailId(rs.getString("login_id"));
				u.setId(rs.getInt("id"));
				u.setfName(rs.getString("first_name"));
				u.setlName(rs.getString("last_name"));
				u.setRole(rs.getString("role"));
				//u.setRole(rs.getString("role"));
				//empList.add(u);
				return u;
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
		return u;
	}

	public User saveUser(User user,String body) {
		String query = "insert into demo (id,name, role,mail_body) values (?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dbConf.dataSource().getConnection();
			ps = con.prepareStatement(query);
			int id = getAllUsers().size();
			ps.setInt(1, id + 1);
			ps.setString(2, user.getMailId());
			ps.setString(3, user.getRole());
			ps.setString(4, body);
			
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
		return user;
	}

	public User getUserByMailId(User user) {
		String query = "select id, login_id,first_name,last_name, role from sys_app_user where login_id = ?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = new User();
		try {
			con = dbConf.dataSource().getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getMailId());
			System.out.println(ps);
			rs = ps.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				u.setMailId(rs.getString("login_id"));
				u.setId(rs.getInt("id"));
				u.setfName(rs.getString("first_name"));
				u.setlName(rs.getString("last_name"));
				u.setRole(rs.getString("role"));
				return u;
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
		return u;
	}

}
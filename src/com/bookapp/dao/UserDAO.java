package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookapp.model.User;
import com.bookapp.util.ConnectionUtil;


public class UserDAO {
	public void register(User user) 
	{
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into users(name,email,passsword) values(?,?,?)";
		PreparedStatement pst = null;
		try {pst = con.prepareStatement(sql);
		pst.setString(1, user.getName());
		pst.setString(2, user.getEmail());
			pst.setString(3, user.getPassword());
			int rows = pst.executeUpdate();
			System.out.println("Number of Rows Inserted :" + rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionUtil.close(con,pst,null);
		}
		
		
	}
	public boolean login(User user) {
		Connection con = ConnectionUtil.getConnection();
		String sql = "select id,name,passsword,email,active from users where email=? and passsword=?";
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			//User u = new User();
			
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				flag= true;
//				u.setId(rs.getInt("id"));
//				u.setName(rs.getString("name"));
//				u.setPassword(rs.getString("passsword"));
//				u.setEmail(rs.getString("email"));
//				u.setActive(rs.getInt("active"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			ConnectionUtil.close(con,pst,null);
			
			
		}
		return flag;
	}
	public List<User> findAll() {
		Connection con = ConnectionUtil.getConnection();
		String sql = "select id,name,passsword,email,active from users";
		PreparedStatement pst= null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			//boolean flag = false;
		    rs = pst.executeQuery();
			while(rs.next())
			{
				//flag= true;
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("passsword"));
				u.setEmail(rs.getString("email"));
				u.setActive(rs.getInt("active"));
				users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pst.setString(1, user.getEmail());
		//pst.setString(2, user.getPassword());
		finally{
			ConnectionUtil.close(con, pst, rs);
		}
		return users;
	}
	public void updatePassword(String email,String oldpass,String newpass) {
		Connection con = ConnectionUtil.getConnection();
		String sql = "update users set passsword=? where email=? and passsword=?";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, newpass);
			pst.setString(2, email);
			pst.setString(3, oldpass);
			int rows = pst.executeUpdate();
			System.out.println("Number of Rows Updated :" + rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionUtil.close(con, pst, null);
		}
		
	}

}

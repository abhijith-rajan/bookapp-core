package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookapp.model.Book;
import com.bookapp.util.ConnectionUtil;

public class BookDAO {

	public void add(Book book) 
	{
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into books(name,price,author) values(?,?,?)";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, book.getName());
			pst.setInt(2, book.getPrice());
			pst.setString(3, book.getAuthor());
			int rows = pst.executeUpdate();
			System.out.println("Number of Rows Inserted :" + rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionUtil.close(con, pst, null);
		}
		
	}
	public void changePrice(Book book){
		
		Connection con= ConnectionUtil.getConnection();
		String sql = "update books set price= ? where id= ?";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setDouble(1, book.getPrice());
			pst.setInt(2, book.getId());
			int rows = pst.executeUpdate();
			System.out.println("Number of rows updated :"+ rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionUtil.close(con, pst, null);
		}

   }
	public void remove(int id)
	{
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("delete from books where book_id = ?");
			pst.setInt(1, id);
			int rows = pst.executeUpdate();
			System.out.print("no of rows deleted :" + rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("\n\t" + book.getBookid());
		finally{
			ConnectionUtil.close(con, pst, null);
		}
	}
	public ArrayList<Book> allBook()  {
		ArrayList<Book> data=new ArrayList<>();
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs= null;
		try {
			pst = con.prepareStatement("Select id,Name,Price from books");
			rs = pst.executeQuery();
			while (rs.next()) {
				Book bookbuff = new Book();
				bookbuff.setId(rs.getInt(1));
				bookbuff.setName(rs.getString(2));
				bookbuff.setPrice(rs.getInt(3));
				data.add(bookbuff);
		}
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionUtil.close(con, pst, rs);
		}
		return data;
	}
	public ArrayList<Book> viewByName(String name) throws Exception {
		ArrayList<Book> data=new ArrayList<>();
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs= null;
		try {
			pst = con.prepareStatement("Select id,Name,Price from books where Name like ?");
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				Book bookbuff = new Book();
				bookbuff.setId(rs.getInt(1));
				bookbuff.setName(rs.getString(2));
				bookbuff.setPrice(rs.getInt(3));
				data.add(bookbuff);
		}
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionUtil.close(con, pst, rs);
		}
		return data;
	 }
	public ArrayList<Book> viewByPrice(int min,int max){
		ArrayList<Book> data=new ArrayList<>();
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs= null;
		try {
			pst = con.prepareStatement("Select id,Name,Price from books where price > ? AND price < ?");
			pst.setInt(1, min);
			pst.setInt(2,max);
			rs = pst.executeQuery();
			while (rs.next()) {
				Book bookbuff = new Book();
				bookbuff.setId(rs.getInt(1));
				bookbuff.setName(rs.getString(2));
				bookbuff.setPrice(rs.getInt(3));
				data.add(bookbuff);
		}
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ConnectionUtil.close(con, pst, rs);
		}
		return data;
	 }
		
	}

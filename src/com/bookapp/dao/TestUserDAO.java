package com.bookapp.dao;

import java.util.List;

import com.bookapp.model.User;

public class TestUserDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//User user = new User();	
		//user.setName("banu");
//		user.setEmail("jai@gmail.com");
//	    user.setPassword("pas");
//		
		UserDAO dao = new UserDAO();
			dao.updatePassword("jai@gmail.com", "pas", "pass");
			//dao.register(user);
			List<User>users = dao.findAll();
			for(User i : users)
			{
				System.out.println(i);
			}

	}

}

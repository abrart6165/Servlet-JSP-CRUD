package com.c2k.service;

import java.util.List;

import com.c2k.dao.UserDao;
import com.c2k.model.User;

public class UserService {

	public boolean insertUser(User u) {
		UserDao ud = new UserDao();
		if (ud.insertUser(u)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkLogin(User u) {
		
		
		UserDao ud = new UserDao();
		if(ud.checkLogin(u)==1)
		{ return true;}
		else
		{
			return false;
		}
		
		
	
		/*
		 * User u2 = new User(); UserDao ud = new UserDao(); List<User> list =
		 * ud.allUsers(); for (User u1 : list) { if
		 * (u1.getUname().contentEquals(u.getUname()) &&
		 * u1.getPass().contentEquals(u.getPass()) &&
		 * u1.getRole().contentEquals(u.getRole())) { u2 = u1; break; } } return u2;
		 */
	}

	public boolean updateUser(User u) {
		UserDao ud = new UserDao();
		if (ud.updateUser(u)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteUser(User u) {
		UserDao ud = new UserDao();
		if (ud.deleteUser(u)==1) {
			return true;
		} else {
			return false;
		}
	}
}

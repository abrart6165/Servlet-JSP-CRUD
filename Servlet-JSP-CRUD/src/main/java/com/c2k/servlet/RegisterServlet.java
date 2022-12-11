package com.c2k.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.c2k.model.User;
import com.c2k.service.UserService;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		User u = new User();
		u.setFname(req.getParameter("fname"));
		u.setLname(req.getParameter("lname"));
		u.setUname(req.getParameter("username"));
		u.setPass(req.getParameter("password"));
		u.setRole(req.getParameter("role"));

		UserService us = new UserService();
		if (us.insertUser(u)) 
		{
			if(u.getRole().contentEquals("admin")) 
			{			
			out.print("Registration Successful as Admin.");
			}
			else {
				out.print("Registration Successful as User.");}
			req.getRequestDispatcher("login.html").include(req, response);
		} else {
			req.getRequestDispatcher("index.html").include(req, response);
			out.print("Registration Unsuccessfull. User already exists!!!");
		}
		out.close();
	}
}

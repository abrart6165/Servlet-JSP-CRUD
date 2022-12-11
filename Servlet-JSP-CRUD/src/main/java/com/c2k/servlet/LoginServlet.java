package com.c2k.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.c2k.model.User;
import com.c2k.service.UserService;

@WebServlet("/login") 
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		User u = new User();
		u.setUname(request.getParameter("name"));
		u.setPass(request.getParameter("password"));
		u.setRole(request.getParameter("role"));
		
		UserService us = new UserService();
		
		
		if (us.checkLogin(u)) 
		{
			if(u.getRole().contentEquals("admin")) 
			{
			request.getRequestDispatcher("adminlink.html").include(request, response);
			out.print("You are successfully logged in as Admin!");
			out.print("Welcome " + u.getUname());
			
			HttpSession session=request.getSession();  
		    session.setAttribute("name",u.getUname());  

			int i = u.getUid();
			String uid = String.valueOf(i);
			ServletContext context = getServletContext();			
			context.setAttribute("uid", uid); 
			}
			else {
			request.getRequestDispatcher("link.html").include(request, response);	
			out.print("You are successfully logged in as User!");
			out.print("Welcome " + u.getUname());
			
			HttpSession session=request.getSession();  
		    session.setAttribute("name",u.getUname());

			int i = u.getUid();
			String uid = String.valueOf(i);
			ServletContext context = getServletContext();			
			context.setAttribute("uid", uid);    }
		}
		 else {
			out.print("Wrong username or password.");
			request.getRequestDispatcher("login.html").include(request, response);
		}

		out.close();
	}
}
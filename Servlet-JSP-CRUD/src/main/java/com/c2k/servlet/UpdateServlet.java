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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		User u = new User();
		ServletContext context = getServletContext();
		String uid10 = (String) context.getAttribute("uid");
		int i = Integer.parseInt(uid10);
		u.setUid(i);
		u.setFname(req.getParameter("fname"));
		u.setLname(req.getParameter("lname"));
		u.setUname(req.getParameter("username"));
		u.setPass(req.getParameter("password"));
		u.setRole(req.getParameter("role"));
		
		HttpSession session=req.getSession(false);
		UserService us = new UserService();
		
		 if (session==null){
			req.getRequestDispatcher("index.html").include(req, response);
			out.print("User is logged out. Please login again...!!!");
			}
		 else if (us.updateUser(u)) {
			req.getRequestDispatcher("link.html").include(req, response);
			out.print("User updated Successfully...!!!");
		} 		
		else {
			req.getRequestDispatcher("index.html").include(req, response);
			out.print("User not updated Successfully...!!!");
		}
		out.close();
	}
}

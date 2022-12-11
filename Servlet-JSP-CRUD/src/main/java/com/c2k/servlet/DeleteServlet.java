package com.c2k.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.c2k.model.User;
import com.c2k.service.UserService;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		User u = new User();
		/*
		 * ServletContext context = getServletContext(); 
		 * String uid = (String)
		 * context.getAttribute("uid");
		 */
		
		u.setUid(Integer.parseInt(req.getParameter("uid")));
		u.setRole(req.getParameter("role"));
		UserService us = new UserService();
		if (us.deleteUser(u)) {
			req.getRequestDispatcher("adminlink.html").include(req, response);
			out.print("User deleted successfully");
		} else {
			req.getRequestDispatcher("adminlink.html").include(req, response);
			out.print("User not deleted successfully.. Role is admin");
		}
		out.close();
	}
}
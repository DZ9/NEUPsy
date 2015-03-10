package com.idoxie.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idoxie.model.Teacher;

public class EditTeacher extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EditTeacher() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nickname = 
				request.getParameter("nickname");
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		String description = new String(request.getParameter("description").getBytes("ISO-8859-1"),"utf-8");
		String authority = new String(request.getParameter("authority").getBytes("ISO-8859-1"),"utf-8");
		String email = request.getParameter("email");
		System.out.println(nickname);
		System.out.println(name);
		System.out.println(description);
		System.out.println(authority);
		System.out.println(email);
		
		Teacher teacher = new Teacher();
		teacher.setName(name);
		teacher.setNickname(nickname);
		teacher.setDescription(description);
		teacher.setEmail(email);
		
		request.setAttribute("teacher", teacher);
		RequestDispatcher view =  
				request.getRequestDispatcher("/view/admin/editTeacher.jsp");
		view.forward(request, response);
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

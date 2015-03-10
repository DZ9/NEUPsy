package com.idoxie.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.idoxie.dao.ITeacherDAO;
import com.idoxie.dao.impl.TeacherDAO;
import com.idoxie.model.Appointment;
import com.idoxie.model.Teacher;

public class AdminLogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminLogin() {
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

		doPost(request,response);
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
		String nickname = new String( request.getParameter("nickname").getBytes("ISO-8859-1"),"utf-8");
		String password = new String( request.getParameter("password").getBytes("ISO-8859-1"),"utf-8");
		ITeacherDAO teacherDAO = new TeacherDAO();
		
		if(teacherDAO.checkLogin(nickname, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("nickname", nickname);
			session.setMaxInactiveInterval(6000);
			RequestDispatcher view =  
					request.getRequestDispatcher("/servlet/ShowAdminIndex");
					view.forward(request, response);
			
		}else {
			RequestDispatcher view =  
					request.getRequestDispatcher("/view/user/loginError.jsp");
					view.forward(request, response);
		}
		
		
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

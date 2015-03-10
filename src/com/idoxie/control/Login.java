package com.idoxie.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.idoxie.dao.IUserDAO;
import com.idoxie.dao.impl.UserDAO;

public class Login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Login() {
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
		if(request.getParameter("stuNum")==null || request.getParameter("password") == null) {
			RequestDispatcher view =  
					request.getRequestDispatcher("/view/user/loginEmpty.jsp");
					view.forward(request, response);
		}
		String stuNum = new String( request.getParameter("stuNum").getBytes("ISO-8859-1"),"utf-8");
		String password = new String( request.getParameter("password").getBytes("ISO-8859-1"),"utf-8");
		
		
		IUserDAO userDAO = new UserDAO();
		if(userDAO.checkLogin(Integer.parseInt(stuNum), password)) {
			HttpSession session = request.getSession();
			session.setAttribute("stuNum", stuNum);
			session.setMaxInactiveInterval(6000);
			RequestDispatcher view =  
					request.getRequestDispatcher("/servlet/ShowUserIndex");
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

package com.idoxie.control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.idoxie.dao.IUserDAO;
import com.idoxie.dao.impl.UserDAO;

public class UserRegister extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserRegister() {
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
		String stuNum = new String(request.getParameter("stuNum").getBytes("ISO-8859-1"),"utf-8");
		String stuName = new String(request.getParameter("stuName").getBytes("ISO-8859-1"),"utf-8");
		String college = new String(request.getParameter("college").getBytes("ISO-8859-1"),"utf-8");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"),"utf-8");
		String sex = new String(request.getParameter("sex").getBytes("ISO-8859-1"),"utf-8");
		String birthday = new String(request.getParameter("birthday").getBytes("ISO-8859-1"),"utf-8");
		String interest = new String(request.getParameter("interest").getBytes("ISO-8859-1"),"utf-8");
		String nation = new String(request.getParameter("nation").getBytes("ISO-8859-1"),"utf-8");
		String hometown = new String(request.getParameter("hometown").getBytes("ISO-8859-1"),"utf-8");
		String grade = new String(request.getParameter("grade").getBytes("ISO-8859-1"),"utf-8");
		String major = new String(request.getParameter("major").getBytes("ISO-8859-1"),"utf-8");
		IUserDAO userDAO = new UserDAO();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sdf.parse(birthday);
			if(userDAO.checkExistance(Integer.parseInt(stuNum))) {
				RequestDispatcher view =  
						request.getRequestDispatcher("/view/user/rerror.jsp");
				view.forward(request, response);
			}else if(!userDAO.validateUser(stuName, Integer.parseInt(stuNum))) {
				RequestDispatcher view =  
						request.getRequestDispatcher("/view/user/validateError.jsp");
				view.forward(request, response);
			}else {
			
				userDAO.addUser(Integer.parseInt(stuNum), stuName, password, college, d, nation, interest, hometown,grade,sex,major);
				HttpSession session = request.getSession();
				session.setAttribute("stuNum", stuNum);
				session.setMaxInactiveInterval(6000);
				RequestDispatcher view =  
						request.getRequestDispatcher("/view/user/success.jsp");
				view.forward(request, response);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

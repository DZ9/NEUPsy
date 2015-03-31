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

import com.idoxie.dao.ITeacherDAO;
import com.idoxie.dao.impl.TeacherDAO;
import com.idoxie.dao.impl.UserDAO;

public class UpdateReserve extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateReserve() {
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
		
		HttpSession session = request.getSession();
		String nickname = (String) session.getAttribute("nickname");
		
		if(nickname == null) {
			RequestDispatcher view =  
					request.getRequestDispatcher("/view/admin/adminLogin.jsp");
			view.forward(request, response);
		}else {
			String stuNum = new String(request.getParameter("stuNum").getBytes("ISO-8859-1"),"utf-8");
			String rDateString = new String(request.getParameter("rDate").getBytes("ISO-8859-1"),"utf-8");
			String aTimeString = new String(request.getParameter("aTime").getBytes("ISO-8859-1"),"utf-8");
			String aTeacher = new String(request.getParameter("aTeacher").getBytes("ISO-8859-1"),"utf-8");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			try {
				Date rDate = sdf1.parse(rDateString);
				Date aTime = sdf2.parse(aTimeString);
				ITeacherDAO teacherDAO = new TeacherDAO();

				teacherDAO.updateReserveByAdmin(rDate, Integer.parseInt(stuNum), aTime, aTeacher);
				String email = teacherDAO.getEmailByTeacherName(aTeacher);
				UserDAO userDAO = new UserDAO();
				userDAO.sendMail(Integer.parseInt(stuNum), email);
				
				RequestDispatcher view =  
						request.getRequestDispatcher("/view/admin/success.jsp");
				view.forward(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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

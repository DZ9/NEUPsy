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

public class AddAppointment extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddAppointment() {
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
			String stuNum = (String) session.getAttribute("stuNum");
			UserDAO userDAO = new UserDAO();
			ITeacherDAO teacherDAO = new TeacherDAO();
			if(stuNum == null) {
				RequestDispatcher view =  
						request.getRequestDispatcher("/servlet/ShowUserIndex");
				view.forward(request, response);
			}else if( teacherDAO.getAppointment(new Date(),Integer.parseInt(stuNum))!= null) {
				RequestDispatcher view =  
						request.getRequestDispatcher("/view/user/addError.jsp");
				view.forward(request, response);
			}
			else {
				
				String date = request.getParameter("date");
				String teacher = new String(request.getParameter("teacher").getBytes("ISO-8859-1"),"utf-8");
				String aspect = request.getParameter("aspect");
				System.out.println(teacher);
				String phone = request.getParameter("telephone");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date d;
				try {
					d = sdf.parse(date);
					
					userDAO.addAppointment(Integer.parseInt(stuNum), phone, teacher, d, Integer.parseInt(aspect), new Date());
					RequestDispatcher view =  
							request.getRequestDispatcher("/view/user/success.jsp");
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

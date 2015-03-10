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

public class AddAppointmentByAdmin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddAppointmentByAdmin() {
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
			String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
			String phone = request.getParameter("phone");
			String college = new String(request.getParameter("college").getBytes("ISO-8859-1"),"utf-8");
					
			String aspect = request.getParameter("aspect");
			String eTime = request.getParameter("eTime");
			String eTeacher = new String(request.getParameter("eTeacher").getBytes("ISO-8859-1"),"utf-8");
			String aTime = request.getParameter("aTime");
			String aTeacher = new String(request.getParameter("aTeacher").getBytes("ISO-8859-1"),"utf-8");
			String way = request.getParameter("way");
			
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date eTimeDate,aTimeDate;
			
			try {
				eTimeDate = sdf.parse(eTime);
				aTimeDate = sdf.parse(aTime);
				ITeacherDAO teacherDAO = new TeacherDAO();
				teacherDAO.addAppointment(Integer.parseInt(stuNum), phone, college, Integer.parseInt(aspect), 
						eTimeDate, eTeacher, aTimeDate, aTeacher, new Date(),Integer.parseInt(way),name);
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

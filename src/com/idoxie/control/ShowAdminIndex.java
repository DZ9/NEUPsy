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
import com.idoxie.model.Music;
import com.idoxie.model.Teacher;

public class ShowAdminIndex extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowAdminIndex() {
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
			ITeacherDAO teacherDAO = new TeacherDAO();
			ArrayList<Appointment> appointmenets = teacherDAO.listAllAppointment();
			ArrayList<Teacher> teachers = teacherDAO.listAllTeacher();
			ArrayList<Appointment> appointmenetsByTeacher = teacherDAO.listAppointmentByTeacherName(nickname);
			ArrayList<Music> musics = teacherDAO.listMusic();
			
			int authority = teacherDAO.checkAuthority(nickname);
			
			request.setAttribute("appointments", appointmenets);
			request.setAttribute("teachers", teachers);
			request.setAttribute("musics", musics);
			
			switch(authority)  {
			case 1:
				RequestDispatcher view1 =  
				request.getRequestDispatcher("/view/admin/admin.jsp");
				view1.forward(request, response);
				break;
			case 2:
				RequestDispatcher view2 =  
				request.getRequestDispatcher("/view/admin/assistant.jsp");
				view2.forward(request, response);
				break;
			case 3:
				request.setAttribute("appointmenetsByTeacher", appointmenetsByTeacher);
				RequestDispatcher view3 =  
				request.getRequestDispatcher("/view/admin/teacher.jsp");
				view3.forward(request, response);
				break;
			default:
				RequestDispatcher view4 =  
				request.getRequestDispatcher("/view/admin/teacher.jsp");
				view4.forward(request, response);	
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

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
import com.idoxie.dao.IUserDAO;
import com.idoxie.dao.impl.TeacherDAO;
import com.idoxie.dao.impl.UserDAO;
import com.idoxie.model.Appointment;
import com.idoxie.model.Music;
import com.idoxie.model.Teacher;

public class ShowUserIndex extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowUserIndex() {
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
		
		if(stuNum == null) {
			RequestDispatcher view =  
					request.getRequestDispatcher("/");
			view.forward(request, response);
		}else {
			IUserDAO userDAO = new UserDAO();
			ITeacherDAO teacherDAO = new TeacherDAO();
			ArrayList<Appointment> appointment =
					userDAO.listAppointMentById(Integer.parseInt(stuNum));
			ArrayList<Teacher> teacher = 
					teacherDAO.listAllTeacher();
			ArrayList<Music> musics = userDAO.listMusicById(Integer.parseInt(stuNum));
			
			request.setAttribute("appointment", appointment);
			request.setAttribute("teacher", teacher);
			request.setAttribute("musics", musics);
			RequestDispatcher view =  
					request.getRequestDispatcher("/view/user/index.jsp");
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

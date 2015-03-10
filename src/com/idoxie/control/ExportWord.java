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
import com.idoxie.dao.IUserDAO;
import com.idoxie.dao.impl.TeacherDAO;
import com.idoxie.dao.impl.UserDAO;
import com.idoxie.model.Appointment;

public class ExportWord extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ExportWord() {
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
			String stuNum =  new String(request.getParameter("stuNum").getBytes("ISO-8859-1"),"utf-8");
			String rDate = request.getParameter("rDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(rDate);
				ITeacherDAO teacherDAO = new TeacherDAO();
				Appointment appointment = teacherDAO.getAppointment(d, Integer.parseInt(stuNum));
//	System.out.println(appointment.getStuNum());
//	System.out.println(appointment.getStuName());
//	System.out.println(appointment.getCollege());
//	System.out.println(appointment.getStuPhone());
//	System.out.println(appointment.geteTime());
//	System.out.println(appointment.getWay());
//	System.out.println(appointment.getAspect());
//	System.out.println(appointment.geteTeacher());
//	System.out.println(appointment.getaTime());
//	System.out.println(appointment.getReceiver());
//	System.out.println(appointment.getRemark());
//	System.out.println(appointment.getResult());
//	System.out.println(appointment.getaTeacher());
//	System.out.println(rDate);			
				IUserDAO  userDAO = new UserDAO();
				
				teacherDAO.generateWordDoc(appointment.getStuName(), appointment.getStuNum()+"", appointment.getCollege(),
						appointment.getStuPhone(), appointment.geteTime(), appointment.getWay(), appointment.getAspect(), appointment.geteTeacher(), 
						appointment.getaTime(), appointment.getReceiver(), appointment.getRemark(), appointment.getResult(), appointment.getaTeacher(), rDate);
				RequestDispatcher view =  
						request.getRequestDispatcher("../"+stuNum+" "+rDate+".doc");
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

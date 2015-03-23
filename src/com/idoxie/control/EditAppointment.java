package com.idoxie.control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

public class EditAppointment extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EditAppointment() {
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
			
		String stuNum = new String(request.getParameter("stuNum"));
		String stuName = new String(request.getParameter("stuName").getBytes("ISO-8859-1"),"utf-8");
		String phone = request.getParameter("phone");
		String college = new String(request.getParameter("college").getBytes("ISO-8859-1"),"utf-8");
		String aspect = new String(request.getParameter("aspect").getBytes("ISO-8859-1"),"utf-8");
		String eTime = new String(request.getParameter("eTime").getBytes("ISO-8859-1"),"utf-8");
		String eTeacher = new String(request.getParameter("eTeacher").getBytes("ISO-8859-1"),"utf-8");
		String aTime = new String(request.getParameter("aTime").getBytes("ISO-8859-1"),"utf-8");
		String aTeacher = new String(request.getParameter("aTeacher").getBytes("ISO-8859-1"),"utf-8");
		String rDateString = new String(request.getParameter("rDate").getBytes("ISO-8859-1"),"utf-8");
System.out.println(eTime);
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//Date date = sdf.parse("2008-08-08 12:10:12");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String eTimeFormat = eTime.substring(0,16);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try {
			Date eDate = sdf.parse(eTimeFormat);
			Date rDate = sdf2.parse(rDateString);
			Appointment appointment = new Appointment();
			appointment.setStuNum(Integer.parseInt(stuNum));
			appointment.setStuName(stuName);
			appointment.setStuPhone(phone);
			appointment.setCollege(college);
			appointment.setAspect(aspect);
			appointment.seteTime(eDate);
			appointment.seteTeacher(eTeacher);
			appointment.setrDate(rDate);
			if(!aTime.equals("null")) {
				String aTimeFormat = aTime.substring(0,16);
				appointment.setaTime(sdf.parse(aTimeFormat));
				
			}
			appointment.setaTeacher(aTeacher);
			TeacherDAO teacherDAO = new TeacherDAO();
			ArrayList<Teacher> teachers = teacherDAO.listAllCouncilor();
			
			
			request.setAttribute("teachers", teachers);
			request.setAttribute("appointment", appointment);
			RequestDispatcher view =  
			request.getRequestDispatcher("/view/admin/editReserve.jsp");
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

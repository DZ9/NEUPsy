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
import com.idoxie.dao.impl.TeacherDAO;
import com.idoxie.dao.impl.UserDAO;
import com.idoxie.model.Appointment;
import com.idoxie.model.Student;
import com.idoxie.util.word.AdvisoryDocumentHandler;

public class ExportAdvisory extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ExportAdvisory() {
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
			TeacherDAO teacherDAO = new TeacherDAO();
			
			String phone =  new String(request.getParameter("phone").getBytes("ISO-8859-1"),"utf-8");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			IUserDAO  userDAO = new UserDAO();
			Student stu = userDAO.getStudentByStuNum(Integer.parseInt(stuNum));
			Date d = stu.getBirthday();
			String birthday;
			if(d != null) {
				 birthday = sdf.format(d);
			}
			else {
				 birthday = "";
			}
			Date date;
			try {
				date = sdf.parse(rDate);
				Appointment app =  teacherDAO.getAppointment(date, Integer.parseInt(stuNum));
				
				System.out.println(app.getSuggestion());
				AdvisoryDocumentHandler adh = new AdvisoryDocumentHandler();
				adh.createDoc(stu.getName(), stu.getSex(), birthday, stu.getInterest(), phone, stu.getCollege(), 
						stu.getGrade(), stuNum, stu.getNation(), stu.getHometown(),app.getContent(),app.getSuggestion(),app.getAspect(),stu.getMajor());
				RequestDispatcher view =  
						request.getRequestDispatcher("../advisory.doc");
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

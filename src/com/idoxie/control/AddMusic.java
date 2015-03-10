package com.idoxie.control;

import java.io.IOException;
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

public class AddMusic extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddMusic() {
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
					request.getRequestDispatcher("/view/user/login.jsp");
			view.forward(request, response);
		}else {
			String phone = new String(request.getParameter("phone").getBytes("ISO-8859-1"),"utf-8");
			String symptom = new String(request.getParameter("symptom").getBytes("ISO-8859-1"),"utf-8");
			String eDate = new String(request.getParameter("eDate").getBytes("ISO-8859-1"),"utf-8");
			String eTime = new String(request.getParameter("eTime").getBytes("ISO-8859-1"),"utf-8");
			String other = new String(request.getParameter("other").getBytes("ISO-8859-1"),"utf-8");
			IUserDAO userDAO = new UserDAO();
			
			userDAO.addMusic(Integer.parseInt(stuNum), symptom, eTime, eDate, new Date(), phone);
			userDAO.sendMusicMail(Integer.parseInt(stuNum));
			RequestDispatcher view =  
					request.getRequestDispatcher("/view/user/success.jsp");
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

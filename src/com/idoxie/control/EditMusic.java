package com.idoxie.control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.idoxie.model.Music;

public class EditMusic extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EditMusic() {
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
			String eDate = new String(request.getParameter("eDate").getBytes("ISO-8859-1"),"utf-8");
			String phone = new String(request.getParameter("phone").getBytes("ISO-8859-1"),"utf-8");
			String eTime = new String(request.getParameter("eTime").getBytes("ISO-8859-1"),"utf-8");
			String symptom = new String(request.getParameter("symptom").getBytes("ISO-8859-1"),"utf-8");
			String rDate = new String(request.getParameter("rDate").getBytes("ISO-8859-1"),"utf-8");
			
			Music music = new Music();
			music.setStuNum(Integer.parseInt(stuNum));
			music.seteDate(eDate);
			music.setPhone(phone);
			music.seteTime(eTime);
			music.setSymptom(symptom);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				music.setrDate(sdf1.parse(rDate));
				request.setAttribute("music", music);
				RequestDispatcher view =  
						request.getRequestDispatcher("/view/admin/editMusic.jsp");
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

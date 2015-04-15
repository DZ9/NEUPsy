package com.idoxie.dao;

import java.util.ArrayList;
import java.util.Date;

import com.idoxie.model.Appointment;
import com.idoxie.model.Music;
import com.idoxie.model.Student;

public interface IUserDAO {
	boolean checkLogin(int stuNum, String password);
	
	boolean addUser(int stuNum, String name, String password,
			String college, Date birthday, String nation, String interest, String hometown,String grade,String sex,String major);
	
	boolean addAppointment(int stuNum, String phone, String eTeacher, Date eTime, int aspect
			,Date rDate);
	
	boolean sendMail(int stuNum,String Teachermail);
	boolean sendMusicMail(int stuNum);
	
	/*
	 *信息真实性判断 
	 */
	boolean validateUser(String name,int stuNum);
	
	/*
	 * 生成预约文档
	 */
//	boolean exportDocument();
	ArrayList<Appointment> listAppointMentById(int stuNum);
	
	  String getAspectById(int id);
	  String getTeacherById(int id);
	  String getWayById(int id);
	  String getAuthorityById(int id);
	  String getNameByStuNum(int id);
	  Student getStudentByStuNum(int stuNum);
	
	boolean checkExistance(int stuNum);
	boolean changePassword(int stuNum,String password);
	boolean addMusic(int stuNum,String symptom,String eTime,String eDate,Date rDate,String phone);
	Music getMusic(int stuNum,Date rDate);
	ArrayList<Music> listMusicById(int stuNum);
	
}

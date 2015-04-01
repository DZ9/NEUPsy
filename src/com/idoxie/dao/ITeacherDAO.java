package com.idoxie.dao;

import java.util.ArrayList;
import java.util.Date;

import com.idoxie.model.Appointment;
import com.idoxie.model.Music;
import com.idoxie.model.Teacher;

public interface ITeacherDAO {
	public boolean checkLogin(String nickname, String password);
	public int checkAuthority(String nickname); 
	public boolean generateWordDoc(String name, String stuNum, String college,
			 String telephone, Date reserveTime, String way, String aspect, String reTeacher,Date aTime,
			 String receiver, String remark, String result, String aTeacher,String rDate);
	public boolean generateExcelDocByDate(Date beginDate, Date endDate);
	public boolean addTeacher(String name, String password, int authority,String description
			,String nickname,String email);
	public boolean modifyTeacherInfo(String nickname, int authority, String description, String name,String email);
	public ArrayList<Appointment> listAllAppointment();
	public ArrayList<Appointment> listAppointmentByTeacherName(String teacherName);
	public ArrayList<Teacher> listAllTeacher();
	public String getName(String nickname);
	public Teacher getTeacher(String nickname);
	public int getTeacherIDByTeacherName(String nickname);
	public boolean addAppointment(int stuNum, String phone, String college,int aspect,
			Date eTime,String eTeacher, Date aTime, String aTeacher, Date rDate,int way
			,String stuName);
	public Appointment getAppointment(Date rDate, int stuNum);
	public boolean updateReserveByAdmin(Date rDate,int stuNum,Date aTime,String aTeacher,String content,String suggestion);
	public boolean updateReserveByTeacher(Date rDate,int stuNum,String receiver,String remark,String result);
	public String getEmailByTeacherName(String name);
	public ArrayList<Music> listMusic();
}

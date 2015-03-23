package com.idoxie.dao.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.idoxie.dao.ITeacherDAO;
import com.idoxie.dao.IUserDAO;
import com.idoxie.model.Appointment;
import com.idoxie.model.Music;
import com.idoxie.model.Student;
import com.idoxie.model.Teacher;
import com.idoxie.util.DB;
import com.idoxie.util.word.DocumentHandler;

public class TeacherDAO implements ITeacherDAO{

	@Override
	public boolean checkLogin(String nickname, String password) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select password from t_teacher where nickname = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			
			ps.setString(1, nickname);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("password").equals(password)) {
					DB.close(ps);
					DB.close(conn);
					return true;
				}
				DB.close(ps);
				DB.close(conn);
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return false;
	}

	@Override
	public int checkAuthority(String nickname) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select authority from t_teacher where nickname = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setString(1, nickname);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("authority");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return 0;
	}

	@Override
	public boolean generateWordDoc(String name, String stuNum, String college,
			 String telephone, Date reserveTime, String way, String aspect, String reTeacher,Date aTime,
			 String receiver, String remark, String result, String aTeacher,String rDate) {
		// TODO Auto-generated method stub
		DocumentHandler doc = new DocumentHandler();
		Appointment appointment = new Appointment();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String reserveTimeString=sdf.format(reserveTime);  
		String aTimeString = sdf.format(aTime);
		doc.createDoc(name, stuNum, college,
				 telephone, reserveTimeString, way, aspect, reTeacher, aTimeString,
				 receiver, remark, result, aTeacher,rDate);
		
		return true;
	}

	@Override
	public boolean generateExcelDocByDate(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select * from t_appointment where aTime between ? and ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setDate(1, new java.sql.Date(beginDate.getTime()));
			ps.setDate(2, new java.sql.Date(endDate.getTime()));
			WritableWorkbook book = Workbook.createWorkbook(new File("C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/NEUPsy/"+"a.xls"));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("第一页", 0);
            Label label00 = new Label(0,0,"学号");
			Label label10 = new Label(1,0,"姓名");
			Label label20 = new Label(2,0,"联系电话");
			Label label30 = new Label(3,0,"预约咨询师");
			Label label40 = new Label(4,0,"咨询师");
			Label label50 = new Label(5,0,"接待人");
			Label label60 = new Label(6,0,"咨询时间");
			Label label70 = new Label(7,0,"咨询方向");
			
			sheet.addCell(label00);
			 sheet.addCell(label10);
			 sheet.addCell(label20);
			 sheet.addCell(label30);
			 sheet.addCell(label40);
			 sheet.addCell(label50);
			 sheet.addCell(label60);
			 sheet.addCell(label70);
			
			ResultSet rs = ps.executeQuery();
			int i = 1;
			while(rs.next()) {
				 Label label01 = new Label(0,i,rs.getInt("stuNum")+"");
				 UserDAO userDAO = new UserDAO();
				 
				 Student student = userDAO.getStudentByStuNum(rs.getInt("stuNum"));
//System.out.println(student.getName());
				 Label label11 = new Label(1,i,student.getName());
				 Label label21 = new Label(2,i,rs.getString("stuPhone"));
				 Label label31 = new Label(3,i,rs.getString("eTeacher"));
				 Label label41 = new Label(4,i,rs.getString("aTeacher"));
				 Label label51 = new Label(5,i,rs.getString("receiver"));
				 
				 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
//				 System.out.println(rs.getDate("aTime"));
				 if(rs.getDate("aTime") != null) {
					 String aDate = sdf.format(rs.getDate("aTime"));
					 Label label61 = new Label(6,i,aDate);
					 sheet.addCell(label61);
				 }
				 
				 Label label71 = new Label(7,i,userDAO.getAspectById(rs.getInt("aspect")));
				 
				 sheet.addCell(label01);
				 sheet.addCell(label11);
				 sheet.addCell(label21);
				 sheet.addCell(label31);
				 sheet.addCell(label41);
				 sheet.addCell(label51);
				 sheet.addCell(label71);
				 i++;
			}
			 
			 book.write();
			 book.close();
			
           
		} catch (SQLException | WriteException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return true;
	}

	@Override
	public boolean addTeacher(String name, String password, int authority,
			String description,String nickname,String email) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "insert into t_teacher(name,password,authority,description,nickname,email) " +
				"values(?, ?, ?, ?, ?,?)";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setInt(3,authority);
			ps.setString(4, description);
			ps.setString(5, nickname);
			ps.setString(6, email);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return true;
	}

	@Override
	public boolean modifyTeacherInfo(String nickname, int authority,
			String description, String name,String email) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "update t_teacher set authority = ?, description = ?, name = ?, email = ? where nickname = ?";
		PreparedStatement ps = DB.prepare(conn, sql);

		try {
			ps.setInt(1, authority);
			ps.setString(2, description);
			ps.setString(3, name);
			ps.setString(4, email);
			ps.setString(5, nickname);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return true;
	}

	@Override
	public ArrayList<Appointment> listAllAppointment() {
		// TODO Auto-generated method stub
		ArrayList<Appointment> al = new ArrayList<Appointment>();
		Connection conn = DB.createConn();
		String sql = "select * from t_appointment";
		PreparedStatement ps = DB.prepare(conn, sql);
		UserDAO userDAO = new UserDAO();
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Appointment appointment = new Appointment();
				appointment.seteTeacher(rs.getString("eTeacher"));
				appointment.setAspect(userDAO.getAspectById(rs.getInt("aspect")));
				appointment.seteTime(rs.getTimestamp("eTime"));
				appointment.setrDate(rs.getDate("rDate"));
				appointment.setStuNum(rs.getInt("stuNum"));
				appointment.setStuPhone(rs.getString("stuPhone"));
				appointment.setWay(userDAO.getWayById(rs.getInt("way")));
				appointment.setChecked(rs.getBoolean("checked"));
				appointment.setrDate(rs.getDate("rDate"));
				appointment.setStuName(rs.getString("stuName"));
				appointment.setCollege(rs.getString("college"));
				if(appointment.isChecked() == true) {
					appointment.setaTeacher(rs.getString("aTeacher"));
					appointment.setaTime(rs.getTimestamp("aTime"));

					appointment.setReceiver(rs.getString("receiver"));
					appointment.setRemark(rs.getString("remark"));
					appointment.setResult(rs.getString("result"));
				}
				
				al.add(appointment);
				
			}
//			System.out.println(al.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		DB.close(ps);
		DB.close(conn);
		return al;
	}

	@Override
	public ArrayList<Appointment> listAppointmentByTeacherName(String nickname) {
		// TODO Auto-generated method stub
		ArrayList<Appointment> al = new ArrayList<Appointment>();
		Connection conn = DB.createConn();
		UserDAO userDAO = new UserDAO();
		Teacher teacher = getTeacher(nickname);
		String sql = "select * from t_appointment where aTeacher = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		int teacherID = getTeacherIDByTeacherName(nickname);
		try {
			ps.setString(1, nickname);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Appointment appointment = new Appointment();
				appointment.seteTeacher(userDAO.getTeacherById(teacherID));
				appointment.setAspect(userDAO.getAspectById(rs.getInt("aspect")));
				appointment.seteTime(rs.getTimestamp("eTime"));
				appointment.setrDate(rs.getDate("rDate"));
				appointment.setStuNum(rs.getInt("stuNum"));
				appointment.setStuPhone(rs.getString("stuPhone"));
				appointment.setWay(userDAO.getWayById(rs.getInt("way")));
				appointment.setChecked(rs.getBoolean("checked"));
				appointment.setrDate(rs.getDate("rDate"));
				appointment.setStuName(rs.getString("stuName"));
				appointment.setCollege(rs.getString("college"));
				if(appointment.isChecked() == true) {
					appointment.setaTeacher(rs.getString("aTeacher"));
					appointment.setaTime(rs.getTimestamp("aTime"));
					appointment.setReceiver(rs.getString("receiver"));
					appointment.setRemark(rs.getString("remark"));
					appointment.setResult(rs.getString("result"));
				}
				
				al.add(appointment);
				
			}
//			System.out.println(al.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		DB.close(ps);
		DB.close(conn);
		return al;
	}

	@Override
	public ArrayList<Teacher> listAllTeacher() {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select * from t_teacher";
		PreparedStatement ps = DB.prepare(conn, sql);
		ArrayList<Teacher> al = new ArrayList();
		UserDAO userDAO = new UserDAO();
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setAuthority(userDAO.getAuthorityById(rs.getInt("authority")));
				teacher.setName(rs.getString("name"));
				teacher.setPassword(rs.getString("password"));
				teacher.setDescription(rs.getString("description"));
				teacher.setNickname(rs.getString("nickname"));
				teacher.setEmail(rs.getString("email"));
				al.add(teacher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return al;
	}

	

	@Override
	public String getName(String nickname) {
		// TODO Auto-generated method stub\
		Connection conn = DB.createConn();
		String sql = "select name from t_teacher where nickname = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setString(1,nickname);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				DB.close(ps);
			    DB.close(conn);
				return rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		DB.close(ps);
		DB.close(conn);
		return null;
	}

	@Override
	public Teacher getTeacher(String nickname) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select * from t_teacher where nickname = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		UserDAO userDAO = new UserDAO();
		Teacher teacher = new Teacher();
		try {
			ps.setString(1, nickname);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				teacher.setAuthority(userDAO.getAuthorityById(rs.getInt("authority")));
				teacher.setDescription(rs.getString("description"));
				teacher.setName(rs.getString("name"));
				teacher.setNickname(rs.getString("nickname"));
				teacher.setPassword(rs.getString("password"));
				teacher.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return teacher;
	}

	@Override
	public boolean addAppointment(int stuNum, String phone, String college,int aspect,
			Date eTime,String eTeacher, Date aTime, String aTeacher, Date rDate,int way,String stuName) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "insert into t_appointment(stuNum,stuPhone,eTeacher,eTime," +
				"aspect,aTeacher,aTime,rDate,way,checked, stuName,college) " +
				"values(?, ?, ?, ?, ?, ?, ?, ?, ?, true, ?,?)";
				
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1, stuNum);
			ps.setString(2, phone);
			ps.setString(3, eTeacher);
			ps.setTimestamp(4, new java.sql.Timestamp(eTime.getTime()));
			ps.setInt(5, aspect);
			ps.setString(6, aTeacher);
			ps.setTimestamp(7, new java.sql.Timestamp(aTime.getTime()));
			ps.setDate(8, new java.sql.Date(rDate.getTime()));
			ps.setInt(9, way);
			ps.setString(10, stuName);
			ps.setString(11, college);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return true;
	}

	@Override
	public int getTeacherIDByTeacherName(String nickname) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select id from t_teacher where nickname = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setString(1, nickname);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				DB.close(ps);
				DB.close(conn);
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return 0;
	}

	@Override
	public Appointment getAppointment(Date rDate, int stuNum) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select * from t_appointment where stuNum = ? and rDate = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		Appointment appointment = new Appointment();
		
		try {
			ps.setInt(1, stuNum);
			ps.setDate(2, new java.sql.Date(rDate.getTime()));
			ResultSet rs = ps.executeQuery();
			IUserDAO userDAO = new UserDAO();
			while(rs.next()) {
				appointment.setAspect(userDAO.getAspectById(rs.getInt("aspect")));	
				appointment.setaTeacher(rs.getString("aTeacher"));
				appointment.setaTime(rs.getTimestamp("aTime"));
				appointment.setChecked(rs.getBoolean("checked"));
				appointment.setCollege(rs.getString("college"));
				appointment.seteTeacher(rs.getString("eTeacher"));
				appointment.seteTime(rs.getTimestamp("eTime"));
				appointment.setrDate(rs.getDate("rDate"));
				appointment.setReceiver(rs.getString("receiver"));
				appointment.setRemark(rs.getString("remark"));
				appointment.setResult(rs.getString("result"));
				appointment.setStuName(rs.getString("stuName"));
				appointment.setStuNum(rs.getInt("stuNum"));
				appointment.setStuPhone(rs.getString("stuPhone"));
				System.out.println(userDAO.getWayById(rs.getInt("way")));
				appointment.setWay(userDAO.getWayById(rs.getInt("way")));
				
				DB.close(ps);
				DB.close(conn);
				return appointment;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return null;
	}

	@Override
	public boolean updateReserveByAdmin(Date rDate, int stuNum, Date aTime,
			String aTeacher) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "update t_appointment set aTime = ?, " +
				"aTeacher = ?, checked = true where stuNum = ? and rDate = ?";
		
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setTimestamp(1, new java.sql.Timestamp(aTime.getTime()));
			ps.setString(2, aTeacher);
			ps.setInt(3, stuNum);
			ps.setDate(4, new java.sql.Date(rDate.getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return true;
	}

	@Override
	public boolean updateReserveByTeacher(Date rDate, int stuNum,
			String receiver, String remark, String result) {
		Connection conn = DB.createConn();
		String sql = "";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		
		return false;
	}

	@Override
	public String getEmailByTeacherName(String name) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select email from t_teacher where name = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String email = rs.getString("email");
				DB.close(ps);
				DB.close(conn);
				return email;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Music> listMusic() {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select * from t_music";
		PreparedStatement ps = DB.prepare(conn, sql);
		ArrayList<Music> musics = new ArrayList<Music>();
		
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Music music = new Music();
				music.seteDate(rs.getString("eDate"));
				music.seteTime(rs.getString("eTime"));
				music.setPhone(rs.getString("phone"));
				music.setrDate(rs.getDate("rDate"));
				music.setSymptom(rs.getString("symptom"));
				music.setStuNum(rs.getInt("stuNum"));
				
				musics.add(music);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return musics;
	}
	
	public void updateMusic(int stuNum,Date rDate,String type,String advice) {
		Connection conn = DB.createConn();
		String sql = "update t_music set type = ?, " +
				"advice = ? where stuNum = ? and rDate = ?";
		
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setString(1,type);
			ps.setString(2, advice);
			ps.setInt(3, stuNum);
			ps.setDate(4, new java.sql.Date(rDate.getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
	}
	
	public void deleteTeacher(String username) {
		Connection conn = DB.createConn();
		String sql = "delete from t_teacher where nickname = ?";
		
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setString(1, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
	}

}

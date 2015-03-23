package com.idoxie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.idoxie.dao.IUserDAO;
import com.idoxie.model.Appointment;
import com.idoxie.model.Music;
import com.idoxie.model.Student;
import com.idoxie.util.DB;
import com.idoxie.util.mail.SendMailDemo;

public class UserDAO implements IUserDAO{

	@Override
	public boolean checkLogin(int stuNum, String password) {
		Connection conn = DB.createConn();
		String sql = "select password from t_student where stuNum = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1, stuNum);
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
	public boolean addUser(int stuNum, String name, String password,
			String college, Date birthday, String nation, String interest, String hometown,int grade,String sex) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "insert into t_student values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1, stuNum);
			ps.setString(2, name);
			ps.setString(3, password);
			ps.setString(4, college);
			ps.setDate(5, new java.sql.Date(birthday.getTime()));
			ps.setString(6, interest);
			ps.setString(7, nation);
			ps.setString(8, hometown);
			ps.setInt(9, grade);
			ps.setString(10, sex);
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
	public boolean addAppointment(int stuNum,String phone, String eTeacher, Date eTime,
			int aspect, Date rDate) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "insert into t_appointment(stuNum,stuPhone,eTeacher,eTime,aspect,rDate," +
				"checked,stuName,college,way) values(?, ?, ?, ?, ?, ?, false,?,?,2)";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		
		
		try {
			ps.setInt(1, stuNum);
			ps.setString(2, phone);
			ps.setString(3, eTeacher);
			ps.setTimestamp(4, new java.sql.Timestamp(eTime.getTime()));
			ps.setInt(5, aspect);
			ps.setTimestamp(6, new java.sql.Timestamp(rDate.getTime()));
			ps.setString(7,getNameByStuNum(stuNum));
			ps.setString(8,getStudentByStuNum(stuNum).getCollege());
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
	public boolean sendMail(int stuNum, String teachermail) {
		// TODO Auto-generated method stub
		String host = "smtp.126.com";
		String from = "neupsy@126.com";
		String to = teachermail;
		String subject = "新的预约提醒";
		String content = "有来自学号为"+stuNum+"同学的新预约";
		String username="neupsy@126.com";
		String password="83681101";
	 	String filename = "";
		SendMailDemo.send(host, username, password, from, to, subject, content);
		return true;
	}
	
	@Override
	public boolean validateUser(String name, int stuNum) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select * from t_stuinfo where stuNum = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1,stuNum);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String n = rs.getString("name");
				if(n.equals(name)) {
					DB.close(ps);
					DB.close(conn);
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Appointment> listAppointMentById(int stuNum) {
		// TODO Auto-generated method stub
		ArrayList<Appointment> al = new ArrayList<Appointment>();
		Connection conn = DB.createConn();
		String sql = "select * from t_appointment where stuNum = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1, stuNum);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Appointment appointment = new Appointment();
				appointment.seteTeacher(rs.getString("eTeacher"));
				appointment.setAspect(getAspectById(rs.getInt("aspect")));
				appointment.seteTime(rs.getTimestamp("eTime"));
				appointment.setrDate(rs.getDate("rDate"));
				appointment.setStuNum(rs.getInt("stuNum"));
				appointment.setStuPhone(rs.getString("stuPhone"));
				appointment.setWay(getWayById(rs.getInt("way")));
				appointment.setChecked(rs.getBoolean("checked"));
				
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
	
	public String getTeacherById(int id) {
		Connection conn = DB.createConn();
		String sql = "select name from t_teacher where id = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("name") != null) {
				String name = rs.getString("name");
				DB.close(ps);
				DB.close(conn);
				return name;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return null;
	}
	
	public String getAspectById(int id) {
		Connection conn = DB.createConn();
		String sql = "select aspect from t_aspect where id = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("aspect") != null) {
					String aspect = rs.getString("aspect");
					DB.close(ps);
					DB.close(conn);
					return aspect;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return null;
		
	}
	
	
	public String getWayById(int id) {
		Connection conn = DB.createConn();
		String sql = "select way from t_way where id = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("way") != null) {
					String way = rs.getString("way");
					DB.close(ps);
					DB.close(conn);
					return way;
				}
					
				
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
	public Student getStudentByStuNum(int stuNum) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select * from t_student where stuNum = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		Student student = new Student();
		try {
			ps.setInt(1, stuNum);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				student.setCollege(rs.getString("college"));
				student.setName(rs.getString("name"));
				student.setPassword(rs.getString("password"));
				student.setStuNum(stuNum);
				student.setBirthday(rs.getDate("birthday"));
				student.setNation(rs.getString("nation"));
				student.setHometown(rs.getString("hometown"));
				student.setInterest(rs.getString("interest"));
				student.setGrade(rs.getString("grade"));
				student.setSex(rs.getString("sex"));
//				student.setSymptom(rs.getString("symptom"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return student;
	}

	@Override
	public String getAuthorityById(int id) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select authority from t_authority where id = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString("authority") != null) {
					String  authority = rs.getString("authority");
					DB.close(ps);
					DB.close(conn);
					return authority;
				}
				
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
	public boolean checkExistance(int stuNum) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select * from t_student where stuNum = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1, stuNum);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				DB.close(ps);
				DB.close(conn);
				return true;
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
	public String getNameByStuNum(int stuNum) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select name from t_student where stuNum = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setInt(1, stuNum);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String stuName = rs.getString("name");
				DB.close(ps);
				DB.close(conn);
				return stuName;
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
	public boolean changePassword(int stuNum,String password) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "update t_student set password = ? where stuNum = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
		try {
			ps.setString(1, password);
			ps.setInt(2, stuNum);
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
	public boolean addMusic(int stuNum, String symptom, String eTime,
			String eDate, Date rDate,String phone) {
		// TODO Auto-generated method stub\
		Connection conn = DB.createConn();
		String sql = "insert into t_music(stuNum,symptom,rDate,eDate,eTime,phone) values(?,?,?,?,?,?)";
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setInt(1, stuNum);
			ps.setString(2,symptom);
			ps.setDate(3, new java.sql.Date(rDate.getTime()));
			ps.setString(4, eDate);
			ps.setString(5, eTime);
			ps.setString(6, phone);
			
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
	public Music getMusic(int stuNum, Date rDate) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select * from t_music where stuNum = ? and rDate = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		
	
			try {
				ps.setInt(1, stuNum);
				ps.setDate(2, new java.sql.Date(rDate.getTime()));
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Music music = new Music();
					music.seteDate(rs.getString("eDate"));
					music.seteTime(rs.getString("eTime"));
					music.setPhone(rs.getString("phone"));
					music.setrDate(rDate);
					music.setSymptom(rs.getString("symptom"));
					music.setStuNum(stuNum);
					
					DB.close(ps);
					DB.close(conn);
					return music;
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
	public ArrayList<Music> listMusicById(int stuNum) {
		// TODO Auto-generated method stub
		Connection conn = DB.createConn();
		String sql = "select * from t_music where stuNum = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		ArrayList<Music> musics = new ArrayList<Music>();
		
		try {
			ps.setInt(1, stuNum);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Music music = new Music();
				music.seteDate(rs.getString("eDate"));
				music.seteTime(rs.getString("eTime"));
				music.setPhone(rs.getString("phone"));
				music.setrDate(rs.getDate("rDate"));
				music.setSymptom(rs.getString("symptom"));
				music.setStuNum(stuNum);
				
				musics.add(music);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DB.close(ps);
		DB.close(conn);
		return musics;
	}
	
	@Override
	public boolean sendMusicMail(int stuNum) {
		// TODO Auto-generated method stub
		System.out.println("邮件发送中……");
		String host = "smtp.126.com";
		String from = "neupsy@126.com";
		String to = "neupsy@126.com";
		String subject = "NEUPSY-新的音乐预约提醒";
		String content = "有来自学号为"+stuNum+"同学的新预约";
		String username="neupsy@126.com";
		String password="83681101";
	 	String filename = "";
		SendMailDemo.send(host, username, password, from, to, subject, content);
		return true;
	}

	
}

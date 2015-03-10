package com.idoxie.model;

import java.util.Date;

public class Appointment {
	private int stuNum;
	private String eTeacher;
	private String aTeacher;
	private String  receiver;
	private String stuPhone;
	private Date eTime;
	private Date aTime;
	private String way;
	private String aspect;
	private String remark;
	private String result;
	private Date rDate;
	private boolean checked;
	private String stuName;
	private String college;
	
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public String geteTeacher() {
		return eTeacher;
	}
	public void seteTeacher(String eTeacher) {
		this.eTeacher = eTeacher;
	}
	public String getaTeacher() {
		return aTeacher;
	}
	public void setaTeacher(String aTeacher) {
		this.aTeacher = aTeacher;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getStuPhone() {
		return stuPhone;
	}
	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}
	public Date geteTime() {
		return eTime;
	}
	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}
	public Date getaTime() {
		return aTime;
	}
	public void setaTime(Date aTime) {
		this.aTime = aTime;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getAspect() {
		return aspect;
	}
	public void setAspect(String aspect) {
		this.aspect = aspect;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getrDate() {
		return rDate;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
	
}

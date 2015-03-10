package com.idoxie.util.mail;

public class SendMailDemo {

	 public static void send(String host, String username, String password,
			 String fromAddress, String toAddress, String subject, String content) {

	  // 设置邮件服务器信息

	  MailSenderInfo mailInfo = new MailSenderInfo();

	  mailInfo.setMailServerHost(host);

	  mailInfo.setMailServerPort("25");

	  mailInfo.setValidate(true);

	  

	  // 邮箱用户名

	  mailInfo.setUserName(username);

	  // 邮箱密码

	  mailInfo.setPassword(password);

	  // 发件人邮箱

	  mailInfo.setFromAddress(fromAddress);

	  // 收件人邮箱

	  mailInfo.setToAddress(toAddress);

	  // 邮件标题

	  mailInfo.setSubject(subject);

	  // 邮件内容

	  StringBuffer buffer = new StringBuffer();

	  buffer.append(content);

//	  buffer.append("JAF 1.1.1 jar包下载地址：http://www.oracle.com/technetwork/java/javase/downloads/index-135046.html");

	  mailInfo.setContent(buffer.toString());

	  

	  // 发送邮件

	  SimpleMailSender sms = new SimpleMailSender();

	  // 发送文体格式

	  sms.sendTextMail(mailInfo);

	  // 发送html格式

//	  SimpleMailSender.sendHtmlMail(mailInfo);

	  System.out.println("邮件发送完毕");

	 }

	}


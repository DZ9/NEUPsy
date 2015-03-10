
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>东北大学心理咨询预约与档案管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="assert/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="assert/css/header.css">
	<link rel="stylesheet" type="text/css" href="assert/css/loginError.css">
	<script type="text/javascript" src="assert/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="assert/js/bootstrap.js"></script>
	<script type="text/javascript" src="assert/js/adminLoginError.js"></script>

  </head>
  
  <body>
  
  	
  	<div class="container" id="content">
  		<strong>用户名或密码错误！</strong> <br>
  	</div>
    
  </body>
</html>
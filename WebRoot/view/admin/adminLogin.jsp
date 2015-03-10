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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="assert/css/bootstrap.css">
	<link rel="stylesheet" href="assert/css/base.css">
	<link rel="stylesheet" href="assert/css/login.css">
	<script type="application/javascript" src="assert/js/jquery-1.js"></script>
	<script type="application/javascript" src="assert/js/jquery.js"></script>
	<script type="application/javascript">
	      document.write('<style>.container{opacity:0}</style>');
	      $(function() {
	        $('#login').rcenter({
	          minTop: 100,
	          topRatio: 0.45
	        });
	        $('.container').animate({
	          opacity: 1
	        });
	      });
	    
	</script><style>.container{opacity:0}</style>
  </head>
  
  <body>
    <div style="opacity: 1; position: relative; margin: auto; top: 134.9px;" id="login" class="container">
    <form method="POST" class="box form-horizontal" action="servlet/AdminLogin">
      <fieldset>
        <legend style="font-size:18px; font-family:Microsoft YaHei">东北大学心理咨询预约与档案管理系统</legend>
        
        <div class="control-group">
          <span class="control-label">账号</span>
          <div class="controls">
            <input name="nickname" type="text">
          </div>
        </div>
        <div class="control-group">
          <span class="control-label">密码</span>
          <div class="controls">
            <input name="password" type="password">
          </div>
        </div>
      </fieldset>
      <div class="form-bottom">
        <button type="submit" class="btn btn-primary">登陆</button>
      
      </div>
    </form>
    <div class="copyright center">
      <p>© 东北大学学生指导服务中心心理健康教育中心</p>
    </div>
  </body>
</html>

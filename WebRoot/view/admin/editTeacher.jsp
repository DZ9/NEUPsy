<%@ page language="java" import="java.util.*,com.idoxie.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	Teacher teacher = (Teacher)request.getAttribute("teacher");

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
	<link rel="stylesheet" href="assert/css/datepicker.css">
	<link rel="stylesheet" href="assert/css/bootstrap-datetimepicker.min.css">
	<style type="text/css">
	#luqu-table {
	  line-height: 2;
	}
	
	.avatar-container {
	  width: 35mm;
	  height: 45mm;
	  background: #efefef;
	  margin: 4px 0;
	  border-radius: 2mm;
	  padding: 2mm;
	}
	</style>
	<script type="application/javascript" src="assert/js/jquery-1.js"></script>
	<script type="application/javascript" src="assert/js/bootstrap.js"></script>
	<script type="application/javascript" src="assert/js/jquery.js"></script>
	<script type="application/javascript" src="assert/js/d3.js"></script>
	<style>.container{opacity:0}</style>
  </head>
  
  <body>
    <div style="opacity: 1;" id="info" class="container">
    <h1>东北大学心理咨询预约与档案管理系统</h1>
    <div class="box tabbable">
      <legend>编辑咨询师</legend>
      <div class="tab-content">
        <div id="appointment" class="tab-pane active">
         <form action="servlet/UpdateTeacher" method="POST" class="form-horizontal">
            <div class="control-group">
              <span class="control-label"><label for="newpassword">姓名：</label></span>
              <div class="controls">
                 <input size="16" name="name" type="text" value="<%=teacher.getName() %>" >        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">用户名：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="nickname" readonly value="<%=teacher.getNickname() %>" >        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">邮箱：</label></span>
              <div class="controls">
                 <input size="16" type="email" name="email" value="<%=teacher.getEmail() %>" >        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">权限：</label></span>
              <div class="controls">
            
              	<select name="authority"> 
	          				<option value="3">咨询师</option>
	                    	<option value="1">管理员</option>
	                    	<option value="2">助理管理员</option>
	                	</select> 
              	
                 
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">介绍：</label></span>
              <div class="controls">
                 <textarea size="16" name="description" type="text" value="" ><%=teacher.getDescription() %></textarea>        
              </div>
            </div> 
            <div class="form-actions">
              <button type="submit" class="btn">确定</button>
            </div>
          </form>
        </div>
        
      </div>
    </div>
    <div class="copyright center">
      <p>© 东北大学学生指导服务中心心理健康教育中心</p>
    </div>
  </div>
  </body>
</html>

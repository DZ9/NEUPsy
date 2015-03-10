<%@ page language="java" import="java.util.*,com.idoxie.model.*,com.idoxie.dao.impl.*,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	Music music = (Music)request.getAttribute("music");
	
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
      <h4>编辑音乐预约</h4>
      <div class="tab-content">
        <div id="appointment" class="tab-pane active">
         <form action="servlet/UpdateMusic" method="POST" class="form-horizontal">
            <div class="control-group">
              <span class="control-label"><label for="newpassword">学号：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="stuNum" value="<%=music.getStuNum() %>" readonly >        
              </div>
            </div>
            <%
            	UserDAO userDAO = new UserDAO();
            %>
            <div class="control-group">
              <span class="control-label"><label for="newpassword">姓名：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="name" value="<%=userDAO.getNameByStuNum(music.getStuNum()) %>" readonly>        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">联系电话：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="phone" value="<%=music.getPhone() %>" readonly>        
              </div>
            </div>
            
            <div class="control-group" style="display:none">
              <span class="control-label"><label for="newpassword">预约日期：</label></span>
              <div class="controls">
              	<%
              		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              		String rDate = sdf.format(music.getrDate());
              	 %>
                 <input size="16" type="text" name="rdate" value="<%=rDate %>" readonly>        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">预约日期：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="edate" value="<%=music.geteDate() %>" readonly>        
              </div>
            </div>
            
             <div class="control-group">
              <span class="control-label"><label for="newpassword">预约时间段：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="etime" value="<%=music.geteTime() %>" readonly>        
              </div>
            </div>
           
            
           
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">症状：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="symptom" value="<%=music.getSymptom() %>" readonly>        
              </div>
            </div>
            
              <div class="control-group">
              <span class="control-label"><label for="oldpassword">放松方式：</label></span>
                  <div class="controls" >
                    <input size="16" type="text" name="type"  >  
                  </div>
              </div>
              
              <div class="control-group">
              <span class="control-label"><label for="oldpassword">咨询建议：</label></span>
                  <div class="controls" >
                    <input size="16" type="text" name="advice"  >  
                  </div>
              </div>
              
              
           
             <div class="form-actions">
              <button type="submit" class="btn">确定</button>
            </div>
          </form>
          <script type="text/javascript" src="assert/js/jquery.js" charset="UTF-8"></script>
			<script type="text/javascript" src="assert/js/bootstrap.js"></script>
            <script type="text/javascript" src="assert/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
            <script type="text/javascript" src="assert/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
            <script type="text/javascript">
                $('.form_datetime').datetimepicker({
                    language:  'zh-CN',
                    weekStart: 1,
                    todayBtn:  1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    forceParse: 0,
                    showMeridian: 1
                });
            </script>
        </div>
        
      </div>
    </div>
    <div class="copyright center">
      <p>© 东北大学学生指导服务中心心理健康教育中心</p>
    </div>
  </div>
  </body>
</html>

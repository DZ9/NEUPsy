<%@ page language="java" import="java.util.*,com.idoxie.model.*,com.idoxie.dao.impl.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	ArrayList<Appointment> appointments = (ArrayList<Appointment>)request.getAttribute("appointments");
	ArrayList<Teacher> teachers = (ArrayList<Teacher>)request.getAttribute("teachers");
	ArrayList<Music> musics = (ArrayList<Music>)request.getAttribute("musics");
	Iterator mit = musics.iterator();
	Iterator ait = appointments.iterator();
	Iterator tit = teachers.iterator();
	Iterator tit2 = teachers.iterator();
	Iterator tit3 = teachers.iterator();
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
	<style type="text/css">
		#message-area {
		  border: none;
		  box-shadow: none;
		  cursor: 'pointer';
		  transition: 0.5s;
		}
		
		.message-area-hover,.message-area:focus {
		  background-color: #efefef;
		}
		
		.message-area:focus {
		  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
		    rgba(82, 168, 236, 0.6);
		}
		
		table .xingming {
		  color: #006dcc;
		  cursor: pointer;
		}
	</style>
  </head>
  
  <body>
   <div style="opacity: 1;" id="info" class="container">
    <h1>东北大学心理咨询预约与档案管理系统</h1>
    <div class="box tabbable">
      <ul class="nav nav-tabs">
        <li class="active"><a href="#appointment" data-toggle="tab">所有咨询预约</a></li>
        <li class=""><a href="#music" data-toggle="tab">所有音乐预约</a></li>
        <li class=""><a href="#addAppointment" data-toggle="tab">添加咨询预约</a></li>
        <li class=""><a href="#addTeacher" data-toggle="tab">添加咨询师</a></li>
        <li class=""><a href="#teacher" data-toggle="tab">编辑咨询师</a></li>
        <li class=""><a href="#export" data-toggle="tab">导出咨询预约</a></li>
        <li class=""><a href="#exportMusic" data-toggle="tab">导出音乐预约</a></li>
        <li class="pull-right"><a href="logout.jsp">退出系统</a></li>
      </ul>
      <div class="tab-content">
        <div id="appointment" class="tab-pane active">
         	<table id="regtable" class="table table-bordered table-hover">
            <thead>
            	
              <tr>
                <th width="50">姓名</th>
                <th width="50">电话</th>
                <th width="70">预约时间</th>
                <th width="70">预约咨询师</th>
                <th width="70">咨询咨询师</th>
                <th width="100">咨询时间</th>
                <th width="50">已安排</th>
                <th width="70">登记表</th>
                <th width="70">咨询表</th>
                <th width="60"></th>
              </tr>
              
              <%
              	while(ait.hasNext()) {
              		UserDAO u = new UserDAO();
              		Appointment appointment = (Appointment)ait.next();
              		java.text.DateFormat yyyy = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
              		String eTimeString = yyyy.format(appointment.geteTime());
              		
              		if(appointment.isChecked()) {
              			String aTimeString = yyyy.format(appointment.getaTime());
              %>
              		<tr>
		              	<td><%=appointment.getStuName()%></td>
		                <td><%=appointment.getStuPhone() %></td>
		              	<td><%=eTimeString %></td>
		                <td><%=appointment.geteTeacher() %></td>
		                <td><%=appointment.getaTeacher() %></td>
		                <td><%=aTimeString %></td>
		                <td>是</td>
		                <td> 
			                <form action="servlet/ExportWord" method="post">
		                		<input name="stuNum" style="display:none;" type="text" value="<%=appointment.getStuNum()%>">
			              		<input name="rDate" style="display:none;" type="text" value="<%=appointment.getrDate()%>">	
		                		<button type="submit" class="btn">导出</button>
	                		</form>
		                </td>
		                
		                <td> 
			                <form action="servlet/ExportAdvisory" method="post">
		                		<input name="stuNum" style="display:none;" type="text" value="<%=appointment.getStuNum()%>">
			              		<input name="rDate" style="display:none;" type="text" value="<%=appointment.getrDate()%>">	
			              		<input name="phone" style="display:none;" type="text" value="<%=appointment.getStuPhone()%>">	
		                		<button type="submit" class="btn">导出</button>
	                		</form>
		                </td>
		               
		                <td> 
		                	<form action="servlet/EditAppointment" method="post">
		                		<input name="stuNum" style="display:none;" type="text" value="<%=appointment.getStuNum()%>">
		                		<input name="stuName" style="display:none;" type="text" value="<%=appointment.getStuName()%>">
			              		<input name="phone" style="display:none;" type="text" value="<%=appointment.getStuPhone()%>">
			              		<input name="college" style="display:none;" type="text" value="<%=appointment.getCollege()%>">
			              		<input name="eTeacher" style="display:none;"  type="text" value="<%=appointment.geteTeacher()%>">
			              		<input name="aTeacher" style="display:none;" type="text" value="<%=appointment.getaTeacher()%>">
			              		<input name="aspect" style="display:none;" type="text" value="<%=appointment.getAspect()%>">
			              		<input name="aTime" style="display:none;" type="text" value="<%=appointment.getaTime()%>">
			              		<input name="eTime" style="display:none;" type="text" value="<%=appointment.geteTime()%>">
		                		<input name="rDate" style="display:none;" type="text" value="<%=appointment.getrDate()%>">
		                		<input name="content" style="display:none;" type="text" value="<%=appointment.getContent()%>">
		                		<input name="suggestion" style="display:none;" type="text" value="<%=appointment.getSuggestion()%>">
		                		<button type="submit" class="btn">编辑</button>
		                	</form>
		               		
		                </td>
		             </tr>				
              <%
              		}else {
              %>			
              		<tr>
		              	<td><%=appointment.getStuName()%></td>
		                <td><%=appointment.getStuPhone() %></td>
		              	<td><%=eTimeString %></td>
		                <td><%=appointment.geteTeacher() %></td>
		                <td>未安排</td>
		                <td>未安排</td>
		                <td>是</td>
		                <td> 
			                <form action="servlet/ExportWord" method="post">
		                		<input name="stuNum" style="display:none;" type="text" value="<%=appointment.getStuNum()%>">
			              		<input name="rDate" style="display:none;" type="text" value="<%=appointment.getrDate()%>">	
		                		<button type="submit" class="btn">导出</button>
	                		</form>
		                </td>
		                
		                <td> 
			                <form action="servlet/ExportAdvisory" method="post">
		                		<input name="stuNum" style="display:none;" type="text" value="<%=appointment.getStuNum()%>">
			              		<input name="rDate" style="display:none;" type="text" value="<%=appointment.getrDate()%>">	
			              		<input name="phone" style="display:none;" type="text" value="<%=appointment.getStuPhone()%>">	
		                		<button type="submit" class="btn">导出</button>
	                		</form>
		                </td>
		               
		                <td> 
		                	<form action="servlet/EditAppointment" method="post">
		                		<input name="stuNum" style="display:none;" type="text" value="<%=appointment.getStuNum()%>">
		                		<input name="stuName" style="display:none;" type="text" value="<%=appointment.getStuName()%>">
			              		<input name="phone" style="display:none;" type="text" value="<%=appointment.getStuPhone()%>">
			              		<input name="college" style="display:none;" type="text" value="<%=appointment.getCollege()%>">
			              		<input name="eTeacher" style="display:none;"  type="text" value="<%=appointment.geteTeacher()%>">
			              		<input name="aTeacher" style="display:none;" type="text" value="<%=appointment.getaTeacher()%>">
			              		<input name="aspect" style="display:none;" type="text" value="<%=appointment.getAspect()%>">
			              		<input name="aTime" style="display:none;" type="text" value="<%=appointment.getaTime()%>">
			              		<input name="eTime" style="display:none;" type="text" value="<%=appointment.geteTime()%>">
		                		<input name="rDate" style="display:none;" type="text" value="<%=appointment.getrDate()%>">
		                		<input name="content" style="display:none;" type="text" value="<%=appointment.getContent()%>">
		                		<input name="suggestion" style="display:none;" type="text" value="<%=appointment.getSuggestion()%>">
		                		<button type="submit" class="btn">编辑</button>
		                	</form>
		               		
		                </td>
		             </tr>				
              <%
              		}
              	}
              %>			
            </thead>
            <tbody></tbody>
          </table>
        </div>
        
        <div id="music" class="tab-pane">
         	<table id="regtable" class="table table-bordered table-hover">
            <thead>
              <tr>
              
                <th width="30">姓名</th>
                <th width="40">学号</th>
                 <th width="40">学院</th>
                <th width="40">电话</th>
                <th width="40">症状</th>
                <th width="40">预约日期</th>
                <th width="40">预约时间段</th>
                <th width="60"></th>
                <th width="60"></th>
                
              </tr>
              <%
              	while(mit.hasNext()) {
            	  UserDAO userDAO = new UserDAO();
            	  Music music = (Music)mit.next();
               %>  
	              <tr>
	              	<td><%=userDAO.getNameByStuNum(music.getStuNum()) %></td>
	                <td><%=music.getStuNum() %></td>
	                <td><%=userDAO.getStudentByStuNum(music.getStuNum()).getCollege() %></td>
	              	<td><%=music.getPhone() %></td>
	                <td><%=music.getSymptom() %></td>
	                <td><%=music.geteDate() %> </td>
	                <td><%=music.geteTime() %></td>
	                <td> 
	                <form action="servlet/ExportMusic" method="post">
	                	<input name="stuNum" style="display:none;" type="text" value="<%=music.getStuNum()%>">
	              		<input name="eDate" style="display:none;" type="text" value="<%=music.geteDate()%>">
	              		<input name="phone" style="display:none;" type="text" value="<%=music.getPhone()%>">
	              		<input name="eTime" style="display:none;"  type="text" value="<%=music.geteTime()%>">
	              		<input name="symptom" style="display:none;" type="text" value="<%=music.getSymptom()%>">
	              		<input name="rDate" style="display:none;" type="text" value="<%=music.getrDate()%>">
		               	
		                <button type="submit">导出</button>
	                </form>	
	                
	                <td>
	                <form action="servlet/EditMusic" method="post">
	                	<input name="stuNum" style="display:none;" type="text" value="<%=music.getStuNum()%>">
	              		<input name="eDate" style="display:none;" type="text" value="<%=music.geteDate()%>">
	              		<input name="phone" style="display:none;" type="text" value="<%=music.getPhone()%>">
	              		<input name="eTime" style="display:none;"  type="text" value="<%=music.geteTime()%>">
	              		<input name="symptom" style="display:none;" type="text" value="<%=music.getSymptom()%>">
		                <input name="rDate" style="display:none;" type="text" value="<%=music.getrDate()%>">
		                <button type="submit">编辑</button>
	                </form>	
	                </td>
	                
	                 
	              </tr>
              <%  
              	}
              %>
             
            </thead>
            <tbody></tbody>
          </table>
        </div>
        
        <div id="addAppointment" class="tab-pane">
         <form action="servlet/AddAppointmentByAdmin" method="POST" class="form-horizontal">
            <div class="control-group">
              <span class="control-label"><label for="newpassword">学号：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="stuNum" >        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">姓名：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="name" >        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">联系电话：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="phone" >        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">学院：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="college" >        
              </div>
            </div>
            
             <div class="control-group">
              <span class="control-label"><label for="newpassword">咨询意向：</label></span>
              <div class="controls">
                <select name="aspect">
                	<option value=1>情绪、情感</option>
                	<option value=2>个性</option>
                	<option value=3>压力</option>
                	<option value=4>神经质</option>
                	<option value=5>人际关系</option>
                	<option value=6>学习、职业、生涯规划</option>
                	<option value=7>适应</option>
                	<option value=8>其他</option>
                </select>
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">预约方式：</label></span>
                  <div class="controls">
                   <select name="way">
		               	<option value=1>
		                	电话
		                </option>
		                <option value=2>
		                	网络
		                </option>
		                <option value=3>
		                	推介
		                </option>
		                <option value=4>
		                	来访
		                </option>
	               </select>
                  </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">预约时间：</label></span>
                  <div class="controls date form_datetime" data-date="2015-04-01T00:00:00Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <input size="16" type="text" name="eTime" readonly>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            
           
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">预约咨询师：</label></span>
              <div class="controls">
                <select name="eTeacher">
                	<%
                		while(tit2.hasNext()) {
                		Teacher teacher = (Teacher)tit2.next();
           			%>
           				<option value="<%=teacher.getNickname()%>"><%=teacher.getName()%></option>
           			<%
                		}
                	%>
                	
                </select>
              </div>
            </div>
            
             <div class="control-group">
              <span class="control-label"><label for="oldpassword">咨询时间：</label></span>
                  <div class="controls date form_datetime" data-date="2015-04-01T00:00:00Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <input size="16" type="text" name="aTime" readonly>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">咨询咨询师：</label></span>
              <div class="controls">
              	<select name="aTeacher">
                   <%
                		while(tit3.hasNext()) {
                		Teacher teacher = (Teacher)tit3.next();
           			%>
           				<option value="<%=teacher.getNickname()%>"><%=teacher.getName()%></option>
           			<%
                		}
                	%>
                </select>
              </div>
            </div>
            
            <div class="form-actions">
              <button type="submit" class="btn">添加</button>
            </div>
          </form>
        </div>
        
       
        <div id="addTeacher" class="tab-pane">
        
          <form action="servlet/AddTeacher" method="POST" class="form-horizontal">
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">姓名</label></span>
              <div class="controls">
                <input name="name" id="oldpassword" type="text">
              </div>
            </div>
            <div class="control-group">
              <span class="control-label"><label for="newpassword">用户名</label></span>
              <div class="controls">
                <input name="nickname" id="newpassword" type="text">
              </div>
            </div>
            <div class="control-group">
              <span class="control-label"><label for="repassword">密码</label></span>
              <div class="controls">
                <input name="password" id="repassword" type="text">
              </div>
            </div>
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">邮箱</label></span>
              <div class="controls">
                <input name="email" id="oldpassword" type="email">
              </div>
            </div>
            <div class="control-group">
              <span class="control-label"><label for="repassword">介绍</label></span>
              <div class="controls">
                <textarea name="description" id="repassword" type="text"></textarea>
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="repassword">权限</label></span>
              <div class="controls">
               <select name="authority">
               	<option value=1>
                	管理员
                </option>
                <option value=2>
                	助理管理员
                </option>
                <option value=3>
                	咨询师
                </option>
               </select>
              </div>
            </div>
            
            <div class="form-actions">
              <button type="submit" class="btn">添加</button>
            </div>
          </form>
        </div>
        
        <div id="teacher" class="tab-pane">
        <table id="regtable" class="table table-bordered table-hover">
            <thead>
              <tr>
              
                <th width="50">姓名</th>
                <th width="100">用户名</th>
                <th width="100">权限</th>
                <th width="100">介绍</th>
                <th width="100">邮箱</th>
                <th width="50"></th>
                <th width="50"></th>
                
              </tr>
              
              	<%
              		while(tit.hasNext()) {
              		Teacher t = (Teacher)tit.next();
              	%>
              		
              		<tr>
              		<td><%=t.getName() %></td>
	                <td><%=t.getNickname() %></td>
	              	<td><%=t.getAuthority() %></td>
	                <td><%=t.getDescription() %></td>
	                <td><%=t.getEmail() %></td>
	                
	                <td> 
	                <form action="servlet/EditTeacher" method="post">
	                	<input name="nickname" style="display:none;" type="text" value="<%=t.getNickname()%>">
	              		<input name="name" style="display:none;" type="text" value="<%=t.getName()%>">
	              		<input name="description" style="display:none;" type="text" value="<%=t.getDescription()%>">
	              		<input name="authority" style="display:none;"  type="text" value="<%=t.getAuthority()%>">
	              		<input name="email" style="display:none;" type="text" value="<%=t.getEmail()%>">
		                <button type="submit">编辑</button>
	                </form>
	                </td>
	                <td> 
	                <form action="servlet/DeleteTeacher" method="post">
	                	<input name="name" style="display:none;" type="text" value="<%=t.getNickname()%>">
		                <button type="submit">删除</button>
	                </form>
	                </td>
	                </tr>
              	<%
              		}
              	%>
              	
              
            </thead>
            <tbody></tbody>
          </table>
        </div>
        
        <div id="export" class="tab-pane">
        	<form action="servlet/ExportExcel" method="POST" class="form-horizontal">
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">开始时间：</label></span>
                  <div class="controls date form_datetime" data-date="2015-04-01T00:00:00Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <input size="16" type="text" value="" readonly name="beginDate">
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">结束时间：</label></span>
                  <div class="controls date form_datetime" data-date="2015-04-01T00:00:00Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <input size="16" type="text" value="" readonly name="endDate">
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            <div class="form-actions">
              <button type="submit" class="btn">导出</button>
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
        
        <div id="exportMusic" class="tab-pane">
        	<form action="servlet/ExportMusicExcel" method="POST" class="form-horizontal">
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">开始时间：</label></span>
                  <div class="controls date form_date" data-date="2015-04-01" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
                    <input size="16" type="text" value="" readonly name="beginDate">
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">结束时间：</label></span>
                  <div class="controls date form_date" data-date="2015-04-01" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
                    <input size="16" type="text" value="" readonly name="endDate">
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            <div class="form-actions">
              <button type="submit" class="btn">导出</button>
            </div>
            </form>
            <script type="text/javascript" src="assert/js/jquery.js" charset="UTF-8"></script>
			<script type="text/javascript" src="assert/js/bootstrap.js"></script>
            <script type="text/javascript" src="assert/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
            <script type="text/javascript" src="assert/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
            <script type="text/javascript">
                
                $('.form_date').datetimepicker({
					language:  'zh-CN',
					weekStart: 1,
					todayBtn:  1,
					autoclose: 1,
					todayHighlight: 1,
					startView: 2,
					minView: 2,
					forceParse: 0
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

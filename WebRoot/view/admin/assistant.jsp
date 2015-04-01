<%@ page language="java" import="java.util.*,com.idoxie.model.*,com.idoxie.dao.impl.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList<Appointment> appointments = (ArrayList<Appointment>)request.getAttribute("appointments");
Iterator ait = appointments.iterator();
ArrayList<Teacher> teachers = (ArrayList<Teacher>)request.getAttribute("teachers");
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
        <li class=""><a href="#addAppointment" data-toggle="tab">添加咨询预约</a></li>
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
              		//System.out.println(appointment.geteTime());
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
		                <td>否</td>
		                <td> <button type="submit" class="btn">导出</button></td>
		                <td> 
							<form action="servlet/EditAppointment" method="post">
		                		<input name="stuNum" style="display:none;" type="text" value="<%=appointment.getStuNum()%>">
		                		<input name="stuName" style="display:none;" type="text" value="<%=appointment.getStuName()%>">
			              		<input name="phone" style="display:none;" type="text" value="<%=appointment.getStuPhone()%>">
			              		<input name="college" style="display:none;" type="text" value="">
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
                  <div class="controls date form_datetime" data-date="2014-11-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
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
           				<option><%=teacher.getName()%></option>
           			<%
                		}
                	%>
                	
                </select>
              </div>
            </div>
            
             <div class="control-group">
              <span class="control-label"><label for="oldpassword">咨询时间：</label></span>
                  <div class="controls date form_datetime" data-date="2014-11-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
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
           				<option><%=teacher.getName()%></option>
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
    <div class="copyright center">
      <p>© 东北大学学生指导服务中心心理健康教育中心</p>
    </div>
  </div>
  
  </body>
</html>

<%@ page language="java" import="java.util.*,com.idoxie.model.*,com.idoxie.dao.impl.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	ArrayList<Appointment> appointmenetsByTeacher = (ArrayList<Appointment>)request.getAttribute("appointmenetsByTeacher");
	Iterator ait = appointmenetsByTeacher.iterator();
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
        <li class="active"><a href="#appointment" data-toggle="tab">我的咨询预约</a></li>
        <li class="pull-right"><a href="http://202.107.127.116:8084/admin/logout">退出系统</a></li>
      </ul>
      <div class="tab-content">
        <div id="appointment" class="tab-pane active">
         	<table id="regtable" class="table table-bordered table-hover">
            <thead>
              <tr>
              
                <th width="50">姓名</th>
                <th width="60">电话</th>
                <th width="70">预约时间</th>
                <th width="60">预约咨询师</th>
                <th width="60">咨询咨询师</th>
                <th width="100">咨询时间</th>
                <th width="50">已安排</th>
                <th width="100">预约登记表</th>
                <th width="60"></th>
                
              </tr>
              <%
              	while(ait.hasNext()) {
              		UserDAO u = new UserDAO();
              		Appointment appointment = (Appointment)ait.next();
              		java.text.DateFormat yyyy = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm");
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
		                	<form action="servlet/EditAppointment" method="post">
		                		<input name="stuNum" style="display:none;" type="text" value="<%=appointment.getStuNum()%>">
		                		<input name="stuName" style="display:none;" type="text" value="<%=appointment.getStuName()%>">
			              		<input name="phone" style="display:none;" type="text" value="<%=appointment.getStuPhone()%>">
			              		<input name="college" style="display:none;" type="text" value="<%=appointment.getCollege()%>">
			              		<input name="eTeacher" style="display:none;"  type="text" value="<%=appointment.geteTeacher()%>">
			              		<input name="aTeacher" style="display:none;" type="text" value="<%=appointment.getaTeacher()%>">
			              		<input name="aspect" style="display:none;" type="text" value="<%=appointment.getAspect()%>">
			              		<input name="aTime" style="display:none;" type="text" value="<%=appointment.getaTime()%>">
			              		<input name="eTime" style="display:none;" type="text" value="<%=eTimeString %>">
		                		<input name="rDate" style="display:none;" type="text" value="<%=aTimeString%>">
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
			              		<input name="eTime" style="display:none;" type="text" value="<%=eTimeString%>">
			              		<input name="rDate" style="display:none;" type="text" value="<%=appointment.getrDate()%>">
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

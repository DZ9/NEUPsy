<%@ page language="java" import="java.util.*,com.idoxie.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	ArrayList<Appointment> appointment = (ArrayList<Appointment>)request.getAttribute("appointment");
	Iterator it = appointment.iterator();
	
	ArrayList<Teacher> teacher = (ArrayList<Teacher>)request.getAttribute("teacher");
	ArrayList<Music> musics = (ArrayList<Music>)request.getAttribute("musics");
	Iterator mit = musics.iterator();
	Iterator tit = teacher.iterator();
	Iterator tit2 = teacher.iterator();
	

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
	<script type="text/javascript" src="theme/upload.js"></script>
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
	#d_phone {
		font-size:12px;
		position:absolute;
		right:40px;
		top:15px;
	}
	#d_addr {
		position:absolute;
		top:100px;
		right:3px;
		font-size:12px;
	}
	#psy {
		width:100px;
		height:100px;
		position:absolute;
		right:80px;
		top:220px;
	}
	#xq {
		width:100px;
		height:100px;
		position:absolute;
		right:80px;
		top:370px;
		
	}
	#psy_t {
		width:100px;
		height:100px;
		position:absolute;
		right:70px;
		top:320px;
		font-size:10px;	
	}
	#xq_t {
		width:100px;
		height:100px;
		position:absolute;
		right:70px;
		top:470px;
		font-size:10px;

	}
	</style>
  </head>
  
  <body>
   <div style="opacity: 1;" id="info" class="container">
    <h2>东北大学心理咨询预约与档案管理系统</h2>
    <p class="d_right" id="d_phone"><strong>咨询预约电话：</strong><br>83681101(南湖校区)<br>18804009525（浑南校区）</p>
	<p class="d_right" id="d_addr"><strong>咨询预约地址：</strong><br>活动中心307（南湖校区）<br>生命科学大楼B座413（浑南校区）</p>
	<img id="psy" src="assert/img/psy.png"/><span id="psy_t">关注心理健康港湾<br>http://psy.neu.edu.cn/</span>
	<img id="xq" src="assert/img/xq.png"/><span id="xq_t">关注NEU心理港湾<br>《心晴》微信订阅号</span>
    <div class="box tabbable">
      <ul class="nav nav-tabs">
        <li class="active"><a href="#appointment" data-toggle="tab">预约心理咨询</a></li>
        <li class=""><a href="#music" data-toggle="tab">预约音乐放松</a></li>
        <li class=""><a href="#myAppointment" data-toggle="tab">我的预约</a></li>
        <li class=""><a href="#changePassword" data-toggle="tab">修改密码</a></li>
        <li class=""><a href="#teacher" data-toggle="tab">咨询师介绍</a></li>
        <li class="pull-right"><a href="logout.jsp">退出系统</a></li>
      </ul>
      <div class="tab-content">
        <div id="appointment" class="tab-pane active">
         <form action="servlet/AddAppointment" method="POST" class="form-horizontal">
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">预约时间：</label></span>
                  <div class="controls date form_datetime" data-date="2014-11-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <input size="16" type="text" name="date" readonly>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            <script type="text/javascript" src="./theme/jquery.js" charset="UTF-8"></script>
			<script type="text/javascript" src="./theme/bootstrap.js"></script>
            <script type="text/javascript" src="./theme/bootstrap-datetimepicker.js" charset="UTF-8"></script>
            <script type="text/javascript" src="./theme/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
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
            <div class="control-group">
              <span class="control-label"><label for="newpassword">预约咨询师：</label></span>
              <div class="controls">
                <select name="teacher">
                	<%
        				while(tit.hasNext()) {
        					Teacher tc = (Teacher)tit.next();
        			%>
                	<option><%=tc.getName() %></option>
                	
                	<%
        				}
                	%>
                </select>
              </div>
            </div>
            <div class="control-group">
              <span class="control-label"><label for="repassword">咨询意向：</label></span>
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
              <span class="control-label"><label for="repassword">联系电话：</label></span>
              <div class="controls ">
                <input name="telephone" id="repassword" type="text">
              </div>
            </div>
            <div class="form-actions">
              <button type="submit" class="btn">提交预约</button>
            </div>
          </form>
         
          <div class="pagination pagination-centered">
            <ul id="regtable-pagination">
              <!-- <li><a href="">&laquo;</a></li>
              <li><a href="">1</a></li>
              <li><a href="">&raquo;</a></li> -->
            </ul>
          </div>
        </div>
        
        <div id="music" class="tab-pane">
         <form action="servlet/AddMusic" method="POST" class="form-horizontal">
            
            <div class="control-group">
              <span class="control-label"><label for="repassword">联系电话：</label></span>
              <div class="controls ">
                <input name="phone" id="repassword" type="text">
              </div>
            </div>
            
            
            <div class="control-group">
              <span class="control-label"><label for="repassword">症状：</label></span>
              <div class="controls ">
                 <select name="symptom">
                	<option>神经紧张</option>
                	<option>压力过大 </option>
                	<option>情绪不安</option>
                	<option>失眠焦虑</option>
                	<option>遇到突发意外事件</option>
                	<option>入睡困难</option>
                	<option>神经症</option>
                	<option>神经衰弱 </option>
                	<input name="other" id="repassword" placeholder="其他" type="text">
                </select>
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">预约日期：</label></span>
                  <div class="controls date form_date" data-date="2014-11-16" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
                    <input size="16" type="text" name="eDate" value="" readonly>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">预约时间段：</label></span>
                  <div class="controls ">
                     <select name="eTime">
						<option>09:00-09:50</option>
						<option>10:00-10:50</option>
						<option>11:00-11:50</option>
						<option>14:00-14:50</option>
						<option>15:00-15:50</option>
						<option>16:00-16:50</option>
                    </select>
                  </div>
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
            
            <div class="form-actions">
              <button type="submit" class="btn">提交预约</button>
            </div>
          </form>
         
          <div class="pagination pagination-centered">
            <ul id="regtable-pagination">
              <!-- <li><a href="">&laquo;</a></li>
              <li><a href="">1</a></li>
              <li><a href="">&raquo;</a></li> -->
            </ul>
          </div>
        </div>
        <div id="myAppointment" class="tab-pane">
         <legend>心理咨询</legend>
         <table id="regtable" class="table table-bordered table-hover">
            <thead>
              <tr>
                <th width="200">预约时间</th>
                <th width="100">预约咨询师</th>
                <th width="100">实际咨询师</th>
                <th width="100">实际咨询时间</th>
                <th width="100">已完成</th>
                
              </tr>
              
              <%
              	while(it.hasNext()) {
              		
              		Appointment ap = (Appointment)it.next();
              		if(ap.isChecked()) {
              %>
              		<tr>
		              	<td><%=ap.geteTime() %></td>
		                <td><%=ap.geteTeacher() %></td>
		                <td><%=ap.getaTeacher() %></td>
		                <td><%=ap.getaTime() %></td>
		                <td>是</td>
		            </tr>
              			
	       			<%
	       				}else {
	       					
	       			%>
	       				<tr>
		              	<td><%=ap.geteTime() %></td>
		                <td><%=ap.geteTeacher() %></td>
		                <td>未安排</td>
		                <td>未安排</td>
		                <td>否</td>
		            </tr>
	       			
	       			<%
	       				}
	       					
	       			%>
	       			
              <%
              	}
              %>
              
              
             
            </thead>
            <tbody></tbody>
          </table>
           <legend>音乐放松</legend>
           <table id="regtable" class="table table-bordered table-hover">
            <thead>
              <tr>
                <th width="200">预约时间</th>
                <th width="100">症状</th>
              </tr>
              
              	<%
              		while(mit.hasNext()) {
              			Music music = (Music)mit.next();
              			%>	
              			<tr>
              			<td><%=music.geteTime() %></td>
                		<td><%=music.getSymptom() %></td>
                		</tr>
              			<%	
              		}
              	%>
              	
              
            </thead>
            <tbody></tbody>
          </table>
        </div>
        
       
        <div id="changePassword" class="tab-pane">
          <form action="servlet/ChangePW" method="POST" class="form-horizontal">
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">新密码</label></span>
              <div class="controls">
                <input name="password" id="newpassword" type="password">
              </div>
            </div>
            <div class="control-group">
              <span class="control-label"><label for="repassword">确认新密码</label></span>
              <div class="controls">
                <input name="rePassword" id="repassword" type="password">
              </div>
            </div>
            <div class="form-actions">
              <button type="submit" class="btn">保存</button>
            </div>
          </form>
        </div>
        
        <div id="teacher" class="tab-pane">
        	<%
        	   
        		while(tit2.hasNext()) {
        			Teacher tc = (Teacher)tit2.next();
        	%>
        			<legend><%=tc.getName() %></legend>
            		<p><%=tc.getDescription() %></p>
        	<%		
        		}
        	%>
        	
            
        </div>
      </div>
    </div>
    <div class="copyright center">
      <p style="font-size:12px">© 东北大学学生指导服务中心  心理健康教育中心</p>
    </div>
  </div>
  
    
  </div>

  </body>
</html>

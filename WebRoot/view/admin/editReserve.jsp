<%@ page language="java" import="java.util.*,com.idoxie.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	Appointment appointment = (Appointment)request.getAttribute("appointment");
	ArrayList<Teacher> teachers = (ArrayList<Teacher>)request.getAttribute("teachers");
	Iterator it = teachers.iterator();
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
      <h4>编辑预约</h4>
      <div class="tab-content">
        <div id="appointment" class="tab-pane active">
         <form action="servlet/UpdateReserve" method="POST" class="form-horizontal">
            <div class="control-group">
              <span class="control-label"><label for="newpassword">学号：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="stuNum" value="<%=appointment.getStuNum() %>" readonly >        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">姓名：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="name" value="<%=appointment.getStuName() %>" readonly>        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">联系电话：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="phone" value="<%=appointment.getStuPhone() %>" readonly>        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">学院：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="college" value="<%=appointment.getCollege() %>" readonly>        
              </div>
            </div>
            
             <div class="control-group">
              <span class="control-label"><label for="newpassword">咨询意向：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="aspect" value="<%=appointment.getAspect() %>" readonly>        
              </div>
            </div>
            <%
	            java.text.DateFormat yyyy = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm");
	      		String eTimeString = yyyy.format(appointment.geteTime());
            %>
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">预约时间：</label></span>
                  <div class="controls date" data-date="2014-11-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <input size="16" type="text" name="eTime" value="<%=eTimeString %>" readonly>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            
           
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">预约咨询师：</label></span>
              <div class="controls">
                 <input size="16" type="text" name="eTeacher" value="<%=appointment.geteTeacher() %>" readonly>        
              </div>
            </div>
            
              <div class="control-group">
              <span class="control-label"><label for="oldpassword">咨询时间：</label></span>
                  <div class="controls date form_datetime" data-date="2014-11-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <%
                    	if(appointment.getaTime() != null) {
            	      		String aTimeString = yyyy.format(appointment.getaTime());
            	    %> 
            	    	<input size="16" type="text" name="aTime" value="<%=aTimeString %>" >
	                    <span class="add-on"><i class="icon-remove"></i></span>
	                    <span class="add-on"><i class="icon-th"></i></span>
            	    <% 		
                    	}else {
                    %>
                    		<input size="16" type="text" name="aTime" value="" >
    	                    <span class="add-on"><i class="icon-remove"></i></span>
    	                    <span class="add-on"><i class="icon-th"></i></span>
                    <%
                    	}
                    %>
                    
                  </div>
              </div>
              
              <%
	            java.text.DateFormat yyy = new java.text.SimpleDateFormat("yyyy-MM-dd");
	      		String rDate = yyy.format(appointment.getrDate());
            %>
              <div class="control-group" style="display:none">
              <span class="control-label"><label for="oldpassword">该预约日期：</label></span>
                  <div class="controls date form_datetime" data-date="2014-11-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <input size="16" type="text" name="rDate" value="<%=rDate %>" >
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
              </div>
            <!-- 
            <%
            	//if(!appointment.getaTeacher().equals("null")) {
            %>
            		 <div class="control-group">
                     <span class="control-label"><label for="newpassword">咨询师：</label></span>
                     <div class="controls">
                        <input size="16" type="text" name="aTeacher" value="<%=appointment.getaTeacher() %>">        
                     </div>
                   </div>
            <%
            	//}else {
            		
            %>
             -->
            		 <div class="control-group">
                     <span class="control-label"><label for="newpassword">咨询师：</label></span>
                     <div class="controls">
                       <select name="aTeacher">
            <%
            	while(it.hasNext()) {
            		Teacher t = (Teacher)it.next();
            		%>	
            			<option value="<%=t.getNickname()%>"><%=t.getName() %></option>
            		<%	
            	}
            %>          		
                       </select>       
                     </div>
                   </div>
            <%		
            	//}
            %>
           <div class="control-group">
              <span class="control-label"><label for="newpassword">咨询内容：</label></span>
              <%
              	if(!appointment.getContent().equals("null")) {
              	
              %>
              		<div class="controls">
                 		<textarea size="16" type="text" name="content" ><%=appointment.getContent()%></textarea>    
              		</div>
              	<%
              	              	}else {
              	              	
               %>
               		<div class="controls">
                 		<textarea size="16" type="text" name="content" ></textarea>        
              		</div>
               <%
              	 }
                %>
            </div>
            <div class="control-group">
              <span class="control-label"><label for="newpassword">咨询建议：</label></span>
              <%
              	if(!appointment.getSuggestion().equals("null")) {
              	
              %>
             
              		<div class="controls">
                 		<textarea size="16" type="text" name="suggestion" ><%=appointment.getSuggestion()%></textarea>           
              		</div>
              		
              	<%
              	 }else {
              	              	
               %>
               		<div class="controls">
                 		<textarea size="16" type="text" name="suggestion" ></textarea>      
              		</div>
               <%
              	 }
                %>
             
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

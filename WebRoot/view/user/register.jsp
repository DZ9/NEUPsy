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
	<link rel="stylesheet" href="assert/css/register.css">
	<link rel="stylesheet" href="assert/css/datepicker.css">
	<link rel="stylesheet" href="assert/css/bootstrap-datetimepicker.min.css">
	<script type="application/javascript" src="assert/js/jquery-1.js"></script>
	<script type="application/javascript" src="assert/js/bootstrap.js"></script>
	<script type="application/javascript" src="assert/js/bootstrap-datepicker.js"></script>
	<script type="application/javascript" src="assert/js/jquery_002.js"></script>
	<script type="application/javascript" src="assert/js/jquery.js"></script>
	<script type="application/javascript" src="assert/js/register.js"></script><style>.container{opacity:0}</style>
	<script type="application/javascript" src="assert/js/userRegister.js"></script>
  </head>
  
  <body>
    <div style="opacity: 1; position: relative; margin: auto; top: 10px;" id="register" class="container">
    <div class="logo">
      <h2>东北大学心理咨询预约与档案管理系统</h2>
    </div>
    <form novalidate id="reg" class="box form-horizontal" action="servlet/UserRegister" method="POST">
      

<fieldset>
  <legend id="basic-part">基本信息</legend>
  <div class="control-group">
    <label class="control-label" for="zhaoshengpici">学号*：</label>
    <div class="controls">
      <input id="stuNum" name="stuNum" type="text">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="xuexizhongxin">姓名*：</label>
    <div class="controls">
      <input id="stuName" name="stuName" type="text">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="baomingxuhao">所在学院*：</label>
    <div class="controls">
	      <select  name="college" id="college">
	    		<option>
	    		文法学院
	    		</option>
	    		<option>
				外国语学院
				</option>
				<option>
				艺术学院
				</option>
				<option>
				工商管理学院
				</option>
				<option>
				理学院
				</option>
				<option>
				资源与土木工程学院
				</option>
				<option>
				材料与冶金学院
				</option>
				<option>
				机械工程与自动化学院
				</option>
				<option>
				信息科学与工程学院
				</option>
				<option>
				软件学院
				</option>
				<option>
				中荷生物医学与信息工程学院
				</option>
				<option>
				国防教育学院
				</option>
				<option>
				国际交流学院
				</option>
				<option>
				生命科学与健康学院
				</option>
				<option>
				江河建筑学院
				</option>
				<option>
				马克思主义学院
				</option>
				<option>
				其他
				</option>
	    		
	    	</select>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="baomingxuhao">专业*：</label>
    <div class="controls">
      <input id="major" name="major" type="text">
    </div>
  </div>

  <div class="control-group">
    <label class="control-label" for="baomingxuhao">密码*：</label>
    <div class="controls">
      <input id="password" name="password" type="password">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="baomingxuhao">重复密码*：</label>
    <div class="controls">
      <input id="rePassword" name="rePassword" type="password">
    </div>
  </div>
  
  
  <div class="control-group">
    <label class="control-label" for="baomingxuhao">年级*：</label>
    <div class="controls">
    	<select  name="grade" id="grade">
    		<option>
    			大一
    		</option>
    		<option>
    			大二
    		</option>
    		<option>
    			大三
    		</option>
    		<option>
    			大四
    		</option>
    		<option>
    			研一
    		</option>
    		<option>
    			研二
    		</option>
    		<option>
    			研三
    		</option>
    		<option>
    			博一
    		</option>
    		<option>
    			博二
    		</option>
    		<option>
    			其他
    		</option>
    	</select>
     
    </div>
  </div>
  
  <div class="control-group">
     <span class="control-label"><label for="newpassword">性别*：</label></span>
     <div class="controls">
       <select  name="sex" id="sex">
       	<option>男</option>
           <option>女</option>
       </select>
     </div>
   </div>
   
   <div class="control-group">
     <span class="control-label"><label for="oldpassword">出生年月*：</label></span>
         <div class="controls date form_date" data-date="2014-11-16" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
           <input size="16" type="text" value="" id="birthday" name="birthday" readonly>
           <span class="add-on"><i class="icon-remove"></i></span>
           <span class="add-on"><i class="icon-th"></i></span>
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
   <div class="control-group">
     <span class="control-label"><label for="repassword">兴趣特长*：</label></span>
     <div class="controls ">
       <input name="interest" id="interest" type="text">
     </div>
   </div>
   
    <div class="control-group">
      <span class="control-label"><label for="repassword">民族*：</label></span>
      <div class="controls ">
        <input name=nation id="nation" type="text">
      </div>
    </div>
    
    <div class="control-group">
      <span class="control-label"><label for="repassword">生源地*：</label></span>
      <div class="controls ">
        <input name="hometown" id="hometown" type="text">
      </div>
    </div>
</fieldset>

      <div class="form-actions">
        <a href="view/user/login.jsp" class="btn">« 返回登陆</a>
        <button class="btn btn-primary" type="submit" id="sub" value="Submit">注册</button>
      </div>
    </form>
    <div class="copyright center">
      <p>© 东北大学学生指导服务中心  心理健康教育中心</p>
    </div>
  </div>
  </body>
  
</html>

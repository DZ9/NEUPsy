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
                <th width="50">已完成</th>
                <th width="100">预约登记表</th>
                <th width="60"></th>
                
              </tr>
              <tr>
              	<td>张煜</td>
                <td>20124743</td>
              	<td>2012-12-12</td>
                <td>zahng</td>
                <td>sdf</td>
                <td>2012-12-12</td>
                <td>asd</td>
                <td> <button type="submit" class="btn">导出</button></td>
                <td> <button type="submit" class="btn">编辑</button></td>
              </tr>
            </thead>
            <tbody></tbody>
          </table>
        </div>
        
        
        <div id="addAppointment" class="tab-pane">
         <form action="/admin/password" method="POST" class="form-horizontal">
            <div class="control-group">
              <span class="control-label"><label for="newpassword">学号：</label></span>
              <div class="controls">
                 <input size="16" type="text" value="" >        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">姓名：</label></span>
              <div class="controls">
                 <input size="16" type="text" value="" >        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">联系电话：</label></span>
              <div class="controls">
                 <input size="16" type="text" value="" >        
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">学院：</label></span>
              <div class="controls">
                 <input size="16" type="text" value="" >        
              </div>
            </div>
            
             <div class="control-group">
              <span class="control-label"><label for="newpassword">咨询意向：</label></span>
              <div class="controls">
                <select>
                	<option>曲惠东</option>
                </select>
              </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="oldpassword">预约时间：</label></span>
                  <div class="controls date form_datetime" data-date="2014-11-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <input size="16" type="text" value="" readonly>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            
           
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">预约咨询师：</label></span>
              <div class="controls">
                <select>
                	<option>曲惠东</option>
                </select>
              </div>
            </div>
            
             <div class="control-group">
              <span class="control-label"><label for="oldpassword">咨询时间：</label></span>
                  <div class="controls date form_datetime" data-date="2014-11-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <input size="16" type="text" value="" readonly>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                  </div>
            </div>
            
            <div class="control-group">
              <span class="control-label"><label for="newpassword">咨询咨询师：</label></span>
              <div class="controls">
                <select>
                	<option>曲惠东</option>
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

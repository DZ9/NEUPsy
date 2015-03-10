$(function(){
	var $stuNum = $("#stuNum")
	var $stuName = $("#stuName");
	var $college = $("#college");
	var $password = $("#password");
	var $repassword = $("#rePassword");
	var $grade = $("#grade");
	var $sex = $("#sex");
	var $birthday = $("#birthday");
	var $interest = $("#interest");
	var $nation = $("#nation");
	var $hometown = $("#hometown");
	
	$("#sub").click(function(){
		if($stuNum.val().length == 0 
				|| $password.val().length == 0 
				|| $repassword.val().length == 0
				|| $stuName.val().length == 0
				|| $college.val().length == 0
				|| $grade.val().length == 0
				|| $sex.val().length == 0
				|| $birthday.val().length == 0
				|| $interest.val().length == 0
				|| $nation.val().length == 0
				|| $hometown.val().length == 0) {
			alert("请填写完整信息");
			return false;
		}
		
		if($password.val() != $repassword.val()) {
			alert("两次输入密码不一致！");
			return false;
		}
	})
	
	$username.blur(function(){
		if($username.val().length == 0 ) {
			$("#uerror").show();
		}else {
			$("#uerror").hide();
		}
	})
	
	$email.blur(function(){
		if($email.val().length == 0 ) {
			$("#eerror").show();
		}else {
			$("#eerror").hide();
		}
	})
	
	$password.blur(function(){
		if($password.val().length == 0 ) {
			$("#perror").show();
		}else {
			$("#perror").hide();
		}
	})
	
	$repassword.blur(function(){
		if($repassword.val().length == 0 ) {
			$("#rperror").show();
		}else {
			$("#rperror").hide();
		}
	})
})
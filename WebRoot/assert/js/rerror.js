$(function(){
//	$('body').oneTime('1000',function(){
//		window.location.href="servlet/ShoppingCart";
//	})
	
	var time = 2000; 
	var interval; //调度器对象。
	function run(){
	  interval = setInterval(fun,time);
	}
	function fun(){
	  window.location.href="view/user/register.jsp";
	}
	
	run();
})
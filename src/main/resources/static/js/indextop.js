$(function(){
	$("input[type='button']").click(function(){
		if(confirm("确定要退出吗？")){
			$("#exitform").submit();
			if (top.location !== self.location) { 
			    top.location = "/wxy/exit";//跳出框架，并回到首页 
			} 
		}
	});
});
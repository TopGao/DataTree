$(function($){
	$("input[name='username']").keydown(function(){
		$(".usernamemessage").text("");
	});
	$("input[name='username']").blur(function(){
		var a=$(this).val();
		if(a==null||a==""){
			if($(".usernamemessage").hasClass("red")){
				$($(".usernamemessage")).removeClass("blue");
			}else{
				$($(".usernamemessage")).removeClass("blue");
				$($(".usernamemessage")).addClass("red");
			}
			$(".usernamemessage").text("用户名不能为空");
		}
	});
	$("input[name='password']").keydown(function(){
		$(".passwordmessage").text("");
	});
	$("input[name='password']").blur(function(){
		var a=$(this).val();
		if(a==null||a==""){
			$(".passwordmessage").text("密码不能为空");
		}
	});
	$("input[name='password1']").keydown(function(){
		$(".passwordmessage1").text("");
	});
	$("input[name='password1']").blur(function(){
		var a=$(this).val();
		var b=$("input[name='password']").val();
		if(a!=b){
			$(".passwordmessage1").text("两次密码不一致");
		}
	});
	$("input[value='立即注册']").click(function(){
		var a=$("input[name='password']").val();
		var b=$("input[name='password1']").val();
		var c=$("input[name='username']").val();
		if(a==b){
			$(".passwordmessage1").text("");
		}
		if(c!=null&&c!=""&&a==b&&$(".usernamemessage").text()!="该用户名已被注册"){
			$("#registerForm").submit();
		}
	});
});
function test() {
	var a=$("input[name='username']").val();
	if(a!=null&&a!=""){
		$.ajax({
			type : "post",
			url : "/demo/userTest",
			dataType : "json",
			data : {
				"username" : a
			},
			error : function(e) {
				$.alert({
					message : "Error!",
					// 按钮的文字
					btnText : "OK",
					// 点击按钮后的事件
					btnOkClick : function() {
					}
				});
			},
			success : function(e) {
				if(e==0){
					if($(".usernamemessage").hasClass("blue")){
						$(".usernamemessage").removeClass("red");
					}else{
						$(".usernamemessage").removeClass("red");
						$(".usernamemessage").addClass("blue");
					}
					$(".usernamemessage").text("该用户名可用");
				}else{
					if($(".usernamemessage").hasClass("red")){
						$(".usernamemessage").removeClass("blue");
					}else{
						$(".usernamemessage").removeClass("blue");
						$(".usernamemessage").addClass("red");
					}
					$(".usernamemessage").text("该用户名已被注册");
				}
			}
		});
	}
}
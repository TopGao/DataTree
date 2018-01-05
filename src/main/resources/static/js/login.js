$(function($){
	$(".tishi").stop().animate({
		left:"120px"
	},500);
	$("input[name='account']").blur(function(){
		var a=$("input[name='account']").val();
		if(a==null||a==""){
			$(this).removeClass("black");
			$(this).addClass("red");
			$(this).val("用户名不能为空");
		}
	});
	$("input[name='account']").focus(function(){
		if($(this).hasClass("red")){
			$(this).removeClass("red");
			$(this).addClass("black");
		}
		var a=$(this).val();
		if(a!=null||a!=""){
			$(this).val("");
		}
	});
	$("input[name='password']").blur(function(){
		var a=$("input[name='password']").val();
		if(a==null||a==""){
			$(this).removeClass("black");
			$(this).addClass("red");
			$(this).val("密码不能为空");
		}
	});
	$("input[name='password']").focus(function(){
		if($(this).hasClass("red")){
			$(this).removeClass("red");
			$(this).addClass("black");
		}
		var a=$(this).val();
		if(a!=null||a!=""){
			$(this).val("");
		}
	});
	$("input[name='account']").click(function(){
		$(".tishi").text("");
	});
	$("input[name='login']").click(function(){
		var a=$("input[name='account']").val();
		var b=$("input[name='password']").val();
		var num=0;
		/*var a1=$("input[name='account1']").val();
		var b1=$("input[name='password1']").val();
		alert(b1);
		if(a1!=null&&b1!=null){
			if(a1==null||a1==""||a1=="用户名不能为空"){
				num++;
				$("input[name='account1']").removeClass("black");
				$("input[name='account1']").addClass("red");
				$("input[name='account1']").val("用户名不能为空");
			}
			if(b1==null||b1==""||b1=="密码不能为空"){
				num++;
				$("input[name='password1']").removeClass("black");
				$("input[name='password1']").addClass("red");
				$("input[name='password1']").val("密码不能为空");
			}
		}else{
			
		}*/
		if(a==null||a==""||a=="用户名不能为空"){
			num++;
			$("input[name='account']").removeClass("black");
			$("input[name='account']").addClass("red");
			$("input[name='account']").val("用户名不能为空");
		}
		if(b==null||b==""||b=="密码不能为空"){
			num++;
			$("input[name='password']").removeClass("black");
			$("input[name='password']").addClass("red");
			$("input[name='password']").val("密码不能为空");
		}
		if(num==0){
			$("#loginform").submit();
		}
	});
});
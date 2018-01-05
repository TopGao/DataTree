
<!DOCTYPE html>
<html lang="en">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>Jira WebTools</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/login/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/login/camera.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/login/bootstrap-responsive.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/login/matrix-login.css" />
<link href="${pageContext.request.contextPath}/styles/font-awesome.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/jquery-1.5.1.min.js"></script>

</head>
<body>

	<div
		style="width: 100%; text-align: center; margin: 0 auto; position: absolute;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm" id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<!-- <img src="../images/login/logo.png" alt="Logo" /> -->
						<i class="icon-leaf"></i><span style="font-family:"arialnarrow"">&nbsp;&nbsp;Jira
							Web Tools</span>
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg"> <i><img height="37"
									src="${pageContext.request.contextPath}/images/login/user.png" /></i>
							</span><input type="text" name="loginname" id="loginname" value=""
								placeholder="Please Enter userName" onblur="checkAccount()" /><br />
							<div id="1" style="display: inline;"></div>
						</div>

					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly"> <i><img height="37"
									src="${pageContext.request.contextPath}/images/login/suo.png" /></i>
							</span><input type="password" name="password" id="password"
								placeholder="Please Enter password" value=""
								oninput="checkPassword()" /><br />
							<div id="2" style="display: inline;"></div>
						</div>
					</div>
				</div>
				<div style="float: right; padding-right: 10%;">
					<div style="float: left; margin-top: 3px; margin-right: 2px;">
						<font color="white">Remember the password</font>
					</div>
					<div style="float: left;">
						<input name="form-field-checkbox" id="saveid" type="checkbox"
							onclick="savePaw();" style="padding-top: 0px;" />
					</div>
				</div>
				<div class="form-actions">
					<div style="width: 86%; padding-left: 8%;">
						<span class="pull-right"><a href="javascript:quxiao();"
							class="btn btn-success" onclick="myRest()">Reset</a></span> <span class="pull-right"><a
							id="jump" class="flip-link btn btn-info" id="to-recover"
							onclick="checkForm()">Login</a></span>

					</div>
				</div>

			</form>


			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright ©
							SDC Jira Support Team </span></font>
				</div>
			</div>
		</div>
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<div
				data-src="${pageContext.request.contextPath}/script/login/images/banner_slide_01.jpg"></div>
			<div
				data-src="${pageContext.request.contextPath}/script/login/images/banner_slide_02.jpg"></div>
			<div
				data-src="${pageContext.request.contextPath}/script/login/images/banner_slide_03.jpg"></div>
		</div>
		<!-- #camera_wrap_3 -->
	</div>





	<script type="text/javascript">
	 	//客户端校验
	 	function myRest() {
	 		$("#loginname").val("");
	 		$("#password").val("");
	 	}
	 	
		function check() {

			if ($("#loginname").val() == "") {

				$("#loginname").tips({
					side : 2,
					msg : 'Enter userName',
					bg : '#AE81FF',
					time : 3
				});

				$("#loginname").focus();
				
			}else if ($("#loginname").val() != "cdcadmin") {

				$("#loginname").tips({
					side : 2,
					msg : 'Wrong account',
					bg : '#AE81FF',
					time : 3
				});

				$("#loginname").focus();
			
			} 
			else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}

			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : 'Enter password',
					bg : '#AE81FF',
					time : 3
				});

				$("#password").focus();
				return false;			
			}else if ($("#password").val() != "asb#1234" ) {
			$("#password").tips({
				side : 2,
				msg : 'Wrong password',
				bg : '#AE81FF',
				time : 3
			});
			$("#password").focus();
			return false;	
			}
			if ($("#loginname").val() == "cdcadmin" && $("#password").val() == "asb#1234") {
 
			return true;
		}
			
	    return false;
	}

		function checkForm() {
			if (check()) {
				location.href = "${pageContext.request.contextPath}/cdc/homePage"
				return true;
			}
			return false;
		} 
		
	</script>

	<script src="${pageContext.request.contextPath}/bootstrap/fhm/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.js"></script>
	<script src="${pageContext.request.contextPath}/script/login/jquery.easing.1.3.js"></script>
	<script src="${pageContext.request.contextPath}/script/login/camera.min.js"></script>
	<script src="${pageContext.request.contextPath}/script/login/templatemo_script.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/login/jquery.tips.js"></script>

</body>

</html>

<!DOCTYPE html>
<html lang="en">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<title>Jira Web Tools</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="apple-touch-icon" href="../images/icons/favicon.png">
<!--Loading bootstrap css-->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/jquery-ui-1.10.4.custom.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/animate.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/style-responsive.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/styles/pace.css">

</head>

<body>
	<div>

		<!--BEGIN TOPBAR-->
		<div id="header-topbar-option-demo" class="page-header-topbar">
			<nav id="topbar" role="navigation" style="margin-bottom: 0;"
				data-step="3" class="navbar navbar-default navbar-static-top">
				<div class="navbar-header">
					<button type="button" data-toggle="collapse"
						data-target=".sidebar-collapse" class="navbar-toggle">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>
					<a id="logo" href="#" class="navbar-brand"><span
						class="fa fa-rocket"></span><span class="logo-text">Jira
							WebTools</span><span style="display: none" class="logo-text-icon">µ</span></a>
				</div>
				<div class="topbar-main">
					<a id="menu-toggle" href="#" class="hidden-xs"><i
						class="fa fa-bars"></i></a>

					<ul class="nav navbar navbar-top-links navbar-right mbn">

						<li class="dropdown topbar-user"><a data-hover="dropdown"
							href="#" class="dropdown-toggle">&nbsp;
							<i class="fa fa-twitter"></i>
							<span
								class="hidden-xs">cdcJira</span>&nbsp;<span class="caret"></span></a>
							<ul class="dropdown-menu dropdown-user pull-right">
								<!-- <li><a href="#"><i class="fa fa-user"></i>My Profile</a></li>
								<li><a href="#"><i class="fa fa-calendar"></i>My
										Calendar</a></li>
								<li><a href="#"><i class="fa fa-envelope"></i>My Inbox<span
										class="badge badge-danger">3</span></a></li>
								<li><a href="#"><i class="fa fa-tasks"></i>My Tasks<span
										class="badge badge-success">7</span></a></li>
								<li class="divider"></li>
								<li><a href="#"><i class="fa fa-lock"></i>Lock Screen</a></li> -->
								<li><a href="${pageContext.request.contextPath}/cdc/myTest"><i class="fa fa-key"></i>Log
										Out</a></li>
							</ul></li>

					</ul>
				</div>
			</nav>
			
		</div>
		<!--END TOPBAR-->
		<div id="wrapper">
			<!--BEGIN SIDEBAR MENU-->
			<nav id="sidebar" role="navigation" data-step="2"
				data-intro="Template has &lt;b&gt;many navigation styles&lt;/b&gt;"
				data-position="right" class="navbar-default navbar-static-side">
				<div class="sidebar-collapse menu-scroll">
					<ul id="side-menu" class="nav">
						<div class="clearfix"></div>
						<li class="active"><a href="${pageContext.request.contextPath}/cdc/homePage"><i
								class="fa fa-database">
									<div class="icon-bg bg-blue"></div>
							</i><span class="menu-title">Add Users To Jira</span></a></li>
							<li><a href="${pageContext.request.contextPath}/cdc/addToGroupPage"> <i class="fa fa-sitemap fa-fw"></i> <span
								class="menu-title">Add Users Into Group</span>
						</a></li>
					</ul>
				</div>
			</nav>

			<div id="page-wrapper">
				<!--BEGIN TITLE & BREADCRUMB PAGE-->
				<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
					<div class="page-header pull-left">
						<div class="page-title">Add Users To Jira</div>
					</div>

					<div class="clearfix"></div>
				</div>
				<!--END TITLE & BREADCRUMB PAGE-->
				<!--BEGIN CONTENT-->
				<div class="page-content">
					<div id="tab-general">
						<div class="row mbl">
							<div class="col-lg-12">

								<div class="col-md-12">
									<div id="area-chart-spline"
										style="width: 100%; height: 300px; display: none;"></div>
								</div>

							</div>

							<div class="col-lg-12">
								<div class="row">
									<div class="col-lg-6">
										<div class="panel panel-grey">
											<div class="panel-heading">Add User</div>
											<div class="panel-body pan">
											
												<form name="myForm" action="${pageContext.request.contextPath}/cdc/checkInfo" method="post" onsubmit="return checkForm()">
													<div class="form-body pal">


														<div class="form-group">
															<label for="inputMessage" class="control-label" >
																Please enter user's CSL/ Email/ FullName/ UPI</label>
															<textarea rows="8" class="form-control" id="addInfo" name="addInfo"></textarea>
														</div>

													</div>
													<div class="form-actions text-right pal">
														<button type="submit" class="btn btn-primary" onclick="checkForm()">
															Submit </button>
													</div>
												</form>
												<br>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="panel panel-grey">
											<div class="panel-heading">Result</div>
											<div class="panel-body pan">

												<div class="form-body pal">

													<div class="form-group">
														<label for="inputMessage" class="control-label">
															The result of the add users</label>
														<textarea rows="11" class="form-control" id="resultInfo" name="resultInfo"  readonly="readonly">${sessionScope.totalNum } 
${sessionScope.successNum }
${sessionScope.failedTotalNum }
<c:choose><c:when test="${not empty sessionScope.failedList }"><c:forEach items="${failedList}" var="everyfailednum">
${everyfailednum}</c:forEach></c:when></c:choose>
<c:choose><c:when test="${not empty sessionScope.noLdapInfo }"><c:forEach items="${noLdapInfo}" var="everynoLdapInfo">
${everynoLdapInfo}</c:forEach></c:when></c:choose>
														</textarea>
													</div>

												</div>
												

											</div>
										</div>
									</div>
								</div>

							</div>

						</div>
					</div>
				</div>
				<!--END CONTENT-->
				<!--BEGIN FOOTER-->

				<!--END FOOTER-->
			</div>
			<!--END PAGE WRAPPER-->
		</div>
	</div>
	
	
	<script type="text/javascript">
	
	function checkForm() {
		var checkInfo =  document.myForm.addInfo.value;
		if (checkInfo !="") {
			//alert("Start check!");
			//location.href = "${pageContext.request.contextPath}/cdc/checkInfo";
			return true;
		}
		else{
			alert("You have not entered any data yet!");
			return false;
		}
	}
	</script>
	
	<script src="${pageContext.request.contextPath}/script/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/script/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/script/jquery-ui.js"></script>
	<script src="${pageContext.request.contextPath}/script/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/script/bootstrap-hover-dropdown.js"></script>
	<script src="${pageContext.request.contextPath}/script/jquery.metisMenu.js"></script>
	<script src="${pageContext.request.contextPath}/script/jquery.slimscroll.js"></script>
	<script src="${pageContext.request.contextPath}/script/icheck.min.js"></script>
	<script src="${pageContext.request.contextPath}/script/jquery.menu.js"></script>
	<script src="${pageContext.request.contextPath}/script/responsive-tabs.js"></script>
	<!--LOADING SCRIPTS FOR CHARTS-->
	<script src="../script/drilldown.js"></script>
	<script src="../script/exporting.js"></script>

	<!--CORE JAVASCRIPT-->
	<script src="../script/main.js"></script>

</body>

</html>
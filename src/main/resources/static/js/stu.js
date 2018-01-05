$(function($) {
	$("#rback").click(function() {
		$("#gradename option[value='请选择年级']").prop("selected",true);
		$("#classname").empty();
		$("#classname").append("<option value='请选择班级'>--请选择班级--</option>");
		$("#stustate option[value='0']").prop("selected",true);
		$("input[name='sno']").val("");
		$("input[name='place']").val("");
		$("#time1").val("");
		$("#time2").val("");
		$("#time3").val("");
	});
	$("#add1").click(function() {
		$(".add").stop().animate({
			"top" : "40px"
		},200,function(){
			$(".add").css("display", "block");
		});
		$(".mask1").css("display", "block");
		$("#agradename option[value='请选择年级']").prop("selected",true);
		$("#aclassname").empty();
		$("#aclassname").append("<option value='请选择班级'>--请选择班级--</option>");
		/*
		 * $(".showStatement").css("display","none");
		 * $(".page").css("display","none");
		 */
	});
	$("#cancle1").click(function() {
		$(".add").stop().animate({
			"top" : "0px"
		},200,function(){
			$(".add").css("display", "none");
		});
		$(".mask1").css("display", "none");
		$(".agradenamemessage").text("");
		$(".aclassnamemessage").text("");
		$(".asnomessage").text("");
		$(".anamemessage").text("");
		$(".aphone").text("");
		$(".aplace").text("");
		$(".atime").text("");
	});
	$("#cancle2").click(function() {
		$(".update").stop().animate({
			"top" : "0px"
		},200,function(){
			$(".update").css("display","none");
			$(".mask").css("display", "none");
		});
		$(".unamemessage").text("");
		$(".uphonemessage").text("");
		$(".uplacemessage").text("");
		$("usexmessage").text("");
	});
	/* 添加js控制 */

});
function search(pnum) {
	var a = $("#gradename").val();
	var b = $("#classname").val();
	$("#gradename").change(function() {
		a = $(this).children('option:selected').val();
	});
	$("#classname").change(function() {
		b = $(this).children('option:selected').val();
	});
	var stustate=$("#stustate").val();
	$("#stustate").change(function() {
		stustate = $(this).children('option:selected').val();
	});
	var sno = $("input[name='sno']").val().trim();
	var place = $("input[name='place']").val().trim();
	var beginTime=$("#time1").val().trim();
	var begintime2=$("#time3").val().trim();
	var endTime=$("#time2").val().trim();
	var flage = true;
	if(b==null){
		b="wxy";
	}
	if (sno != "" && sno != null) {
		if (isNaN(sno)) {
			flage = false;
			$.alert({
				message : "学号必须是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});
		}
	}
	if (flage == true) {
		$.ajax({
					type : "post",
					url : "/wxy/table",
					dataType : "json",
					data : {
						"gradename" : a,
						"classname" : b,
						"sno" : sno,
						"place" : place,
						"page" : pnum,
						"begin":beginTime,
						"end":endTime,
						"state":stustate,
						"begin1":begintime2
					},
					error : function(e) {
						$.alert({
							message : "错误!",
							// 按钮的文字
							btnText : "确定",
							// 点击按钮后的事件
							btnOkClick : function() {
							}
						});
					},
					success : function(e) {
						/* alert(e); */
						if (e == null || e == "") {
							$("#message").html("");
							$("#body").html("");
							$(".page").html("");
							$.alert({
								message : "没有记录",
								// 按钮的文字
								btnText : "确定",
								// 点击按钮后的事件
								btnOkClick : function() {
								}
							});
							$(".record").html("没有相应条件的记录..........");
						} else {
							$(".record").html("");
							var list = e;
							$("#message").html("");
							$("#body").html("");
							$(".page").html("");
							var body = "<tr>"+ "<th>序号</th>" + "<th>年级</th>" + "<th>班级</th>"
									+ "<th>学号</th>" + "<th>姓名</th>"
									+ "<th>性别</th>" + "<th>电话</th>"
									+ "<th>家庭住址</th>" + "<th>入学时间</th>"+ "<th>离学时间</th>"
									+ "<th></th></tr>";
							$("#body").append(body);
							var m = "";
							for (var i = 0; i < list.length; i++) {
								if(list[i].stuState==1){
									m = "<tr><td>"+(i+1)+ "</td>" +
										"<td>"+ list[i].gName+ "</td>" +
										"<td>"+ list[i].cName
										+ "</td><td>"
										+ list[i].sNo
										+ "</td><td>"
										+ list[i].sName
										+ "</td><td>"
										+ list[i].sexString
										+ "</td><td>"
										+ list[i].sPhone
										+ "</td><td>"
										+ list[i].place
										+ "</td>"
										+ "<td>"
										+ list[i].time
										+ "</td>"
										+ "<td>"
										+ list[i].timeLeave
										+ "</td>"
										+ "<td><a href='javascriot:void(0)' onclick='d("
										+ list[i].sId
										+ ")'>删除</a><a href='javascriot:void(0)' onclick='u("
										+ list[i].sId + ")'>更新</a>"
										+ "</td></tr>";
								}else{
									m = "<tr>" 
										+"<td>"+(i+1)+ "</td>"
										+"<td>"
										+ list[i].gName
										+ "</td><td>"
										+ list[i].cName
										+ "</td><td>"
										+ list[i].sNo
										+ "</td><td>"
										+ list[i].sName
										+ "</td><td>"
										+ list[i].sexString
										+ "</td><td>"
										+ list[i].sPhone
										+ "</td><td>"
										+ list[i].place
										+ "</td>"
										+ "<td>"
										+ list[i].time
										+ "</td>"
										+ "<td>"
										+ list[i].timeLeave
										+ "</td>"
										+ "<td><a href='javascriot:void(0)' onclick='rb("
										+ list[i].sId
										+ ")'>恢复</a></td></tr>";
								}
								$("#message").append(m);
							}
							$("input[name='current']").val(list[0].currentPage);
							$(".M-box")
									.pagination(
											{
												pageCount : list[0].totalPage,
												totalData : list[0].totalDate,
												showData : list[0].pageCount,
												current : list[0].currentPage,
												jump : true,
												coping : true,
												homePage : '首页',
												endPage : '末页',
												prevContent : '上页',
												nextContent : '下页',
												callback : function(api) {
													var a = $("#gradename").val();
													var b = $("#classname").val();
													var sno = $("input[name='sno']").val().trim();
													var place = $("input[name='place']").val().trim();
													var stustate=$("#stustate").val();
													var beginTime=$("#time1").val().trim();
													var endTime=$("#time2").val().trim();
													var begintime2=$("#time3").val().trim();
													if(b==null){
														b="wxy";
													}
													$("input[name='current']").val(api.getCurrent());
													var data = {
														page : api.getCurrent(),
														"gradename" : a,
														"classname" : b,
														"sno" : sno,
														"place" : place,
														"begin":beginTime,
														"end":endTime,
														"state":stustate,
														"begin1":begintime2
													};
													$
															.getJSON(
																	'http://localhost:8080/wxy/table',
																	data,
																	function(
																			json) {
																		var list = json;
																		$(
																				"#message")
																				.html(
																						"");
																		var m = "";
																		for (var i = 0; i < list.length; i++) {
																			if(list[i].stuState==1){
																				m = "<tr>" 
																					+"<td>"+(i+1)+ "</td>"
																					+"<td>"
																					+ list[i].gName
																					+ "</td><td>"
																					+ list[i].cName
																					+ "</td><td>"
																					+ list[i].sNo
																					+ "</td><td>"
																					+ list[i].sName
																					+ "</td><td>"
																					+ list[i].sexString
																					+ "</td><td>"
																					+ list[i].sPhone
																					+ "</td><td>"
																					+ list[i].place
																					+ "</td>"
																					+ "<td>"
																					+ list[i].time
																					+ "</td>"
																					+ "<td>"
																					+ list[i].timeLeave
																					+ "</td>"
																					+ "<td><a href='javascriot:void(0)' onclick='d("
																					+ list[i].sId
																					+ ")'>删除</a><a href='javascriot:void(0)' onclick='u("
																					+ list[i].sId + ")'>更新</a>"
																					+ "</td></tr>";
																			}else{
																				m = "<tr>" 
																					+"<td>"+(i+1)+ "</td>"
																					+"<td>"
																					+ list[i].gName
																					+ "</td><td>"
																					+ list[i].cName
																					+ "</td><td>"
																					+ list[i].sNo
																					+ "</td><td>"
																					+ list[i].sName
																					+ "</td><td>"
																					+ list[i].sexString
																					+ "</td><td>"
																					+ list[i].sPhone
																					+ "</td><td>"
																					+ list[i].place
																					+ "</td>"
																					+ "<td>"
																					+ list[i].time
																					+ "</td>"
																					+ "<td>"
																					+ list[i].timeLeave
																					+ "</td>"
																					+ "<td><a href='javascriot:void(0)' onclick='rb("
																					+ list[i].sId
																					+ ")'>恢复</a></td></tr>";
																			}
																			$("#message").append(m);
																		}
																	});
												}
											});
						}
					}
				});
	}
}
function d(sid) {
	var currentpage = $("input[name='current']").val();
	/*alert("sid=" + sid + "currentpage=" + currentpage);*/
	$.ajax({
		type : "post",
		url : "/wxy/delete",
		dataType : "json",
		data : {
			"sid" : sid,
			"page" : currentpage
		},
		error : function(e) {
			$.alert({
				message : "错误!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});
		},
		success : function(e) {
			if (e == 1) {
				$.alert({
					message : "删除成功!",
					// 按钮的文字
					btnText : "确定",
					// 点击按钮后的事件
					btnOkClick : function() {
					}
				});
				var page=sumPage();
				if (page < currentpage) {
					currentpage = page;
					$("input[name='current']").val(currentpage);
				}
				search(currentpage);
			}
		}
	});
}
function rb(sid) {
	var currentpage = $("input[name='current']").val();
	/*alert("sid=" + sid + "currentpage=" + currentpage);*/
	$.ajax({
		type : "post",
		url : "/wxy/rback",
		dataType : "json",
		data : {
			"sid" : sid,
			"page" : currentpage
		},
		error : function(e) {
			$.alert({
				message : "错误!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});
		},
		success : function(e) {
			if (e == 1) {
				$.alert({
					message : "恢复成功!",
					// 按钮的文字
					btnText : "确定",
					// 点击按钮后的事件
					btnOkClick : function() {
					}
				});
				var page=sumPage();
				if (page < currentpage) {
					currentpage = page;
					$("input[name='current']").val(currentpage);
				}
				search(currentpage);
			}
		}
	});
}
var usersno;
function u(sid) {
	$(".update").stop().animate({
		"top" : "40px"
	},200,function(){
		$(".mask").css("display", "block");
		$(".update").css("display", "block");
	});
	$(".ugradename").text("");
	$(".uclassmessage").text("");
	$(".usno").text("");
	$(".unamemessage").text("");
	$(".usexmessage").text("");
	$(".uphonemessage").text("");
	$(".uplacemessage").text("");
	$(".utimemessage").text("");
	$(".utimemessage1").text("");
	var currentpage = $("input[name='current']").val();
	/* alert("sid="+sid+"currentpage="+currentpage); */
	$.ajax({
		type : "post",
		url : "/wxy/update",
		dataType : "json",
		data : {
			"sid" : sid,
			"page" : currentpage
		},
		error : function(e) {
			$.alert({
				message : "错误!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});
		},
		success : function(e) {
			if (e != null) {
				/*<div class="mask">*/
				/* $(".update").css("display","none"); */
				$("input[name='usid']").val(e.sId);
				$("#ugradename option[value='"+e.gId+"']").prop("selected",true);
				$("#ugradename").change();
				$("#uclassname option[value='"+e.cId+"']").prop("selected",true);
				$("input[name='usno']").val(e.sNo);
				usersno=e.sNo;
				$("input[name='uname']").val(e.sName);
				$("input[name='uphone']").val(e.sPhone);
				$("input[name='uplace']").val(e.place);
				$("#udateinfo").val(e.time);
				$("#udateinfo1").val(e.timeLeave);
				if (e.sex == 0) {
					$("#uman").prop("checked", true);
					$("#uw").prop("checked", false);
				} else {
					$("#uw").prop("checked", true);
					$("#uman").prop("checked", false);
				}
				
			}
		}
	});
}
function add() {
	// var sid=$("input[name='asid']").val().trim();
	var sname = $("input[name='aname']").val().trim();
	var phone = $("input[name='aphone']").val().trim();
	var place = $("input[name='aplace']").val().trim();
	var sex = $("input[name='sex']:checked").val().trim();
	var sno = $("input[name='asno']").val().trim();
	var time = $("#dateinfo").val().trim();
	var time1 = $("#dateinfo1").val().trim();
	var a = $("#agradename").val();
	var b = $("#aclassname").val();
	$("#agradename").change(function() {
		a = $(this).children('option:selected').val();
	});
	$("#aclassname").change(function() {
		b = $(this).children('option:selected').val();
	});
	var flage = true;
	if (a == "请选择年级") {
		flage = false;
		$(".agradenamemessage").text("请选择年级！");
	}
	if (b == "请选择班级") {
		flage = false;
		$(".aclassnamemessage").text("请选择班级！");
	}
	if (sname == "") {
		flage = false;
		$(".anamemessage").text("姓名不能为空！");
	}
	if (phone == "") {
		flage = false;
		$(".aphone").text("电话不能为空！");
	} else {
		if (isNaN(phone)) {
			flage = false;
			/*$.alert({
				message : "电话不是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});*/
			$(".aphone").text("电话不是数字！");
		}
	}
	if (place == "") {
		flage = false;
		$(".aplace").text("住址不能为空！");
	}
	if (sno == "") {
		flage = false;
		$(".asnomessage").text("学号不能为空！");
	} else {
		if (isNaN(sno)) {
			flage = false;
			/*$.alert({
				message : "学号不是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});*/
			$(".asnomessage").text("学号不是数字！");
		}
	}
	if (time == "") {
		flage = false;
		$(".atime").text("时间不能为空！");
	}
	if (time1 == "") {
		flage = false;
		$(".atime1").text("时间不能为空！");
	}
	if(time>=time1){
		flage=false;
		/*$.alert({
			message : "入学时间比离学时间小!",
			// 按钮的文字
			btnText : "确定",
			// 点击按钮后的事件
			btnOkClick : function() {
			}
		});*/
		$(".atime").text("入学时间比离学时间大！");
	}
	if ($(".asnomessage").text() == "该学号已被使用") {
		flage = false;
	}
	/*alert(a + " " + b + " " + sno + " " + sname + " " + sex + " " + phone + " "
			+ place + " " + time)*/
	if (flage) {
		$.ajax({
			type : "post",
			url : "/wxy/addStu",
			dataType : "json",
			data : {
				"sno" : sno,
				"sname" : sname,
				"phone" : phone,
				"place" : place,
				"sex" : sex,
				"gid" : a,
				"cid" : b,
				"time" : time,
				"time1":time1
			},
			error : function(e) {
				$(".add").stop().animate({
					"top" : "0px"
				},200,function(){
					$(".add").css("display", "none");
					$(".mask1").css("display", "none");
				});
				$.alert({
					message : "错误!",
					// 按钮的文字
					btnText : "确定",
					// 点击按钮后的事件
					btnOkClick : function() {
					}
				});
			},
			success : function(e) {
				/*alert(e);*/
				if (e == 1) {
					$(".add").stop().animate({
						"top" : "0px"
					},200,function(){
						$(".add").css("display", "none");
					});
					$(".mask1").css("display", "none");
					$("#agradename option[value='请选择年级']").prop("selected",
							true);
					$("#aclassname").empty();
					$("input[name='aname']").val("");
					$("input[name='aphone']").val("");
					$("input[name='aplace']").val("");
					$("input[name='asno']").val("");
					$("#dateinfo").val("");
					$("#dateinfo1").val("");
					$(".asnomessage").text("");
					$("#aman").prop("checked", true);
					$("#aw").prop("checked", false);
					$.alert({
						message : "添加成功!",
						// 按钮的文字
						btnText : "确定",
						// 点击按钮后的事件
						btnOkClick : function() {
						}
					});
					$("input[name='current']").val("1");
					search(1);
				} else {
					$(".add").stop().animate({
						"top" : "0px"
					},200,function(){
						$(".add").css("display", "none");
						$(".mask1").css("display", "none");
					});
					$.alert({
						message : "添加失败!",
						// 按钮的文字
						btnText : "确定",
						// 点击按钮后的事件
						btnOkClick : function() {
						}
					});
				}
			}
		});
	}
}
function update() {
	var currentpage = $("input[name='current']").val();
	var sid = $("input[name='usid']").val().trim();
	var sname = $("input[name='uname']").val().trim();
	var phone = $("input[name='uphone']").val().trim();
	var place = $("input[name='uplace']").val().trim();
	var sex = $("input[name='sex1']:checked").val().trim();
	/*var sno = $("input[name='usno']").val().trim();*/
	var time = $("#udateinfo").val().trim();
	var time1 = $("#udateinfo1").val().trim();
	var a = $("#ugradename").val();
	var b = $("#uclassname").val();
	$("#ugradename").change(function() {
		a = $(this).children('option:selected').val();
	});
	$("#uclassname").change(function() {
		b = $(this).children('option:selected').val();
	});
	var flage = true;
	if (a == "请选择年级") {
		flage = false;
		$(".agradenamemessage").text("请选择年级！");
	}
	if (b == "请选择班级") {
		flage = false;
		$(".ugradename").text("请选择班级！");
	}
	if (sname == "") {
		flage = false;
		$(".unamemessage").text("姓名不能为空！");
	}
	if (phone == "") {
		flage = false;
		$(".uphonemessage").text("电话不能为空！");
	} else {
		if (isNaN(phone)) {
			flage = false;
			/*$.alert({
				message : "电话不是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});*/
			$(".uphonemessage").text("电话不是数字！");
		}
	}
	if (place == "") {
		flage = false;
		$(".uplacemessage").text("住址不能为空！");
	}
	/*if (sno == "") {
		flage = false;
		$(".usno").text("学号不能为空！");
	} else {
		if (isNaN(sno)) {
			flage = false;
			$.alert({
				message : "学号不是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});
			$(".usno").text("学号不是数字！");
		}
	}*/
	if (time == "") {
		flage = false;
		$(".utimemessage").text("时间不能为空！");
	}
	if (time1 == "") {
		flage = false;
		$(".utimemessage1").text("时间不能为空！");
	}
	if(time>=time1){
		flage=false;
		$(".utimemessage").text("入学时间比离学时间大！");
	}
	/*if(usersno!=sno){
		usame();
		if ($(".usno").text() == "该学号已被使用") {
			flage = false;
		}
	}else{
		$(".usno").text("");
	}*/
	if (flage) {
		$.ajax({
			type : "post",
			url : "/wxy/updateStuInfo",
			dataType : "json",
			data : {
				"sid" : sid,
				"sname" : sname,
				"phone" : phone,
				"place" : place,
				"sex" : sex,
				"gid" : a,
				"cid" : b,
				"time" : time,
				"time1":time1,
			},
			error : function(e) {
				$(".update").stop().animate({
					"top" : "0px"
				},200,function(){
					$(".update").css("display","none");
					$(".mask").css("display", "none");
				});
				$.alert({
					message : "错误!",
					// 按钮的文字
					btnText : "确定",
					// 点击按钮后的事件
					btnOkClick : function() {
					}
				});
			},
			success : function(e) {
				if (e == 1) {
					$(".update").stop().animate({
						"top" : "0px"
					},200,function(){
						$(".update").css("display","none");
						$(".mask").css("display","none");
					});
					$.alert({
						message : "更新成功!",
						// 按钮的文字
						btnText : "确定",
						// 点击按钮后的事件
						btnOkClick : function() {
						}
					});
					search(currentpage);
				}
			}
		});
	}
}
function same() {
	var a = $("input[name='asno']").val();
	var flage = true;
	if (a == "") {
		$(".asnomessage").text("学号不能为空！");
		flage = false;
	} else {
		if (isNaN(a)) {
			flage = false;
			/*$.alert({
				message : "学号不是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});*/
			$(".asnomessage").text("学号不是数字！");
		}
	}
	if (flage) {
		$.ajax({
			type : "post",
			url : "/wxy/sameSno",
			dataType : "json",
			data : {
				"sno" : a
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
				if (e == 0) {
					if ($(".asnomessage").hasClass("blue")) {
						$(".asnomessage").removeClass("red");
					} else {
						$(".asnomessage").removeClass("red");
						$(".asnomessage").addClass("blue");
					}
					$(".asnomessage").text("该学号可用");
				} else {
					if ($(".asnomessage").hasClass("red")) {
						$(".asnomessage").removeClass("blue");
					} else {
						$(".asnomessage").removeClass("blue");
						$(".asnomessage").addClass("red");
					}
					$(".asnomessage").text("该学号已被使用");
				}
			}
		});
	}
}
function usame() {
	var a = $("input[name='usno']").val();
	var flage = true;
	if (a == "") {
		$(".usno").text("学号不能为空！");
		flage = false;
	} else {
		if (isNaN(a)) {
			flage = false;
			/*$.alert({
				message : "学号不是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});*/
			$(".usno").text("学号不是数字！");
		}
	}
	if (flage) {
		$.ajax({
			type : "post",
			url : "/wxy/sameSno",
			dataType : "json",
			async : false,/* 异步关闭 */
			data : {
				"sno" : a
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
				if (e == 0) {
					if ($(".usno").hasClass("blue")) {
						$(".usno").removeClass("red");
					} else {
						$(".usno").removeClass("red");
						$(".usno").addClass("blue");
					}
					$(".usno").text("该学号可用");
				} else {
					if ($(".usno").hasClass("red")) {
						$(".usno").removeClass("blue");
					} else {
						$(".usno").removeClass("blue");
						$(".usno").addClass("red");
					}
					$(".usno").text("该学号已被使用");
				}
			}
		});
	}
}
function sumPage() {
	var totalpage = 1;
	var a = $("#gradename").val();
	var b = $("#classname").val();
	var sno = $("input[name='sno']").val().trim();
	var place = $("input[name='place']").val().trim();
	var stustate=$("#stustate").val();
	var beginTime=$("#time1").val().trim();
	var endTime=$("#time2").val().trim();
	if(b==null){
		b="wxy";
	}
	$.ajax({
		type : "post",
		url : "/wxy/totalPage",
		dataType : "json",
		async:false,/*异步关闭*/
		data : {
			"gradename" : a,
			"classname" : b,
			"sno" : sno,
			"place" : place,
			"begin":beginTime,
			"end":endTime,
			"state":stustate
		},
		error : function(e) {
			$.alert({
				message : "错误!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});
		},
		success : function(e) {
			totalpage=e;
		}
	});
	return totalpage;
}
function getcname(){
	var gid = $("#gradename").val();
	$.ajax({
		type : "post",
		url : "/cg/cName",
		dataType : "json",
		async : false,/* 异步关闭 */
		data : {
			"gid":gid
		},
		error : function(e) {
			$.alert({
				message : "错误!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});
		},
		success : function(data) {
			if(data.length!=0){
				$("#classname").empty();
				$("#classname").append("<option value='请选择班级'>--请选择班级--</option>");
				var m="";
				for (var i = 0; i < data.length; i++) {
					m="<option value='"+data[i].cName+"'>"+data[i].cName+"</option>";
					$("#classname").append(m);
				}
			}else{
				$("#classname").empty();
				$("#classname").append("<option value='请选择班级'>--请选择班级--</option>");
			}
		}
	});
}
function getcname1(){
	var gid = $("#agradename").val();
	$.ajax({
		type : "post",
		url : "/cg/cName",
		dataType : "json",
		async : false,/* 异步关闭 */
		data : {
			"gid":gid
		},
		error : function(e) {
			/*$.alert({
				message : "错误!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});*/
			$(".agradenamemessage").text("数据错误!");
		},
		success : function(data) {
			if(data.length!=0){
				$("#aclassname").empty();
				$("#aclassname").append("<option value='请选择班级'>--请选择班级--</option>");
				var m="";
				for (var i = 0; i < data.length; i++) {
					m="<option value='"+data[i].cId+"'>"+data[i].cName+"</option>";
					$("#aclassname").append(m);
				}
			}else{
				$("#aclassname").empty();
				$("#aclassname").append("<option value='请选择班级'>--请选择班级--</option>");
			}
		}
	});
}
function ugetcname(){
	var gid = $("#ugradename").val();
	$.ajax({
		type : "post",
		url : "/cg/cName",
		dataType : "json",
		async : false,/* 异步关闭 */
		data : {
			"gid":gid
		},
		error : function(e) {
			/*$.alert({
				message : "错误!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});*/
			$(".ugradename").text("数据错误!");
		},
		success : function(data) {
			if(data.length!=0){
				$("#uclassname").empty();
				$("#uclassname").append("<option value='请选择班级'>--请选择班级--</option>");
				var m="";
				for (var i = 0; i < data.length; i++) {
					m="<option value='"+data[i].cId+"'>"+data[i].cName+"</option>";
					$("#uclassname").append(m);
				}
			}else{
				$("#uclassname").empty();
				$("#uclassname").append("<option value='请选择班级'>--请选择班级--</option>");
			}
		}
	});
}
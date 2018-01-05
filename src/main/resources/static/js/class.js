$(function($) {
	$("#rback").click(function() {
		$("#gradename option[value='请选择年级']").prop("selected",true);
		$("#classname").empty();
		$("#classname").append("<option value='请选择班级'>--请选择班级--</option>");
		$("#cstate option[value='0']").prop("selected",true);
	});
	$("#add1").click(function() {
		$(".add").css("display", "block");
		$(".mask1").css("display", "block");
		$("#agradename option[value='请选择年级']").prop("selected",true);
		$("input[name='aname']").val("");
		$("#aname").text("");
	});
	$("#cancle1").click(function() {
		$(".add").css("display", "none");
		$(".aname").text("");
		$(".mask1").css("display","none");
	});
	$("#cancle2").click(function() {
		$(".update").css("display", "none");
		$(".uname").text("*可修改");
		$(".mask").css("display","none");
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
	var cstate=$("#cstate").val();
	$("#cstate").change(function() {
		cstate = $(this).children('option:selected').val();
	});
	if(b==null){
		b="wxy";
	}
	$.ajax({
				type : "post",
				url : "/cg/grade",
				dataType : "json",
				data : {
					"page" : pnum,
					"cstate":cstate,
					"gid":a,
					"cname":b
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
						var body = "<tr>"+ "<th>序号</th>"+ "<th>年级</th>"+ "<th>班级</th>"+"<th>添加时间</th>" +"<th></th></tr>";
						$("#body").append(body);
						var m = "";
						for (var i = 0; i < list.length; i++) {
							if(list[i].cState==2){
								m = "<tr>"+"<td>"+(i+1)+ "</td>" +"<td>"+ list[i].gName+ "</td>"
								+"<td>"+ list[i].cName+ "</td>"
								+"<td>"+ list[i].addcTime+ "</td>"
								+ "<td><a href='javascriot:void(0)' onclick='rb("
								+ list[i].cId
								+ ")'>恢复</a></td></tr>";
							}else{
								m = "<tr>" +"<td>"+(i+1)+ "</td>" +"<td>"+ list[i].gName+ "</td>"
								+"<td>"+ list[i].cName+ "</td>" 
								+"<td>"+ list[i].addcTime+ "</td>"
								+ "<td><a href='javascriot:void(0)' onclick='d("
								+ list[i].cId
								+ ")'>删除</a><a href='javascriot:void(0)' onclick='u("
								+ list[i].cId + ")'>更新</a>" + "</td></tr>";
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
												if(b==null){
													b="wxy";
												}
												var cstate=$("#cstate").val();
												$("input[name='current']").val(
														api.getCurrent());
												var data = {
													page : api.getCurrent(),
													"cstate":cstate,
													"gid":a,
													"cname":b
												};
												$
														.getJSON(
																'http://localhost:8080/cg/grade',
																data,
																function(json) {
																	var list = json;
																	$(
																			"#message")
																			.html(
																					"");
																	var m = "";
																	for (var i = 0; i < list.length; i++) {
																		if(list[i].cState==2){
																			m = "<tr>" +"<td>"+(i+1)+ "</td>" +"<td>"+ list[i].gName+ "</td>"
																			+"<td>"+ list[i].cName+ "</td>" 
																			+"<td>"+ list[i].addcTime+ "</td>"
																			+ "<td><a href='javascriot:void(0)' onclick='rb("
																			+ list[i].cId
																			+ ")'>恢复</a></td></tr>";
																		}else{
																			m = "<tr>" +"<td>"+(i+1)+ "</td>" +"<td>"+ list[i].gName+ "</td>"
																			+"<td>"+ list[i].cName+ "</td>" 
																			+"<td>"+ list[i].addcTime+ "</td>"
																			+ "<td><a href='javascriot:void(0)' onclick='d("
																			+ list[i].cId
																			+ ")'>删除</a><a href='javascriot:void(0)' onclick='u("
																			+ list[i].cId + ")'>更新</a>" + "</td></tr>";
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

function d(cid) {
	var currentpage = $("input[name='current']").val();
	/*alert("cid=" + cid + "currentpage=" + currentpage);*/
	$.ajax({
		type : "post",
		url : "/cg/gdelete",
		dataType : "json",
		data : {
			"cid" : cid,
			"page" : currentpage,
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
				var gid=$("#gradename").val();
				if(gid!="请选择年级"){
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
							if(data!=null){
								$("#classname").empty();
								$("#classname").append("<option value='请选择班级'>--请选择班级--</option>");
								var m="";
								for (var i = 0; i < data.length; i++) {
									m="<option value='"+data[i].cName+"'>"+data[i].cName+"</option>";
									$("#classname").append(m);
								}
							}
						}
					});
				}
				var page = sumPage();
				if (page < currentpage) {
					currentpage = page;
					$("input[name='current']").val(currentpage);
				}
				search(currentpage);
			}
		}
	});
	/*var gid=$("#gradename").val();
	if(gid=="请选择年级"){
		$.alert({
			message : "请选择检索条件的年级!",
			// 按钮的文字
			btnText : "确定",
			// 点击按钮后的事件
			btnOkClick : function() {
			}
		});
	}else{
		
	}*/
}
function rb(cid){
	var currentpage = $("input[name='current']").val();
	/*alert("cid=" + cid + "currentpage=" + currentpage);*/
	$.ajax({
		type : "post",
		url : "/cg/rback",
		dataType : "json",
		data : {
			"cid" : cid,
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
				var gid=$("#gradename").val();
				if(gid!="请选择年级"){
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
							if(data!=null){
								$("#classname").empty();
								$("#classname").append("<option value='请选择班级'>--请选择班级--</option>");
								var m="";
								for (var i = 0; i < data.length; i++) {
									m="<option value='"+data[i].cName+"'>"+data[i].cName+"</option>";
									$("#classname").append(m);
								}
							}
						}
					});
				}
				search(currentpage);
			}
		}
	});
	/*var gid=$("#gradename").val();
	if(gid=="请选择年级"){
		$.alert({
			message : "请选择检索条件的年级!",
			// 按钮的文字
			btnText : "确定",
			// 点击按钮后的事件
			btnOkClick : function() {
			}
		});
	}else{
		
	}*/
}
function u(cid) {
	var currentpage = $("input[name='current']").val();
	/* alert("sid="+sid+"currentpage="+currentpage); */
	$(".update").css("display", "block");
	$(".mask").css("display","block");
	$.ajax({
		type : "post",
		url : "/cg/gupdate",
		dataType : "json",
		data : {
			"cid" : cid,
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
				/* $(".update").css("display","none"); */
				$("input[name='ucid']").val(e.cId);
				$("input[name='uname']").val(e.cName);
				$("#ugradename option[value='"+e.gId+"']").prop("selected",true);
			}
		}
	});
	/*var gid=$("#gradename").val();
	if(gid=="请选择年级"){
		$.alert({
			message : "请选择检索条件的年级!",
			// 按钮的文字
			btnText : "确定",
			// 点击按钮后的事件
			btnOkClick : function() {
			}
		});
	}else{
		
	}*/
}
function add() {
	// var sid=$("input[name='asid']").val().trim();
	$(".mask1").css("display","block");
	$(".add").css("display","block");
	var aname = $("input[name='aname']").val().trim();
	var flage=true;
	if (aname == "") {
		flage = false;
		$(".aname").text("班级名不能为空！");
	}
	if ($(".aname").text() == "该年级名称已被使用") {
		flage = false;
	}
	var gid=$("#agradename").val();
	if(gid=="请选择年级"){
		/*$.alert({
			message : "请选择年级!",
			// 按钮的文字
			btnText : "确定",
			// 点击按钮后的事件
			btnOkClick : function() {
			}
		});*/
		flage=false;
		$(".agradename").text("请选择年级");
	}
	/*alert(aname);*/
	if (flage) {
		$.ajax({
			type : "post",
			url : "/cg/addClass",
			dataType : "json",
			data : {
				"cname" : aname,
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
			success : function(e) {
				/*alert(e);*/
				$(".add").css("display", "none");
				$(".mask1").css("display","none");
				$(".aname").text("");
				if (e== 1) {
					$(".add").css("display", "none");
					$.alert({
						message : "添加成功!",
						// 按钮的文字
						btnText : "确定",
						// 点击按钮后的事件
						btnOkClick : function() {
						}
					});
					var gid=$("#gradename").val();
					if(gid!="请选择年级"){
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
								if(data!=null){
									$("#classname").empty();
									$("#classname").append("<option value='请选择班级'>--请选择班级--</option>");
									var m="";
									for (var i = 0; i < data.length; i++) {
										m="<option value='"+data[i].cName+"'>"+data[i].cName+"</option>";
										$("#classname").append(m);
									}
								}
							}
						});
					}
					/*var currentpage = sumPage();*/
					$("input[name='current']").val("1");
					search(1);
				} else {
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
	var cid = $("input[name='ucid']").val().trim();
	var cname = $("input[name='uname']").val().trim();
	var currentpage = $("input[name='current']").val();
	var gid=$("#ugradename").val();
	var flage = true;
	if (cname == "") {
		flage = false;
		$(".uname").text("用户名不能为空！");
	}
	if ($(".uname").text() == "该年级名称已被使用") {
		flage = false;
	}
	if (flage) {
		$.ajax({
			type : "post",
			url : "/cg/updateGrade",
			dataType : "json",
			data : {
				"cid" : cid,
				"cname" : cname,
				"gid":gid
			},
			error : function(e) {
				$(".update").css("display", "none");
				$(".mask").css("display","none");
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
					$(".update").css("display", "none");
					$(".mask").css("display", "none");
					$(".uname").text("*可修改");
					if ($(".uname").hasClass("red")) {
						$(".uname").removeClass("blue");
					} else {
						$(".uname").removeClass("blue");
						$(".uname").addClass("red");
					}
					$.alert({
						message : "更新成功!",
						// 按钮的文字
						btnText : "确定",
						// 点击按钮后的事件
						btnOkClick : function() {
						}
					});
					var gid=$("#gradename").val();
					if(gid!="请选择年级"){
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
								if(data!=null){
									$("#classname").empty();
									$("#classname").append("<option value='请选择班级'>--请选择班级--</option>");
									var m="";
									for (var i = 0; i < data.length; i++) {
										m="<option value='"+data[i].cName+"'>"+data[i].cName+"</option>";
										$("#classname").append(m);
									}
								}
							}
						});
					}
					search(currentpage);
				}
			}
		});
	}
}
function same() {
	var a = $("input[name='aname']").val();
	var gid=$("#agradename").val();
	var flage = true;
	if (a == "") {
		$(".aname").text("班级名不能为空！");
		flage = false;
	}
	if(gid!="请选择年级"){
		if (flage) {
			$.ajax({
				type : "post",
				url : "/cg/sameCname",
				dataType : "json",
				data : {
					"cname" : a,
					"gid":gid
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
						if ($(".aname").hasClass("blue")) {
							$(".aname").removeClass("red");
						} else {
							$(".aname").removeClass("red");
							$(".aname").addClass("blue");
						}
						$(".aname").text("该年级名称可用");
					} else {
						if ($(".aname").hasClass("red")) {
							$(".aname").removeClass("blue");
						} else {
							$(".aname").removeClass("blue");
							$(".aname").addClass("red");
						}
						$(".aname").text("该年级名称已被使用");
					}
				}
			});
		}
	}
}
function usame() {
	var a = $("input[name='uname']").val();
	var gid = $("input[name='ugid']").val();
	var flage = true;
	if (a == "") {
		$(".uname").text("年级名不能为空！");
		flage = false;
	} 
	if (flage) {
		$.ajax({
			type : "post",
			url : "/cg/sameCname",
			dataType : "json",
			data : {
				"cname" : a,
				"gid":gid
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
					if ($(".uname").hasClass("blue")) {
						$(".uname").removeClass("red");
					} else {
						$(".uname").removeClass("red");
						$(".uname").addClass("blue");
					}
					$(".uname").text("该年级名称可用");
				} else {
					if ($(".uname").hasClass("red")) {
						$(".uname").removeClass("blue");
					} else {
						$(".uname").removeClass("blue");
						$(".uname").addClass("red");
					}
					$(".uname").text("该年级名称已被使用");
				}
			}
		});
	}
}
function sumPage() {
	var totalpage = 1;
	var a = $("#gradename").val();
	var b = $("#classname").val();
	var cstate=$("#cstate").val();
	if(b==null){
		b="wxy";
	}
	$.ajax({
		type : "post",
		url : "/cg/gtotalPage",
		dataType : "json",
		async : false,/* 异步关闭 */
		data : {
			"cstate":cstate,
			"gid":a,
			"cname":b
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
			totalpage = e;
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

$(function($) {
	$("#rback").click(function() {
		$("#gradename option[value='请选择年级']").attr("selected",true);
		$("#gstate option[value='0']").attr("selected",true);
	});
	$("#add1").click(function() {
		$(".add").css("display", "block");
		/*
		 * $(".showStatement").css("display","none");
		 * $(".page").css("display","none");
		 */
	});
	$("#cancle1").click(function() {
		$(".add").css("display", "none");
		$(".agradenamemessage").text("");
		$(".aclassnamemessage").text("");
		$(".asnomessage").text("");
		$(".anamemessage").text("");
		$(".aphone").text("");
		$(".aplace").text("");
		$(".atime").text("");
	});
	$("#cancle2").click(function() {
		$(".update").css("display", "none");
		$(".unamemessage").text("*可修改");
		$(".uphonemessage").text("*可修改");
		$(".uplacemessage").text("*可修改");
		$("usexmessage").text("*可修改");
	});
	/* 添加js控制 */

});

function search(pnum) {
	var a = $("#gradename").val();
	$("#gradename").change(function() {
		a = $(this).children('option:selected').val();
	});
	var gstate=$("#gstate").val();
	$("#gstate").change(function() {
		gstate = $(this).children('option:selected').val();
	});
	var flage=true;
	/*if(a=="请选择年级"){
		flage=false;
		$.alert({
			message : "请选择班级!",
			// 按钮的文字
			btnText : "确定",
			// 点击按钮后的事件
			btnOkClick : function() {
			}
		});
	}*/
	if(flage){
		$.ajax({
			type : "post",
			url : "/gc/grade",
			dataType : "json",
			data : {
				"page" : pnum,
				"gstate":gstate,
				"gname":a
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
					var body = "<tr>"+"<th>序号</th>" + "<th>年级</th>"+ "<th>添加时间</th>" + "<th></th></tr>";
					$("#body").append(body);
					var m = "";
					for (var i = 0; i < list.length; i++) {
						if(list[i].gState==1){
							m = "<tr>" 
								+"<td>"+ (i+1)+ "</td>"
								+"<td>"+ list[i].gName+ "</td>"
								+"<td>"+ list[i].addgTime+ "</td>"
								+ "<td><a href='javascriot:void(0)' onclick='d("
								+ list[i].gId
								+ ")'>删除</a><a href='javascriot:void(0)' onclick='u("
								+ list[i].gId + ")'>更新</a>" + "</td></tr>";
						}else{
							m = "<tr>" 
								+"<td>"+ (i+1)+ "</td>"
								+"<td>"+ list[i].gName+ "</td>"
								+"<td>"+ list[i].addgTime+ "</td>"
								+ "<td><a href='javascriot:void(0)' onclick='r("
								+ list[i].gId
								+ ")'>恢复</a>" + "</td></tr>";
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
											var gstate=$("#gstate").val();
											$("input[name='current']").val(
													api.getCurrent());
											var data = {
												page : api.getCurrent(),
												"gstate":gstate,
												"gname":a
											};
											$
													.getJSON(
															'http://localhost:8080/gc/grade',
															data,
															function(json) {
																var list = json;
																$(
																		"#message")
																		.html(
																				"");
																var m = "";
																for (var i = 0; i < list.length; i++) {
																	if(list[i].gState==1){
																		m = "<tr>" 
																			+"<td>"+ (i+1)+ "</td>"
																			+"<td>"+ list[i].gName+ "</td>"
																			+"<td>"+ list[i].addgTime+ "</td>"
																			+ "<td><a href='javascriot:void(0)' onclick='d("
																			+ list[i].gId
																			+ ")'>删除</a><a href='javascriot:void(0)' onclick='u("
																			+ list[i].gId + ")'>更新</a>" + "</td></tr>";
																	}else{
																		m = "<tr>" 
																			+"<td>"+ (i+1)+ "</td>"
																			+"<td>"+ list[i].gName+ "</td>"
																			+"<td>"+ list[i].addgTime+ "</td>"
																			+ "<td><a href='javascriot:void(0)' onclick='r("
																			+ list[i].gId
																			+ ")'>恢复</a>" + "</td></tr>";
																	}
																	$(
																			"#message")
																			.append(
																					m);
																}
															});
										}
									});
				}
			}
		});
	}
}
function d(gid) {
	var currentpage = $("input[name='current']").val();
/*	alert("gid=" + gid + "currentpage=" + currentpage);*/
	$.ajax({
		type : "post",
		url : "/gc/gdelete",
		dataType : "json",
		data : {
			"gid" : gid,
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
				$.ajax({
					type : "post",
					url : "/gc/gradeName",
					dataType : "json",
					async : false,/* 异步关闭 */
					data : {
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
						$("#gradename").empty();
						$("#gradename").append("<option value='请选择年级'>--请选择年级--</option>");
						var m="";
						for (var i = 0; i < data.length; i++) {
							m="<option value='"+data[i].gName+"'>"+data[i].gName+"</option>";
							$("#gradename").append(m);
						}
					}
				});
				var page = sumPage();
				if (page < currentpage) {
					currentpage = page;
					$("input[name='current']").val(currentpage);
				}
				search(currentpage);
			}
		}
	});
}
function r(gid) {
	var currentpage = $("input[name='current']").val();
/*	alert("gid=" + gid + "currentpage=" + currentpage);*/
	$.ajax({
		type : "post",
		url : "/gc/gr",
		dataType : "json",
		data : {
			"gid" : gid,
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
				$.ajax({
					type : "post",
					url : "/gc/gradeName",
					dataType : "json",
					async : false,/* 异步关闭 */
					data : {
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
						$("#gradename").empty();
						$("#gradename").append("<option value='请选择年级'>--请选择年级--</option>");
						var m="";
						for (var i = 0; i < data.length; i++) {
							m="<option value='"+data[i].gName+"'>"+data[i].gName+"</option>";
							$("#gradename").append(m);
						}
					}
				});
				search(currentpage);
			}
		}
	});
}
function u(gid) {
	$(".update").css("display", "block");
	var currentpage = $("input[name='current']").val();
	/* alert("sid="+sid+"currentpage="+currentpage); */
	$.ajax({
		type : "post",
		url : "/gc/gupdate",
		dataType : "json",
		data : {
			"gid" : gid,
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
				$("input[name='ugid']").val(e.gId);
				$("input[name='uname']").val(e.gName);
			}
		}
	});
}
function add() {
	// var sid=$("input[name='asid']").val().trim();
	var aname = $("input[name='aname']").val().trim();
	var flage=true;
	if (aname == "") {
		flage = false;
		$(".aname").text("年级不能不能为空！");
	}
	if ($(".aname").text() == "该年级名称已被使用") {
		flage = false;
	}
	/*alert(aname);*/
	if (flage) {
		$.ajax({
			type : "post",
			url : "/gc/addGrade",
			dataType : "json",
			data : {
				"gname" : aname
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
					/*var currentpage = sumPage();*/
					$("input[name='current']").val("1");
					$.ajax({
						type : "post",
						url : "/gc/gradeName",
						dataType : "json",
						async : false,/* 异步关闭 */
						data : {
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
							$("#gradename").empty();
							$("#gradename").append("<option value='请选择年级'>--请选择年级--</option>");
							var m="";
							for (var i = 0; i < data.length; i++) {
								m="<option value='"+data[i].gName+"'>"+data[i].gName+"</option>";
								$("#gradename").append(m);
							}
						}
					});
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
	var gid = $("input[name='ugid']").val().trim();
	var gname = $("input[name='uname']").val().trim();
	var currentpage = $("input[name='current']").val();
	var flage = true;
	if (gname == "") {
		flage = false;
		$(".uname").text("用户名不能为空！");
	}
	if ($(".uname").text() == "该年级名称已被使用") {
		flage = false;
	}
	if (flage) {
		$.ajax({
			type : "post",
			url : "/gc/updateGrade",
			dataType : "json",
			data : {
				"gid" : gid,
				"gname" : gname
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
					$(".update").css("display", "none");
					$.alert({
						message : "更新成功!",
						// 按钮的文字
						btnText : "确定",
						// 点击按钮后的事件
						btnOkClick : function() {
						}
					});
					$.ajax({
						type : "post",
						url : "/gc/gradeName",
						dataType : "json",
						async : false,/* 异步关闭 */
						data : {
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
							$("#gradename").empty();
							$("#gradename").append("<option value='请选择年级'>--请选择年级--</option>");
							var m="";
							for (var i = 0; i < data.length; i++) {
								m="<option value='"+data[i].gName+"'>"+data[i].gName+"</option>";
								$("#gradename").append(m);
							}
						}
					});
					search(currentpage);
				}
			}
		});
	}
}
function same() {
	var a = $("input[name='aname']").val();
	var flage = true;
	if (a == "") {
		$(".aname").text("年级名不能为空！");
		flage = false;
	} 
	if (flage) {
		$.ajax({
			type : "post",
			url : "/gc/sameGname",
			dataType : "json",
			data : {
				"gname" : a
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
function usame() {
	var a = $("input[name='uname']").val();
	var flage = true;
	if (a == "") {
		$(".uname").text("年级名不能为空！");
		flage = false;
	} 
	if (flage) {
		$.ajax({
			type : "post",
			url : "/gc/sameGname",
			dataType : "json",
			data : {
				"gname" : a
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
	var gstate=$("#gstate").val();
	$.ajax({
		type : "post",
		url : "/gc/gtotalPage",
		dataType : "json",
		async : false,/* 异步关闭 */
		data : {
			"gstate":gstate,
			"gname":a
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
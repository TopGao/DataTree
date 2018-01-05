$(function($){
	$("#rback").click(function() {
		$("#gradename option[value='请选择年级']").prop("selected",true);
		$("#classname").empty();
		$("#classname").append("<option value='请选择班级'>--请选择班级--</option>");
		$("#scstate option[value='0']").prop("selected",true);
		$("#subjectname option[value='请选择学科']").prop("selected",true);
//		$("#addsubjectname option[value='请选择学科']").prop("selected",true);
		$("input[name='sno']").val("");
		$("#grade1").val("");
		$("#grade").val("");
		/*$("#addsubjectname").change();
		searchno(1);*/
	});
	/*$("#add1").click(function(){
		$("#agradename option[value='请选择年级']").attr("selected",true);
		$(".add").css("display","block");
		$(".showStatement").css("display","none");
		$(".page").css("display","none");
	});*/
	$("#cancle1").click(function(){
		$(".add").stop().animate({
			"top" : "0px"
		},200,function(){
			$(".add").css("display","none");
		});
		$(".mask1").css("display","none");
		$(".agradenamemessage").text("");
		$(".aclassnamemessage").text("");
		$(".asnomessage").text("");
		$(".anamemessage").text("");
		$(".aphone").text("");
		$(".aplace").text("");
		$(".atime").text("");
	});
	$("#cancle2").click(function(){
		$(".update").stop().animate({
			"top" : "0px"
		},200,function(){
			$(".update").css("display","none");
		});
		$(".mask").css("display","none");
		$(".unamemessage").text("*可修改");
		$(".uphonemessage").text("*可修改");
		$(".uplacemessage").text("*可修改");
		$("usexmessage").text("*可修改");
	});
	/*添加js控制*/
	
});

function search(pnum) {
	$(".choseSubject").css("display","none");
//	$("#subjectname option[value='请选择学科']").prop("selected",true);
	$("#subjectname").prop("disabled", false);
	$("#grade").prop("disabled", false);
	$("#grade1").prop("disabled", false);
	var a = $("#gradename").val();
	var b = $("#classname").val();
	var c=$("#subjectname").val();
	var state=$("#scstate").val();
	$("#gradename").change(function() {
		a = $(this).children('option:selected').val();
	});
	$("#classname").change(function() {
		b = $(this).children('option:selected').val();
	});
	$("#subjectname").change(function() {
		c = $(this).children('option:selected').val();
	});
	$("#scstate").change(function() {
		state = $(this).children('option:selected').val();
	});
	if(b==null){
		b="wxy";
	}
	var sno = $("input[name='sno']").val().trim();
	var minscore=$("input[name='score']").val().trim();
	var maxscore=$("input[name='score1']").val().trim();
	var flage = true;
	if(minscore!=""&&maxscore!=""){
		if(Number(minscore)>=Number(maxscore)){
			flage=false;
			$.alert({
				message : "分数最小值要比最大值小!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});
		}
	}
	if(sno!=""&&sno!=null){
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
	if(minscore!=""&&minscore!=null){
		if (isNaN(minscore)) {
			flage = false;
			$.alert({
				message : "最小成绩有误,成绩必须是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});
		}else{
			var num=Number(minscore);
			if(num<0||num>100){
				flage = false;
				$.alert({
					message : "成绩范围在0~100之间!",
					// 按钮的文字
					btnText : "确定",
					// 点击按钮后的事件
					btnOkClick : function() {
					}
				});
			}
		}
	}
	if(maxscore!=""&&maxscore!=null){
		if (isNaN(maxscore)) {
			flage = false;
			$.alert({
				message : "最大成绩有误,成绩必须是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});
		}else{
			var num=Number(maxscore);
			if(num<0||num>100){
				flage = false;
				$.alert({
					message : "成绩范围在0~100之间!",
					// 按钮的文字
					btnText : "确定",
					// 点击按钮后的事件
					btnOkClick : function() {
					}
				});
			}
		}
	}
	if (flage == true) {
		$.ajax({
			type : "post",
			url : "/w/score",
			dataType : "json",
			data : {
				"gradename" : a,
				"classname" : b,
				"subjectname":c,
				"sno" : sno,
				"minscore":minscore,
				"page":pnum,
				"maxscore":maxscore,
				"state":state
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
				if(e==null||e==""){
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
				}else{
					$(".record").html("");
					var list=e;
					$("#message").html("");
					$("#body").html("");
					$(".page").html("");
					var body="<tr>" +
					"<th>序号</th>" +
							"<th>年级</th>" +
							"<th>班级</th>" +
							"<th>学号</th>" +
							"<th>姓名</th>" +
							"<th>学科</th>" +
							"<th>分数</th>" +
							"<th>录入时间</th>" +
							"<th>学期</th>" +
							"<th></th></tr>";
					$("#body").append(body);
					var m ="";
					for (var i = 0; i < list.length; i++) {
						if(list[i].scState==2){
							m= "<tr>" 
								+"<td>"+(i+1)+ "</td>" 
								+"<td>" + list[i].gName + "</td><td>"
							+ list[i].cName + "</td><td>"
							+ list[i].sNo + "</td><td>"
							+ list[i].sName + "</td><td>"
							+ list[i].subjectName + "</td>"
							+"<td>"+ list[i].marks + "</td>"
							+"<td>"+ list[i].timeMarks + "</td>"
							+"<td>第"+ list[i].turn + "学期</td>"
							+"<td><a href='javascriot:void(0)' onclick='rb("+list[i].scId + ")'>恢复</a></td></tr>";
						}else{
							m= "<tr>"
								+"<td>"+(i+1)+ "</td>" 
								+"<td>" + list[i].gName + "</td><td>"
							+ list[i].cName + "</td><td>"
							+ list[i].sNo + "</td><td>"
							+ list[i].sName + "</td><td>"
							+ list[i].subjectName + "</td>"
							+"<td>"+ list[i].marks + "</td>"
							+"<td>"+ list[i].timeMarks + "</td>"
							+"<td>第"+ list[i].turn + "学期</td>"
							+"<td><a href='javascriot:void(0)' onclick='d("+list[i].scId + ")'>删除</a><a href='javascriot:void(0)' onclick='u("+list[i].scId + ")'>更新</a>"
							+"</td></tr>";
						}
						$("#message").append(m);
					}
					$("input[name='current']").val(list[0].currentPage);
					$(".M-box").pagination({
					    pageCount: list[0].totalPage,
					    totalData:list[0].totalDate,
					    showData:list[0].pageCount,
					    current:list[0].currentPage,
					    jump: true,
					    coping:true,
					    homePage:'首页',
					    endPage:'末页',
					    prevContent:'上页',
					    nextContent:'下页',
					    callback:function(api){
					    	var a = $("#gradename").val();
					    	var b = $("#classname").val();
					    	var c=$("#subjectname").val();
					    	var state=$("#scstate").val();
					    	var sno = $("input[name='sno']").val().trim();
					    	var minscore=$("input[name='score']").val().trim();
					    	var maxscore=$("input[name='score1']").val().trim();
					    	$("input[name='current']").val(api.getCurrent());
					        var data = {
					            page: api.getCurrent(),
					            "gradename" : a,
								"classname" : b,
								"subjectname":c,
								"sno" : sno,
								"minscore":minscore,
								"maxscore":maxscore,
								"state":state
					        };
					        $.getJSON('http://localhost:8080/w/score',data,function(json){
					        	var list=json;
								$("#message").html("");
								var m ="";
								for (var i = 0; i < list.length; i++) {
									if(list[i].scState==2){
										m= "<tr>"
											+"<td>"+(i+1)+ "</td>"
											+"<td>" + list[i].gName + "</td><td>"
										+ list[i].cName + "</td><td>"
										+ list[i].sNo + "</td><td>"
										+ list[i].sName + "</td><td>"
										+ list[i].subjectName + "</td>"
										+"<td>"+ list[i].marks + "</td>"
										+"<td>"+ list[i].timeMarks + "</td>"
										+"<td>第"+ list[i].turn + "学期</td>"
										+"<td><a href='javascriot:void(0)' onclick='rb("+list[i].scId + ")'>恢复</a></td></tr>";
									}else{
										m= "<tr>"
											+"<td>"+(i+1)+ "</td>"
											+"<td>" + list[i].gName + "</td><td>"
										+ list[i].cName + "</td><td>"
										+ list[i].sNo + "</td><td>"
										+ list[i].sName + "</td><td>"
										+ list[i].subjectName + "</td>"
										+"<td>"+ list[i].marks + "</td>"
										+"<td>"+ list[i].timeMarks + "</td>"
										+"<td>第"+ list[i].turn + "学期</td>"
										+"<td><a href='javascriot:void(0)' onclick='d("+list[i].scId + ")'>删除</a><a href='javascriot:void(0)' onclick='u("+list[i].scId + ")'>更新</a>"
										+"</td></tr>";
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
function chose(){
	$("#subjectname option[value='请选择学科']").prop("selected",true);
	$("#subjectname").prop("disabled", true);
	$("#grade").val("");
	$("#grade").prop("disabled", true);
	$("#grade1").val("");
	$("#grade1").prop("disabled", true);
	$("#message").html("");
	$("#body").html("");
	$(".page").html("");
	$(".choseSubject").css("display","block");
	$("#addsubjectname option[value='请选择学科']").prop("selected",true);
}
function searchno(pnum) {
	var subjectname=$("#addsubjectname").val();
	var a = $("#gradename").val();
	var b = $("#classname").val();
	var state=$("#scstate").val();
	$("#gradename").change(function() {
		a = $(this).children('option:selected').val();
	});
	$("#classname").change(function() {
		b = $(this).children('option:selected').val();
	});
	$("#scstate").change(function() {
		state = $(this).children('option:selected').val();
	});
	$("#addsubjectname").change(function() {
		subjectname = $(this).children('option:selected').val();
	});
	if(b==null){
		b="wxy";
	}
	var sno = $("input[name='sno']").val().trim();
	var flage = true;
	/*if(subjectname=="请选择学科"){
		flage=false;
		$.alert({
			message : "请选择学科!",
			// 按钮的文字
			btnText : "确定",
			// 点击按钮后的事件
			btnOkClick : function() {
			}
		});
	}*/
	if(sno!=""&&sno!=null){
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
		var date =new Date();
		var year=date.getFullYear();
		var month=date.getMonth()+1;
		var turn=0;
		if(month>=9||month<2){
			turn=1;
		}else{
			turn=2;
		}
		$.ajax({
			type : "post",
			url : "/w/noScore",
			dataType : "json",
			data : {
				"gradename" : a,
				"classname" : b,
				"sno" : sno,
				"page":pnum,
				"state":state,
				"subjectname":subjectname,
				"year":year,
				"turn":turn
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
				if(e==null||e==""){
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
				}else{
					$(".record").html("");
					var list=e;
					$("#message").html("");
					$("#body").html("");
					$(".page").html("");
					var body="<tr>" +
					"<th>序号</th>" +
							"<th>年级</th>" +
							"<th>班级</th>" +
							"<th>学号</th>" +
							"<th>姓名</th>" +
							"<th></th></tr>";
					$("#body").append(body);
					var m ="";
					for (var i = 0; i < list.length; i++) {
						m= "<tr>"
							+"<td>"+(i+1)+ "</td>"
							+"<td>" + list[i].gName + "</td><td>"
								+ list[i].cName + "</td><td>"
								+ list[i].sNo + "</td><td>"
								+ list[i].sName + "</td>"
								+"<td><a href='javascriot:void(0)' onclick='add("+list[i].sId + ")'>添加分数</a>"
								+"</td></tr>";
						$("#message").append(m);
					}
					$("input[name='current']").val(list[0].currentPage);
					$(".M-box").pagination({
					    pageCount: list[0].totalPage,
					    totalData:list[0].totalDate,
					    showData:list[0].pageCount,
					    current:list[0].currentPage,
					    jump: true,
					    coping:true,
					    homePage:'首页',
					    endPage:'末页',
					    prevContent:'上页',
					    nextContent:'下页',
					    callback:function(api){
					    	var state=$("#scstate").val();
					    	var a = $("#gradename").val();
					    	var b = $("#classname").val();
					    	var sno = $("input[name='sno']").val().trim();
					    	$("input[name='current']").val(api.getCurrent());
					    	var subjectname=$("#addsubjectname").val();
					    	var date =new Date();
							var year=date.getFullYear();
							var month=date.getMonth()+1;
							var turn=0;
							if(month>=9||month<2){
								turn=1;
							}else{
								turn=2;
							}
					        var data = {
					            page: api.getCurrent(),
					            "gradename" : a,
								"classname" : b,
								"sno" : sno,
								"state":state,
								"subjectname":subjectname,
								"year":year,
								"turn":turn
					        };
					        $.getJSON('http://localhost:8080/w/noScore',data,function(json){
					        	var list=json;
								$("#message").html("");
								var m ="";
								for (var i = 0; i < list.length; i++) {
									m= "<tr>"
										+"<td>"+(i+1)+ "</td>"
										+"<td>" + list[i].gName + "</td><td>"
									+ list[i].cName + "</td><td>"
									+ list[i].sNo + "</td><td>"
									+ list[i].sName + "</td>"
									+"<td><a href='javascriot:void(0)' onclick='add("+list[i].sId + ")'>添加分数</a>"
									+"</td></tr>";
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
function add(sid){
	$(".add").stop().animate({
		"top" : "40px"
	},200,function(){
		$(".add").css("display","block");
	});
	$(".mask1").css("display","block");
	$.ajax({
		type : "post",
		url : "/w/add",
		dataType : "json",
		data : {
			"sid" : sid
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
			if(e!=null){
				/*$(".update").css("display","none");*/
				var date =new Date();
				var year=date.getFullYear();
				var month=date.getMonth()+1;
				var turn=0;
				if(month>=9||month<2){
					turn=1;
				}else{
					turn=2;
				}
				var subjectname=$("#addsubjectname").val();
				$("input[name='asno']").val(e.sNo);
				$("input[name='aname']").val(e.sName);
				$("input[name='ayear']").val(year);
				$("input[name='aturn']").val(turn);
				$("input[name='agid']").val(e.gId);
				$("input[name='acid']").val(e.cId);
				$(".sname").text(subjectname);
			}
		}
	});
	
}
function d(scid) {
	var currentpage=$("input[name='current']").val();
	/*alert("scid="+scid+"currentpage="+currentpage);*/
	$.ajax({
		type : "post",
		url : "/w/delete",
		dataType : "json",
		data : {
			"scid" : scid
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
			if(e==1){
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
function rb(scid) {
	var currentpage=$("input[name='current']").val();
	/*alert("scid="+scid+"currentpage="+currentpage);*/
	$.ajax({
		type : "post",
		url : "/w/rback",
		dataType : "json",
		data : {
			"scid" : scid
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
			if(e==1){
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
function u(scid) {
	$(".update").stop().animate({
		"top" : "40px"
	},200,function(){
		$(".update").css("display","block");
	});
	$(".mask").css("display","block");
	var currentpage=$("input[name='current']").val();
	/*alert("sid="+sid+"currentpage="+currentpage);*/
	$.ajax({
		type : "post",
		url : "/w/update",
		dataType : "json",
		data : {
			"scid" : scid,
			"page":currentpage
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
			if(e!=null){
				/*alert(e);*/
				/*$(".update").css("display","none");*/
				$("input[name='uscid']").val(e.scId);
				$("input[name='usno']").val(e.sNo);
				$("input[name='uname']").val(e.sName);
				$("input[name='umarks']").val(e.marks);
				$("input[name='uyear']").val(e.timeMarks);
				$("input[name='uturn']").val(e.turn);
				$(".usubjectname").text(e.subjectName);
			}
		}
	});
}
function addScore(){
//	var sid=$("input[name='asid']").val().trim();
	var marks=$("input[name='ascore']").val().trim();
	var asno=$("input[name='asno']").val().trim();
	var year=$("input[name='ayear']").val();
	var turn=$("input[name='aturn']").val();
	var gid=$("input[name='agid']").val();
	var cid=$("input[name='acid']").val();
	var subjectname=$("#addsubjectname").val();
	var flage=true;
	if(marks==""){
		flage=false;
		$(".ascore").text("成绩不能为空！");
	}else{
		if(isNaN(marks)){
			flage = false;
			/*$.alert({
				message : "成绩不是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});*/
			$(".ascore").text("成绩不是数字！");
		}else{
			var num=Number(marks);
			if(num<0||num>100){
				flage = false;
				/*$.alert({
					message : "成绩范围在0~100之间!",
					// 按钮的文字
					btnText : "确定",
					// 点击按钮后的事件
					btnOkClick : function() {
					}
				});*/
				$(".ascore").text("成绩范围在0~100之间！");
			}
		}
	}
	/*alert(mathMarks+" "+pMarks);*/
	if(flage){
		$.ajax({
			type : "post",
			url : "/w/addScore",
			dataType : "json",
			data : {
				"marks" : marks,
				"asno":asno,
				"year":year,
				"turn":turn,
				"gid":gid,
				"cid":cid,
				"subjectname":subjectname
			},
			error : function(e) {
				$(".add").stop().animate({
					"top" : "0px"
				},200,function(){
					$(".add").css("display","none");
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
				if(e==1){
					$(".add").stop().animate({
						"top" : "0px"
					},200,function(){
						$(".add").css("display","none");
					});
					$(".choseSubject").css("display","none");
					$(".mask1").css("display","none");
					$("input[name='ascore']").val("");
					$("#asubjectname option[value='请选择学科']").prop("selected",true);
					$(".asubjectname").text("");
					$(".ascore").text("");
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
				}else{
					$(".add").stop().animate({
						"top" : "0px"
					},200,function(){
						$(".add").css("display","none");
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
function update(){
	var scid=$("input[name='uscid']").val().trim();
	var marks=$("input[name='umarks']").val().trim();
	var currentpage=$("input[name='current']").val();
/*	alert(scid+"  "+marks);*/
	var flage=true;
	if(marks==""){
		flage=false;
		$(".umarks").text("成绩不能为空！");
	}else{
		if(isNaN(marks)){
			flage = false;
			/*$.alert({
				message : "成绩不是数字!",
				// 按钮的文字
				btnText : "确定",
				// 点击按钮后的事件
				btnOkClick : function() {
				}
			});*/
			$(".umarks").text("成绩不是数字！");
		}else{
			var num=Number(marks);
			if(num<0||num>100){
				flage = false;
				/*$.alert({
					message : "成绩范围在0~100之间!",
					// 按钮的文字
					btnText : "确定",
					// 点击按钮后的事件
					btnOkClick : function() {
					}
				});*/
				$(".umarks").text("成绩范围在0~100之间！");
			}
		}
	}
	if(flage){
		$.ajax({
			type : "post",
			url : "/w/updateScore",
			dataType : "json",
			data : {
				"scid" : scid,
				"marks":marks
			},
			error : function(e) {
				$(".update").stop().animate({
					"top" : "0px"
				},200,function(){
					$(".update").css("display","none");
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
				if(e==1){
					$(".update").stop().animate({
						"top" : "0px"
					},200,function(){
						$(".update").css("display","none");
					});
					$(".mask").css("display","none");
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
function sumPage() {
	var totalpage = 1;
	var a = $("#gradename").val();
	var b = $("#classname").val();
	var c=$("#subjectname").val();
	var state=$("#scstate").val();
	if(b==null){
		b="wxy";
	}
	var sno = $("input[name='sno']").val().trim();
	var minscore=$("input[name='score']").val().trim();
	var maxscore=$("input[name='score1']").val().trim();
	$.ajax({
		type : "post",
		url : "/w/totalPage",
		dataType : "json",
		async:false,/*异步关闭*/
		data : {
			"gradename" : a,
			"classname" : b,
			"subjectname":c,
			"sno" : sno,
			"minscore":minscore,
			"maxscore":maxscore,
			"state":state
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
					m="<option value='"+data[i].cId+"'>"+data[i].cName+"</option>";
					$("#classname").append(m);
				}
			}else{
				$("#classname").empty();
				$("#classname").append("<option value='请选择班级'>--请选择班级--</option>");
			}
		}
	});
}
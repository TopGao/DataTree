(function() {
	// 插件第二种写法,调用方法$.ajax();
	$
			.extend({
				show : function() {
					alert('2323');
				},
				alert : function(options) {
					var defaults = {
						message : "请输入信息",
						btnText : "确定",
						btnClick : undefined,
						skin : "#959e9e",
						fontColor : "white"
					};
					// 覆盖默认的参数
					var opt = $.extend(defaults, options);
					var str = '<div class="mask"></div><div class="dvalert">'
							+ '<div class="alertTitle"   style="background-color:'
							+ opt.skin
							+ ';color:'
							+ opt.fontColor
							+ '">'
							+ '<span class="sTitle">Message</span><span class="alertClose">x</span>'
							+ '</div><div class="dvContent">'
							+ '<div class="alertContent">'
							+ opt.message
							+ '</div>'
							+ '<div class="alertbtn">'
							+ '<button class="btnalert" style="background-color:'
							+ opt.skin + ';color:' + opt.fontColor + '">'
							+ opt.btnText + '</button>' + '</div></div></div>';
					$("body").append(str);
					$(".dvalert").stop().animate({
						"top" : $(".dvalert").height() + "px"
					});
					$(".alertClose").bind("click", $.alertClose);
					$(".btnalert").bind("click", function() {
						$.alertClose();
						if (opt.btnClick) {
							opt.btnClick();
						}
					});
				},
				alertClose : function() {
					$(".mask").remove();
					$(".dvalert").stop().animate({
						"top" : -$(".dvalert").height() - 10 + "px"
					}, 500, function() {
						$(".dvalert").remove();
					});
				}
			});
	// 插件第二种写法,调用方法$("#id").css();
	$.fn
			.extend({
				chkAll : function(option) {
					var defaults = {
						chkName : "chks"
					};
					var obj = $(this);

					// 覆盖原有的参数
					var opt = $.extend(defaults, option);
					var chk = $("input[name=" + opt.chkName + "]");
					obj.click(function() {
						chk.prop("checked", obj.prop("checked"));
					});
					chk.click(function() {
						obj.prop("checked", chk.length == chk
								.filter(":checked").length);
					});

				},
				// 美化复选框(开关)
				icheck : function(options) {
					var defaults = {
						// 选中时的文字
						onText : "ON",
						// 没选的文字
						offText : "OFF",
						// 复选框的大小(宽度)
						size : 100
					};
					var opt = $.extend(defaults, options);
					// 获取美化复选框的宽度
					var width = parseInt(opt.size);
					var height = 0.5 * width;
					var left = 0.5 * width;
					var fontSize = width * 0.15;
					// 真正的checkbox隐藏
					var chk = $(this);
					$(this).hide();
					var str = '<div class="icheck icheckOnColor" style="width:'
							+ width + 'px;height:' + height + 'px;line-height:'
							+ height + 'px;border-radius:' + height + 'px;">'
							+ '<div class="icheckOn"   style="width:'
							+ (0.5 * width) + 'px;font-size:' + fontSize
							+ 'px;">ON</div>'
							+ '<div class="icheckbtn"   style="width:' + height
							+ 'px;height:' + (height - 2) + 'px;left:' + left
							+ 'px;border-radius:' + height + 'px;"></div>'
							+ '</div>';
					// 在一个jq元素后面加一个jq元素
					$(this).after(str);
					// 判断真正的checkbox是哦否一是选中的状态
					if (chk.prop("checked")) {
						chk.next(".icheck").removeClass("icheckCloseColor")
								.addClass("icheckOnColor")
								.children(".icheckOn").text(opt.onText).css({
									"left" : 0
								});
						chk.next(".icheck").children(".icheckbtn").css({
							"left" : left
						});
					} else {
						chk.next(".icheck").removeClass("icheckOnColor")
								.addClass("icheckCloseColor").children(
										".icheckOn").text(opt.offText).css({
									"left" : left
								});
						chk.next(".icheck").children(".icheckbtn").css({
							"left" : 0
						});
					}
					chk.next(".icheck").click(
							function() {
								chk.prop("checked", false);
								// 判断jq元素是否有这个class
								if ($(this).hasClass("icheckOnColor")) {
									$(this).removeClass("icheckOnColor")
											.addClass("icheckCloseColor")
											.children(".icheckOn").text(
													opt.offText).animate({
												"left" : left
											}, 500);
									$(this).children(".icheckbtn").animate({
										"left" : 0
									}, 500);

								} else {
									chk.prop("checked", true);
									$(this).removeClass("icheckCloseColor")
											.addClass("icheckOnColor")
											.children(".icheckOn").text(
													opt.onText).animate({
												"left" : 0
											}, 500);
									$(this).children(".icheckbtn").animate({
										"left" : left
									}, 500);

									;
								}
							});
				},
				page : function(options) {
					var defaults = {
						psize : 4,
						pageIndex : 1,
						pageCount : 0,
						actionName : ""
					};
					var opt = $.extend(defaults, options);
					var str = '<form action='
							+ opt.actionName
							+ ' method="post" name="form1" id="frmpage">'
							+ '当前第<span>'
							+ opt.pageIndex
							+ '</span>页&nbsp; &nbsp; 共<span>'
							+ opt.pageCount
							+ '</span>页&nbsp;'
							+ '&nbsp; 跳转到第<input type="text" name="page"  value='
							+ opt.pageIndex
							+ '>页&nbsp;'
							+ '&nbsp;&nbsp;&nbsp; 每页<input type="text" name="count" value='
							+ opt.psize
							+ '>条'
							+ '<input type="submit" value="跳转" >'
							+ '<br /><input type="button" value="上一页"> <input type="button" value="下一页">'
							+ '</form>';

					$(this).html(str);
					$("input[value='上一页']").bind(
							"click",
							function() {
								$("input[name='page']").val(
										parseInt(opt.pageIndex) - 1);
								$("#frmpage").submit();
							});
					$("input[value='下一页']").bind(
							"click",
							function() {
								$("input[name='page']").val(
										parseInt(opt.pageIndex) + 1);
								$("#frmpage").submit();
							});

				},
				pages : function(options) {
					var defaults = {
						pageIndex : 1,
						pageCount : 0,
						actionName : ""
					};
					var opt = $.extend(defaults, options);

					var html = "<form action=" + opt.actionName
							+ " id='frmpages'><ul class='pages'>共"
							+ opt.pageCount + "页  当前" + opt.pageIndex + "页";
					if (parseInt(opt.currentPage) != 1) {
						html += "<li  class='cpage ' id='prev'><i class='fa fa-angle-double-left'></i></li>";
					} else {
						html += "<li onclick='javascript:void(0)'   disabled='disabled' class='cpage' id='prev'><i class='fa fa-angle-double-left'></i></li>";
					}
					var dotLi_head = "";
					var dotLi_end = "";
					var startNum;
					if (opt.pageIndex < 5)
						startNum = 1;
					else if (opt.pageIndex >= opt.pageCount - 3)
						startNum = opt.pageCount - 4;
					else
						startNum = opt.pageIndex - 2;
					if (startNum > 2)
						dotLi_head = "<li class='cpage' >1</li><li>...</li>";
					if (startNum + 5 < pageCount)
						dotLi_end = "<li>...</li><li  class='cpage'>"
								+ pageCount + "</li>";
					html += dotLi_head;
					var max = opt.pageCount > 5 ? 5 : opt.pageCount;
					for ( var i = 0; i < max; i++) {
						if (startNum + i == opt.pageIndex)
							html += "<li  class='cpage  current'  >"
									+ opt.pageIndex + "</li>";
						else
							html += "<li class='cpage ' >" + (startNum + i)
									+ "</li>";
					}
					html += dotLi_end;
					if (opt.pageIndex == opt.pageCount) {
						html += "<li  onclick='javascript:void(0)'  class='cpage'  id='next'><i class='fa fa-angle-double-right'></i></li>";
					} else {
						html += "<li   class='cpage'  id='next'><i class='fa fa-angle-double-right'></i></li>";
					}
					html += "</ul><input type='hidden' name='page'   value="
							+ opt.pageIndex + "/></form>";
					$(this).html(html);

					$(".cpage").bind("click", function() {
						$("input[name='page']").val(parseInt($(this).text()));
						$("#frmpages").submit();
					});
					$("#next").bind(
							"click",
							function() {
								$("input[name='page']").val(
										parseInt(opt.pageIndex) + 1);
								$("#frmpages").submit();
							});
					$("#prev").bind(
							"click",
							function() {
								$("input[name='page']").val(
										parseInt(opt.pageIndex) - 1);
								$("#frmpages").submit();
							});
				}
			});
	/*全选*/
	$.fn.extend({
		chkAll : function(option) {
			var defaults = {
				chkName : "chks"
			};
			var obj = $(this);

			// 覆盖原有的参数
			var opt = $.extend(defaults, option);
			var chk = $("input[name=" + opt.chkName + "]");
			obj.click(function() {
				chk.prop("checked", obj.prop("checked"));
			});
			chk.click(function() {
				obj
						.prop("checked",
								chk.length == chk.filter(":checked").length);
			});

		}
	});

})($)

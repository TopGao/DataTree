(function() {
	$.fn.extend({
		checkOne : function(options) {

			var defaults = {
				size : 100,
				message : "输入文本",
				inputname:"sex"
			};
			var opt = $.extend(defaults, options);
			// 获取美化复选框的宽度
			var width = parseInt(opt.size);
			var height = 0.5 * width;
			var left = 0.5 * width;
			// 真正的checkbox隐藏
			var chk = $(this);
			/* $(this).hide();*/
			var str ='<div class="chkone checkoffcolor" style="width:' + width
					+ 'px;height:' + height + 'px;' + 'border-radius:' + height
					+ 'px;"><div class="chkonein" style="width:' + height
					+ 'px;height:' + (height - 2) + 'px;left:' + left
					+ 'px;border-radius:' + height + 'px;"></div>' + '</div>'
					+ '<div  class="text unchecktextcolor" style="left:'+(width+5)+'px;top:'+(-(0.32*width))+'px; font-size:'+(0.3*width)+'px;width:'+width+'px;">' + opt.message
					+ '</div>';
			// 在一个jq元素后面加一个jq元素
			$(this).after(str);
			// 判断真正的checkbox是哦否一是选中的状态
			if (chk.prop("checked")) {
				chk.next(".chkone").removeClass("checkoffcolor").addClass(
						"checkoncolor");
				chk.next(".chkone").children(".chkonein").css({
					"left" : left
				});
			} else {
				chk.next(".chkone").removeClass("checkoncolor").addClass(
						"checkoffcolor");
				chk.next(".chkone").children(".chkonein").css({
					"left" : 0
				});
			}
			chk.next(".chkone").click(
					function() {
						var flage = chk.prop("checked");
						// alert(flage);
						if (flage) {
							chk.prop("checked", false);
							$(this).removeClass("checkoncolor").addClass(
									"checkoffcolor");
							$(this).children(".chkonein").animate({
								"left" : 0
							}, 500);
						} else {
							chk.prop("checked", true);
							$(this).removeClass("checkoffcolor").addClass(
									"checkoncolor");
							$(this).children(".chkonein").animate({
								"left" : left
							}, 500);
						}
						// 对整个input的radio相同name进行控制
						$("input[name='"+opt.inputname+"']").map(
								function() {
									var div = $(this).next(".chkone");
									if ($(this).prop("checked")) {
										div.removeClass("checkoffcolor")
												.addClass("checkoncolor");
										div.children(".chkonein").animate({
											"left" : left
										}, 500);
									} else {
										div.removeClass("checkoncolor")
												.addClass("checkoffcolor");
										div.children(".chkonein").animate({
											"left" : 0
										}, 500);
									}
								});
						// 判断jq元素是否有这个class
						/*
						 * if ($(this).hasClass("checkoncolor")) {
						 * $(this).removeClass("checkoncolor").addClass(
						 * "checkoffcolor");
						 * $(this).children(".chkonein").animate({ "left" : 0 },
						 * 500); } else { chk.prop("checked", true);
						 * $(this).removeClass("checkoffcolor").addClass(
						 * "checkoncolor");
						 * $(this).children(".chkonein").animate({ "left" : left },
						 * 500); }
						 */
					});
		}
	});
})($);

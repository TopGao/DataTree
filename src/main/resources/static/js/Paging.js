function W_Paging(currentPage, pageCount, methodName)
{
	var html = "<ul class='pages'>";
	if (parseInt(currentPage) != 1)
	{
		html += "<li onclick='" + methodName + "(" + (parseInt(currentPage) - 1)
				+ ")' class='cpage ' id='prev'><i class='fa fa-angle-double-left'></i></li>";
	}
	else
	{
		html += "<li onclick='javascript:void(0)'   disabled='disabled' class='cpage' id='prev'><i class='fa fa-angle-double-left'></i></li>";
	}
	var dotLi_head = "";
	var dotLi_end = "";
	var startNum;
	if (currentPage < 5)
		startNum = 1;
	else if (currentPage >= pageCount - 3)
		startNum = pageCount - 4;
	else
		startNum = currentPage - 2;
	if (startNum > 2)
		dotLi_head = "<li class='cpage' onclick='" + methodName + "(Number(this.innerHTML))'>1</li><li>...</li>";
	if (startNum + 5 < pageCount)
		dotLi_end = "<li>...</li><li onclick='" + methodName + "(Number(this.innerHTML))' class='cpage'>" + pageCount
				+ "</li>";
	html += dotLi_head;
	var max = pageCount > 5 ? 5 : pageCount;
	for ( var i = 0; i < max; i++)
	{
		if (startNum + i == currentPage)
			html += "<li  class='cpage  current'   onclick='" + methodName + "(Number(this.innerHTML))'>"
					+ currentPage + "</li>";
		else
			html += "<li class='cpage ' onclick='" + methodName + "(Number(this.innerHTML))'>" + (startNum + i)
					+ "</li>";
	}
	html += dotLi_end;
	if (currentPage == pageCount)
	{
		html += "<li  onclick='javascript:void(0)'  class='cpage'  id='next'><i class='fa fa-angle-double-right'></i></li>";
	}
	else
	{
		html += "<li onclick='" + methodName + "(" + (parseInt(currentPage) + 1) + ")'  class='cpage'  id='next'><i class='fa fa-angle-double-right'></i></li>";
	}
	html += "</ul>";
	return html;
}
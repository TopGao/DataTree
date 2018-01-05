$(function () {
	//alert("2222");
	$("#jump").click(function() {

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
	});
	function checkForm() {
		if (check()) {
			location.href = "${pageContext.request.contextPath}/cdc/homePage"
			return true;
		}
		return false;
	}
});




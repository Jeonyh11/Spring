<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/common_lib.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#makeBtn").on("click",function(){
			$("#reqData").text("");
			
			//form
			if($("#type").val()=="form"){
// 				$("#reqData").text($("#frm").serialize());
				$("#reqData").text("userid="+$("input[name=userid]").val()
								 + "&usernm=" + $("input[name=usernm]").val());
			}
			//json
			else{
				
			}
		});	
				
				$("#send").on("click",function(){
					
					$.ajax({
						url : '/ajax/form',
						type : "post",
						data : $("#frm").serialize(),
						dataType : 'json',
						success : function(res){
								console.log(res);
						}
					});
				})
	})
	
</script>

</head>
<body>
<form id="frm">
	사용자 아이디 :<input type="text" name="userid" value="brown"/> <br>
	사용자 이름 :<input type="text" name="usernm" value="브라운"/> <br>
	<select id="type">
		<option value="form">form전송</option>
		<option value="json">json전송</option>
	</select>	<input type="button" id="makeBtn" value="전송데이터생성"/> <br>
	</form>
	<br>
	
	<h3>전송 데이터</h3>
	<span id="reqData">
	</span>
	
	<h3>응답 데이터</h3>
	<span id="respData">
	</span>
	
	<input type="button" id="send" value="전송"/>
</body>
</html>
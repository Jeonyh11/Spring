<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/mvc/multi/param" method="POST">
		userid :<input type="text" name="userid"value="brown"/> <br>
		userid :<input type="text" name="userid"value="sally"/> <br>
		<input type="submit" value="전송"/>
	</form>
	
	<h3>List&lt;UserVo&gt;</h3>
	<form action="/mvc/multi/param" method="POST">
		userid :<input type="text" name="userVoList[0].userid"value="brown"/> <br>
		userid :<input type="text" name="userVoList[1].userid"value="sally"/> <br>
		<input type="submit" value="전송"/>
	</form>
</body>
</html>
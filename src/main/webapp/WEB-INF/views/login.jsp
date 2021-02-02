<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="">

      <title>Login</title>

   <%@ include file="/WEB-INF/views/common/common_lib.jsp" %>
   
   <script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
   
    <link href="${cp }/css/signin.css" rel="stylesheet">
   
   <script>
      function getCookieValue(cookieStr, cookieName) {
         console.log("getCookieValue");
         
         var cookies = cookieStr.split("; ");
         
         for(var i in cookies) {
            
            var cookie = cookies[i].split("=");
            
            if (cookieName == cookie[0]) {
               return cookie[1];
            }
         }
         return "";
      }   
      
      //cookieName : 추가하고자 하는 쿠키이름
      //cookieValue : 쿠키의 값
      //expires : 유효기간 (일수)
      function addCookie(cookieName, cookieValue, expires) {
         var dt = new Date();    // 현재 날짜 ==> expires 만큼 미래날짜로 변경
         dt.setDate( dt.getDate() + parseInt(expires));
         console.log(dt);
         
         document.cookie = cookieName + "=" + cookieValue + "; " +
                        "path=/; expires=" + dt.toGMTString();
      }
      
      function deleteCookie(cookieName) {
         addCookie(cookieName, "", -1);
      }
      
      // html 문서 로딩이 완료된 후 실행되는 코드
      $(function(){
         <c:if test="${msg != null }">
            alert("${msg}");
         </c:if>
         
         // userid, rememberme 쿠키를 확인하여 존재할 경우 값 설정, 체크
         if (Cookies.get("rememberme") != undefined) {
            $("#rememberme").prop("checked", true);   
            $("#userid").val(Cookies.get("userid"));
         }
         
         //signin 아이디를 select
         $('#signin').on("click", function(){
            // rememberme 체크박스의 체크 여부 확인
            // 체크되어있을 경우
            if($("#rememberme").is(":checked") == true){
               // userid input에 있는 값을 userid쿠키로 저장
               Cookies.set("userid" , $('#userid').val());                        
               // rememberme 쿠키로 Y 값을 저장
               Cookies.set("rememberme" , "Y");
               
            } 
            // 체크되어있지 않은 경우 : userid, rememberme 쿠키 삭제
            else {
               Cookies.remove("userid");
               Cookies.remove("rememberme");
            }
            
            // form태그를 이용하여 signin 요청
            $('#frm').submit();
            
         });
      });
   </script>
   
</head>

<body>

    <div class="container">
      <form class="form-signin" id="frm" action="${cp }/login/process" method="post">
         <h2 class="form-signin-heading">로그인</h2>
        <label for="userid" class="sr-only">userid</label>
        <input type="text" name="userid" id="userid" class="form-control" placeholder="사용자 아이디" required autofocus>
        <label for="pass" class="sr-only">password</label>
        <input type="password" name="pass" id="pass" class="form-control" placeholder="비밀번호" required>
        <div class="checkbox">
           <label>
               <input id="rememberme" type="checkbox" value="remember-me"> Remember me
             </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="signin">로그인</button>
      </form>

    </div> 


</body>
</html>
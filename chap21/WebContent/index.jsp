<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판 예제</title>
</head>
<body>

<u:isLogin>
change: ${authUser.name}님, 안녕하세요!
<a href="logout.do">[Logout]</a>
<a href="changePwd.do">[change password]</a>
</u:isLogin>

<u:isLogin>
change: <a href="join.do">[join]</a>
<a href="login.do">[Login]</a>
</u:isLogin>
<br/>
</body>
</html>
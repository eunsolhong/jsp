<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>방명록 메세지 삭제 확인</title>
</head>
<body>
<form action="deleteMessage.jsp" method="post">
<input type="hidden" name="messageId" value="${param.messageId}">
메세지를 삭제하시려면 암호를 입력하세요 : <br><p>
암호 : <input type="password" name="password"><br><p>
<input type="submit" value="메세지 삭제하기">
</form>
</body>
</html>
<%@page import="service.MessageListView"%>
<%@page import="service.GetMessageListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String pageNumberStr= request.getParameter("page");
int pageNumber = 1;
if(pageNumberStr != null){
	pageNumber = Integer.parseInt(pageNumberStr);
}

GetMessageListService messageListService = 
GetMessageListService.getInstance();

MessageListView viewData1 = 
messageListService.getMessageList(pageNumber);

%>

<!-- 순서 : 뷰 -> 서비스 -> 다오 (다오에서 서비스를 뷰 데이터로 만들어주고 뿌려줌) -->
<!-- list -> getmessageListService -> Dao들어가있는 방명록을 가져옴   -->

<c:set var="viewData" value="<%= viewData1%>" />  
<html>
<head>
<title>방 명 록 메 세 지 목 록</title>
<link href="https://fonts.googleapis.com/css?family=Sunflower:300,500,700&display=swap&subset=korean" rel="stylesheet">
<style>
*{font-family: 'Sunflower', sans-serif;}
*{font-size : 30px;}
</style>
</head>
<body>
<form action="writeMessage.jsp" method="post" style="width:434px;">
<br>

*방명록*<br>
<p>
**이름 : <input type="text" name="guestName" size="4"> <br>
**암호 : <input type="password" name="password" size="8" ><br>

메세지 : <br> 
<textarea name="message" cols="30" rows="3"></textarea><br>
<p>
<input type="submit" value="메세지남기기" style="text-align: right; float:right;"  /></form><br>
<hr><c:if test="${viewData.isEmpty()}"> 등록된 메세지가 없습니다. </c:if>
<c:if test="${!viewData.isEmpty()}">
<table border="1">
<c:forEach var="message" items="${viewData.messageList}">
<tr><td>
메세지 번호 : ${message.id} <br/>
손님이름 : ${message.guestName} <br/>
메세지 : ${message.message} <br/>
<a href="confirmDeletion.jsp?messageId=${message.id}">[삭제]</a>
</td></tr> 
</c:forEach>
</table>
<c:forEach var="pageNum" begin="1" end="${viewData.pageTotalCount}"> 
<a href="list.jsp?page=${pageNum}">[${pageNum}]</a>
</c:forEach>
</c:if>
</body>
</html>

<%@page import="member.LogonDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap&subset=korean" rel="stylesheet">
<title>Insert title here</title>
<style>
*{font-family: 'Poor Story', cursive;}
</style>

</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");

LogonDao manager = LogonDao.getInstance();
int check = manager.confirmId(id);
if(check==1){
	%>
	<table width="270" border="0" cellspacing="0" cellpadding="5">
	<tr>
	<td align="center" height="39">~<%=id%>는 이미 사용중~</td>
	</tr>	
	</table>
	<form name="checkForm" method="post" action="confirmId.jsp">
	<table width="270" border="0" cellspacing="0" cellpadding="5">
	<tr>
	<td align="center">
	~다른 아이디를 선택하세요~<p>
	<input type="text" size="10" maxlength="12" name="id"  autocomplete=off>
	<input type="submit" value="ID중복확인">
</td>
</tr>	
	</table> 
	</form>
	
	
	<%} else { %>
<table width="270" border="0" cellspacing="0" cellpadding="5">
<tr>
<td align="center">
<p> 입력하신 <%=id%>는 사용하실수있는 ID입니다.</p>
<input type="button" value="닫기" onclick="setid()">
</td>
</tr>
</table>
<% } %>
</body>
<script>
function setid(){
	opener.document.userinput.id.value="<%=id%>";
	self.close();
}
</script>
</html>
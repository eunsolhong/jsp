<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>MAIN</title>
<html>
<body>

<%
if(session.getAttribute("memID")==null){
	response.sendRedirect(request.getContextPath()+"/member/loginForm.jsp");
} else{%>
<div class = "w3-container">
<table class ="w3-table w3-bordered">
<tr>
<td width="300" height="20">오구</td>
<td rowspan="3" align="center">
<%=session.getAttribute("memID")%>님이<br>방문하셨습니다.
<form method="post" action="loginout.jsp">
<input type="submit" value="로그아웃">
<input type="button" value="회원정보변경" 
onclick="javascript:window.location=' <%=request.getContextPath()%>/member/modifyForm.jsp'">
<input type="button" value="회원정보삭제" 
onclick="javascript:window.location=' <%=request.getContextPath()%>/member/deleteForm.jsp'">
</form>
</td>
</tr>
<tr> <td rowspan="2" width="300">메인입니다. </td>
</tr></table></div> <% } %> 
</body>
</html>
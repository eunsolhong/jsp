<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>deletePro</title>
</head>
<body>
<%
int num = Integer.parseInt(request.getParameter("num"));
String passwd = request.getParameter("passwd");

BoardDao dbPro = BoardDao.getInstance();
int check = dbPro.deleteArticle(num, passwd);

if(check==1){
	%> 
	<meta http-equiv="Refresh" content="0;url=list.jsp">
	
<%}else{ %>
<script language="JavaScript">
alert("!비밀번호불일치!");
history.go(-1);
</script><%} %>
</body>
</html>
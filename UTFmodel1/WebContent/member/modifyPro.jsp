<%@page import="member.LogonDao"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>modifyPro</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="member" class="member.LogonDataBean">
<jsp:setProperty name="member" property="*"/>
</jsp:useBean>
<%
member.setId((String)session.getAttribute("memID"));

LogonDao manager = LogonDao.getInstance();
manager.updateMember(member);

response.sendRedirect("main.jsp");
%>
</body>
</html>
<%@page import="member.LogonDao"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="member" class="member.LogonDataBean">
<jsp:setProperty name="member" property="*"/>
</jsp:useBean>
<%
member.setReg_date(new Timestamp(System.currentTimeMillis()));
LogonDao manager = LogonDao.getInstance();
manager.insertMember(member);

response.sendRedirect("index.jsp");
%>
</body>
</html>
<%@page import="member.LogonDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");


%>

<%
String id = request.getParameter("id");
String passwd = request.getParameter("passwd");

LogonDao manager = LogonDao.getInstance();
int check = manager.userCheck(id,passwd);
%>

<% if(check==1){
session.setAttribute("memID", id); 
response.sendRedirect(request.getContextPath()+"/member/main.jsp");
} else if(check ==0) {
%>

<script>
alert("~비밀번호가 일치하지않습니다~");
history.go(-1);
</script>
<% } else { %>
<script>
alert("~아이디가 일치하지않습니다~");
history.go(-1);
</script>
<% } %>
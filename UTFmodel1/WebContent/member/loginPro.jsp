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
//개발자가 요구하는 시간까지만 유지하는거 : session
//session.setAttribute("memID", null);  : board 아이디는 유지됨  
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
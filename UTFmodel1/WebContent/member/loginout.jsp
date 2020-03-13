<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* session.invalidate(); */
//모든 것에서 나감. 

session.removeAttribute("memID");
//memID 만 지우기위함, 다른 세션은 나가지않음.

 response.sendRedirect("main.jsp");

%>

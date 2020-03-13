<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NullparameterFilter Test</title>
</head>
<body>
id파라미터 : <%=request.getParameter("id") %>
name 파라미터 : <%=request.getParameter("name") %>
member 파라미터 : <%=request.getParameter("member") %>
</body>
</html>
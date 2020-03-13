<%@page import="member.LogonDataBean"%>
<%@page import="member.LogonDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보수정</title>
<link rel="style.css"rel="stylesheet" type="text/css">
</head>
<body>
<% String id = (String)session.getAttribute("memID");
LogonDao manager = LogonDao.getInstance();
LogonDataBean c = manager.getMember(id);
try{%>

<% } catch(Exception e){} %>

<div class = "w3-container w-center w3-padding-64">
<form method="post" action="modifyPro.jsp" name="userinput" onSumit="return checkIt()">
<table class = "w3-table-all w3-center">


	<tr> 
		<td colspan="2" height="39" align="center"><font size="+5"><b>회원가입</b></font></td>
		</tr>
		<tr>
			<td width="100"><b>아이디입력</b></td>
			<td width="300">&nbsp;</td>
			</tr>
			<tr>
				<td width="100">사용자 ID</td>
				<td width="300"><%=c.getId()%></td>
			</tr>
			<tr>
				<td width="100">비밀번호</td>
				<td width="300"><input type="password" name="passwd" size="15"
					maxlength="12"></td>
			</tr>
			
			<tr>
				<td width="100"><b>개인정보 입력</b></td>
				<td width="300">&nbsp;</td>
			<tr>
			<tr>
				<td width="100">사용자이름</td>
				<td width="300"><input type="text" name="name" size="15" 
					maxlength="10" autocomplete=off value="<%=c.getName()%>" ></td>
			</tr>
			<tr>
				<td width="100">주민등록번호</td>
				<td width="400"> <%=c.getJumin1()%> -<%=c.getJumin2()%></td>
			</tr>
			<tr>
				<td width="100">E-Mail</td>
				<td width="400"><input type="text" name="email" size="40"
					maxlength="30"  autocomplete=off value="<%=c.getEmail()%>"></td>
			</tr>
			<tr>
				<td width="100">Blog</td>
				<td width="400"><input type="text" name="blog" size="60"
					maxlength="50"  autocomplete=off value="<%=c.getBlog()%>"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name="confirm" value="수정"> <input type="reset"
					name="reset" value="취소" onclick="javascript:window.location='main.jsp'">
					</td>
					</tr>
</table></form>
</body>
</html>
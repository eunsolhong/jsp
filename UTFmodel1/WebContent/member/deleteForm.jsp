<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap&subset=korean" rel="stylesheet">

<title>회원탈퇴</title>
<style>
h1{font-family: 'Poor Story', cursive;}
*{font-family: 'Poor Story', cursive;}
</style>

</head>
<body>

<div class="w3-container">
<form name = "myform" action="deletePro.jsp" method="post">

<strong><h1>회원탈퇴</h1></strong>
<TABLE class="w3-table-all">

<tr height="30">
<td width="110" align="center">비밀번호</td>
<td width="150" align="center">
<input type="password" name="passwd" size="15" maxlength="12"></td></tr>
<tr height="30">
<td colspan="2" class="w3-center">

<input type="submit"  value="회원탈퇴">

<input type="button" value="취소"
onclick = "javascrip:window.location='<%=request.getContextPath()%>/member/main.jsp'">
</td></tr>
</TABLE>
</form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap&subset=korean" rel="stylesheet">

<title>deleteForm</title>
<style>

h3{ text-align:center;
font-family: 'Poor Story', cursive;}
*{font-family: 'Poor Story', cursive;}

.w3-table {
margin : 0 auto;
width: 190px;
}
</style>
</head>

<body>

<div class="w3-container">

<br>
<br>
<br>
<strong><h3>~ 글삭제합니다 ~</h3></strong>
<br>
<form name = "delForm" action="deletePro.jsp" method="post">
<TABLE class="w3-table">

<tr height="30">
<td width="50" align="center">*비밀번호를 입력해주세요*</td></tr>
<tr>
<td width="50" align="center">
<input type="password" name="passwd" size="25" maxlength="10"></td></tr>
<tr height="30">
<input type = "hidden" name ="num" value="<%=request.getParameter("num") %>">
<td colspan="2" class="w3-center">

<input type="submit"  value="글삭제">
<input type="button" value="글목록" onclick = "document.location.href='list.jsp'">
</td></tr>
</TABLE>
</form>
</div>
</body>
</html>
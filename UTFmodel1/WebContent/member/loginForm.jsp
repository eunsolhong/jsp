<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap&subset=korean" rel="stylesheet">

<title>로그인</title>
<style>
h1{font-family: 'Poor Story', cursive;}
*{font-family: 'Poor Story', cursive;}
</style>
<script>

function begin(){
	document.myform.id.focus();
}


function checkIt(){
	var myform =eval("document.myform");
	
	if(!document.myform.id.value){ 
		alert("~아이디를 입력하지않았어요~"); 
	document.myform.id.focus();
	return false;
	}
	if(!document.myform.passwd.value){ 
		alert("~비밀번호를 입력하지않았어요~");
	document.myform.passwd.focus();
	return false;
	}
	}


</script>
</head>
<body>
<div class="w3-container">
<strong><h1>Login</h1></strong>
<form name="myform" action="loginPro.jsp" class="w3.form" method="post" onSubmit="return checkIt()">
<TABLE class="w3-table-all">
<tr height="30">
<td colspan="2" align="center"><strong>회원로그인</strong></td></tr>
<tr height="30">
<td width="110" align="center">아이디</td>
<td width="150" align="center">
<input type="text" name="id" size="15" maxlength="12" autocomplete=off></td></tr>
<tr height="30">
<td width="110" align="center">비밀번호</td>
<td width="150" align="center">
<input type="password" name="passwd" size="15" maxlength="12"></td></tr>
<tr height="30">
<td colspan="2" class="w3-center">
<input type="submit"  value="로그인">
<input type= "reset" value="다시입력">
<input type="button" value="회원가입"
onclick = "javascript:window.location='inputForm.jsp'"></td></tr>
</TABLE>

</form>
</div>
</body>
</html>
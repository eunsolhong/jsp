<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap&subset=korean" rel="stylesheet">

<style>
table{
margin : 60px;
}

body {
background-image:url('1212.jpg');
background-repeat:no-repeat;
background-size:cover;
}

*{font-family: 'Poor Story', cursive;}
</style>
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>
<body>
<script>
function checkIt(){
	var userinput = eval("document.userinput");
	if(!userinput.id.value){ alert("ID를 입력하세요"); userinput.id.focus();
	return false;}
	if(!userinput.passwd.value){ alert("비밀번호를 입력하세요"); userinput.passwd.focus();
	return false;}
	if(!userinput.passwd.value != userinput.passwd2.value){ 
		alert("비밀번호를 동일하게 입력하세요");
	return false;}
	if(!userinput.name.value){ alert("name을 입력하세요"); userinput.name.focus();
	return false;}
	if(!userinput.jumin1.value){ alert("jumin1을 입력하세요"); userinput.jumin1.focus();
	return false;}
	if(!userinput.jumin2.value){ alert("jumin2을 입력하세요"); userinput.jimin2.focus();
	return false;}
	if(!userinput.email.value){ alert("email을 입력하세요"); userinput.email.focus();
	return false;}	
}

//아아디 중복 여부를 판단
function openConfirmid(userinput){
	//아이디를 입력했는지 검사
	if(userinput.id.value ==""){
		alert("아이디를 입력하세요");
		return;
	}
	
	//url과 사용자입력 id를 조합합니다.
	url = "confirmId.jsp?id=" + userinput.id.value;
	
	//새로운 윈도우를 엽니다.
	open(url,"confirm","toolbar=no,location=no,status=no,menuba=no,scrollbars=no,resizable=no,width=300,height=200");
}


</script>


	<form method="post" action="inputPro.jsp" name="userinput" onSubmit="return checkIt()">
		
		<table class="w3-table w3-bordered" cellspacing="0" cellpadding="3"
			align="center">
			
		
			<tr> 
			<!-- LogonDataBean  -->
				<td colspan="2" height="39" align="center"><font size="+5"><b>회원가입</b></font></td>
			</tr>
			<tr>
				<td width="100"><b>아이디입력</b></td>
				<td width="300">&nbsp;</td>
			</tr>
			<tr>
				<td width="100">사용자 ID</td>
				<td width="300"><input type="text" name="id" size="10"
					maxlength="12"  autocomplete=off> <input type="button" name="confirm_id"
					value="중복확인" Onclick="openConfirmid(this.form)"></td>
			</tr>
			<tr>
				<td width="100">비밀번호</td>
				<td width="300"><input type="password" name="passwd" size="15"
					maxlength="12"></td>
			</tr>
			<tr>
				<td width="100">비밀번호 확인</td>
				<td width="300"><input type="password" name="passwd2" size="15"
					maxlength="12"></td>
			</tr>
			<tr>
				<td width="100"><b>개인정보 입력</b></td>
				<td width="300">&nbsp;</td>
			<tr>
			<tr>
				<td width="100">사용자이름</td>
				<td width="300"><input type="text" name="name" size="15" 
					maxlength="10"  autocomplete=off></td>
			</tr>
			<tr>
				<td width="100">주민등록번호</td>
				<td width="400"><input type="text" name="jumin1" size="7"
					maxlength="6"  autocomplete=off> -<input type="text" name="jumin2" size="7"
					maxlength="7"  autocomplete=off></td>
			</tr>
			<tr>
				<td width="100">E-Mail</td>
				<td width="400"><input type="text" name="email" size="40"
					maxlength="30"  autocomplete=off></td>
			</tr>
			<tr>
				<td width="100">Blog</td>
				<td width="400"><input type="text" name="blog" size="60"
					maxlength="50"  autocomplete=off></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name="confirm" value="등  록"> <input type="reset"
					name="reset" value="다시입력"> <input type="button"
					value="가입안함" onclick="javascript:window.location='main.jsp'">
					
					
</td></tr>   
</table>
</body>
</html>
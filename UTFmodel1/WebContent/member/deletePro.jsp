<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="member.LogonDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>deletePro</title>
</head>
<%
String id = (String)session.getAttribute("memID");
String passwd = request.getParameter("passwd");

LogonDao manager = LogonDao.getInstance();
int check = manager.deleteMember(id,passwd);

if(check==1){
	session.invalidate();
}
%>


<% if(check==1){
session.setAttribute("memID", id); 
response.sendRedirect(request.getContextPath()+"/member/deleteForm.jsp");
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


<body>
<form method="post" action="main.jsp" name="userinput">
<TABLE class="w3-table-all">
<tr>
<td height="39" align="center">
<font size="+1"><b> 회원정보 삭제 완료 </b></font></td>
</tr><tr><td align="center"> <p> 안녕히가세용 </p>
<meta http-equiv="Refresh" content="5;url=main.jsp"> <!--뷰단에서 화면을 이동하기위함 -->
</td></tr><tr> <td align="center">
<input type="submit" value="확인"></td></tr></TABLE>
</form>


</body>
</html>




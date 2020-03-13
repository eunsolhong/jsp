<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<body>

<p class="w3-left" style="padding-left:30px;"></p>
<div class="w3-container">
<h1><b>* 글쓰기</h1></b><br>
<%
int num=0, ref=1, re_step=0, re_level=0;
if(request.getParameter("num")!=null){ //글을 쓰면 num이 할당됨
	num=Integer.parseInt(request.getParameter("num"));
	ref=Integer.parseInt(request.getParameter("ref"));
	re_step = Integer.parseInt(request.getParameter("re_step"));
	re_level = Integer.parseInt(request.getParameter("re_level"));
}

%>
<form method="post" name="writeform" enctype="multipart/form-data"
action="<%=request.getContextPath()%>/board/writeUploadPro.jsp">
<input type="hidden" name = "num" value="<%=num%>">
<input type="hidden" name = "ref" value="<%=ref %>">
<input type="hidden" name = "re_step" value="<%=re_step %>">
<input type="hidden" name = "re_level" value="<%=re_level %>">

<table class = "w3-table" style="width:50%;">

<tr><td width="70" align="center"> 이름 </td>
<td width="330">
<input type="text" size="10" maxlength="10" name="writer"></td></tr>
<tr> <td width="70" align="center"> 제  목 </td> <td width="330">

<%if(request.getParameter("num")==null) { %>
<input type="text" size="40" maxlength="50" name="subject"></td></tr>
<% } else { %>

<input type="text" size="40" maxlength="50" name="subject" value=" *답글*  "></td></tr>

<%} %>

<tr> <td width="70" align="center"> Email</td> 
<td width="330"><input type="text" size="40" maxlength="30" name="email"></td></tr>
<tr> <td width="70" align="center"> 내 용</td> 
<td width="330"><textarea name="content" rows="13" cols="40"></textarea></td></tr>


<tr> <td width="70" align="center"> file </td>
<td width="330"><input type="file" size="40" maxlength="30"
name = "uploadfile"></td></tr>


<tr> <td width="70" align="center"> 비밀번호</td> 
<td width="330"><input type="password" size="8" maxlength="12" name="passwd"></td></tr>
<tr>
<td colspan=2 align="center"> <input type="submit" value="글쓰기">
<input type="reset" value="다시작성"> 
<input type="button" value="목록보기" onclick="window.location='list.jsp'">

<tr><td align="right" colspan="2"><a href="<%=request.getContextPath()%>/board/list.jsp">글목록</a></td></tr>
</td></tr></table>
</form>
</div>
</head>
</body>
</html>
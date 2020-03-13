<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<body>

<p class="w3-left" style="padding-left:30px;"></p>
<div class="w3-container">
<h1><b>* 글쓰기</h1></b><br>

<form method="post" name="writeform" enctype="multipart/form-data"
action="${pageContext.request.contextPath}/board/writePro">
<input type="hidden" name = "num" value="${num}">
<input type="hidden" name = "ref" value="${ref}">
<input type="hidden" name = "re_step" value="${re_step}">
<input type="hidden" name = "re_level" value="${re_level}">

<table class = "w3-table" style="width:50%;">

<tr><td width="70" align="center"> 이름 </td>
<td width="330">
<input type="text" size="10" maxlength="10" name="writer"></td></tr>
<tr> <td width="70" align="center"> 제  목 </td> <td width="330">


<c:if test="${num==0}">
<input type="text" size="40" maxlength="50" name="subject"></c:if>

<c:if test="${num!=0}">
<input type="text" size="40" maxlength="50" name="subject" value=" *답글*  "></c:if>



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

<tr><td align="right" colspan="2"><a href="${pageContext.request.contextPath}/board/list">글목록</a></td></tr>
</td></tr></table>
</form>
</div>
</head>
</body>
</html>
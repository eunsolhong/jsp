<%@page import="board.BoardDataBean"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>updateForm</title>
</head>
<body>
<%


//두개 받아오기
//pageNum 받아왔지만 넘겨주지않아서 pageNum은 실행되지않음
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");

BoardDao dbPro = BoardDao.getInstance();

BoardDataBean article = dbPro.getUpdateArticle(num);

%>

<title>게시판</title>

<body>

<p class="w3-left" style="padding-left:30px;"></p>
<div class="w3-container">
<h1><b>게시판 수정</h1></b><br>

<form method="post" name="writeform" action="<%=request.getContextPath()%>/board/updatePro.jsp">

<input type="hidden" name = "num" value="<%=article.getNum() %>" > 
<!-- hidden : 써야되는데  입력받을 필요는 없어서   -->



<table class = "w3-table-all" style="width:70%;">

<tr><td align="right" colspan="2">
<a href="<%=request.getContextPath()%>/board/list.jsp">글목록</a></td></tr>
<tr><td width="70" align="center"> 이름 </td>
<td width="330">
<input type="text" size="10" maxlength="10" name="writer" value="<%=article.getWriter()%>"></td></tr>
<tr> <td width="70" align="center"> 제  목 </td> <td width="330">
<input type="text" size="40" maxlength="50" name="subject" value="<%=article.getSubject()%>"></td></tr>
<tr> <td width="70" align="center"> Email</td> 
<td width="330"><input type="text" size="40" maxlength="30" name="email" value="<%=article.getEmail()%>"></td></tr>
<tr> <td width="70" align="center"> 내 용</td> 
<td width="330"><textarea name="content" rows="13" cols="40"><%=article.getContent()%>"</textarea></td></tr>

<tr> <td width="70" align="center"> 비밀번호</td> 
<td width="330"><input type="text" size="8" maxlength="12" name="passwd" value="<%=article.getPasswd()%>"></td></tr>
<tr>
<td colspan=2 align="center"> <input type="submit" value="수정">
<input type="reset" value="다시작성"> 
<input type="button" value="목록보기" onclick="window.location='list.jsp'">
</td></tr></table>
</form>
</div>
</head>
</body>
</html>

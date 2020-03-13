<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.BoardDataBean"%>
<%@page import="java.util.List"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap&subset=korean" rel="stylesheet">
<style>
h2{font-family: 'Poor Story', cursive;}
a {text-decoration: none;}
</style>

<title>list</title>
</head>
<body>
<%

int pageSize= 10;

int currentPage = 0;

//"null"
try{
currentPage = Integer.parseInt(request.getParameter("pageNum"));
session.setAttribute("pageNum", currentPage);	

} catch(Exception e){
	
if(session.getAttribute("pageNum")==null){
	session.setAttribute("pageNum",1);
}
}

currentPage = (int)session.getAttribute("pageNum");


int startRow = (currentPage-1)*pageSize+1;
int endRow = startRow + pageSize - 1;
// int endRow =currentPage*pageSize;


BoardDao service = BoardDao.getInstance();
int count = service.getArticleCount(boardid);
List li = service.getArticles(startRow, endRow, boardid);
SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
int number = count-(currentPage-1)*pageSize; //게시글 번호

%>
<p class = "w3-left" style="padding-left: 30px;">
</p>
<div class = "w3-container">
<span class="w3-center w3-large">
<h2><strong>*** 게시판 ( 전체글 _ <%=count %> ) ***</strong></h2>
</span>
<p class="w3-right w3-padding-right-large"><a href="writeUploadForm.jsp">글쓰기</a></p>


<%
if(count == 0){
%>
<table class="table-bordered" width="700">
<tr class="w3-grey">
<td align="center"> 게시판에 저장된 글이 없습니다. </td>
</table>
<%
} else{
%>

<table class="w3-table-all">
<tr class="w3-brown">
<td align="center"> 번호 </td>
<td align="center"> 제  목 </td>
<td align="center"> 작성자</td>
<td align="center"> 작성일 </td>
<td align="center"> 조 회 </td>
<td align="center"> IP </td>
<td align="center"> 파일이름 </td>
<td align="center"> 파일사이즈 </td>
</tr>

<%
for(int i=0; i< li.size(); i++){
	BoardDataBean article = (BoardDataBean) li.get(i);
%>

<tr class="w3-white">
<td align="center"> <%=number-- %> </td>
<td align="center"> 

<%
int wid = 0; 
if(article.getRe_level() > 0){ //답글이면 
wid = 30 * (article.getRe_level());
%><img src = "<%=request.getContextPath()%>/image/level.gif" width="<%=wid %>" height="16">
<img src="<%=request.getContextPath()%>/image/re.gif">
<%
}else {
%> <img src = "<%=request.getContextPath()%>/image/level.gif" width="<%=wid%>" height="16">
<%} %> 



<a href="<%=request.getContextPath()%>/board/content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>"><%=article.getSubject() %> </td>
<td align="center"> <%=article.getWriter() %></td>
<td align="center"> <%=sdf.format(article.getReg_date()) %> </td>
<td align="center"> <%=article.getReadcount() %> 회 </td>
<td align="center"> <%=article.getIp() %> </td>
<td align="center"> <%=article.getFilename() %> </td>
<td align="center"> <%=article.getFilesize() %> </td>
</tr>

<% } %></table>




<p align="center">

<%
int bottomLine = 3;
int pageCount = count/pageSize +(count % pageSize == 0 ? 0 : 1);
int startPage = 1+(currentPage -1)/bottomLine * bottomLine;
int endPage = startPage + bottomLine -1;

if(endPage>pageCount) endPage=pageCount;

if(startPage>bottomLine){
	%>
		<a href="list.jsp?pageNum=<%=startPage-bottomLine%>">[이전]</a>
	<% 
}


for(int i=startPage; i<=endPage; i++){
	%>
	<a href="list.jsp?pageNum=<%=i%>"><%=i %></a>

<%} 

if(endPage< pageCount){	%>
<a href="list.jsp?pageNum=<%=startPage + bottomLine%>">[다음]</a>

 
<%}} %>


</p>
</div>
</body>
</html>
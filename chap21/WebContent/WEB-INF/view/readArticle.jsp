<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<script>
function confirmDel(){
	var con = confirm("~정말로 삭제하시겠습니까~");
	if(conn){
		location.href = "delete.do?no=${articleData.article.number}";
	}
}
</script>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 읽기</title>
</head>
<body>
	<table border="1" width="27%">
		<tr>
			<td>번호</td>
			<td>${articleData.article.number}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${articleData.article.writer.name}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${articleData.article.title}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${articleData.content}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><u:pre value="${articleData.content}"/></td>
		</tr>

	 <tr>
			<td colspan="2">
		 	<c:set var="pageNo"  value="${ empty param.pageNo ? 1 : param.pageNo}" />
			<a href="list.do?pageNo=${param.pageNo}">목록</a>
			 <c:if test="${authUser.id == articleData.article.writer.id}">
					<a href="modify.do?no=${articleData.article.number}">[게시글수정]</a>
					<a href="#" onclick = "confirmDel()">[게시글삭제]</a>
				</c:if></td>
		</tr> 
	</table>
</body>
</html>
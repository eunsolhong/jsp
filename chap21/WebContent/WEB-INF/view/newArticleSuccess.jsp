<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
</head>
<body>
~게시글 등록완료~
<br>
${ctxPath = pageContext.request.contextPath;''}
<a href="${ctxPath}/article/list.do">[게시글목록보기]</a>
<a href="${ctxPath}/article/read.do?no=${modReq.articleNumber}">[게시글내용보기]</a>
</body>
</html>
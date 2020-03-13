<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1}">

<!-- 비밀번호가 맞으면 1 -->

<meta http-equiv="Refresh" content="0;url=list?pageNum="> </c:if>

<!-- 시간(second)와 동시에 새로고침으로 이동하게 될 페이지나 파일의 URL까지 정해줄수있다  -->
<!-- meat http-eqiv="refresh" content="시간(초단위); URL=이동하고자하는 웹페이지 또는 미디어 주소 -->


<c:if test="${check==0}">
<script language="JavaScript">
alert("!비밀번호 불일치!")
history.go(-1);
</script>
</c:if>

<c:if test="${check==-1}">
<script language="JavaScript">
alert("!게시물이없음!")
history.go(-1);
</script>
</c:if>
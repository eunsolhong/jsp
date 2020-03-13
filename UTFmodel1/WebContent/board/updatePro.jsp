<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="article" class="board.BoardDataBean">
<jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<%

System.out.print(article); /* 콘솔에 찍어보기위함  */

BoardDao dbPro = BoardDao.getInstance(); 
// getInstance(); 싱글턴패턴, 하나의 인스턴스만 가지고 공유해서 씀.
//싱글턴 패턴 
//생성자를 private로 선언하여 다른클래스에서 해당클래스의 인스턴스를 new로 생성하지못하게하구, getInstance()함수를 통해 인스턴스를 갖도록한다.


int check = dbPro.updateArticle(article); //비밀번호 체크체크

System.out.print(check); /* 콘솔에 찍어보기위함  */

if(check==1){%>

<!-- 비밀번호가 맞으면 1 -->

<meta http-equiv="Refresh" content="0;url=list.jsp?pageNum=">

<!-- 시간(second)와 동시에 새로고침으로 이동하게 될 페이지나 파일의 URL까지 정해줄수있다  -->
<!-- meat http-eqiv="refresh" content="시간(초단위); URL=이동하고자하는 웹페이지 또는 미디어 주소 -->

<%} else{ %>
<script language="JavaScript">
alert("!비밀번호 불일치!")
history.go(-1);
</script>
<%} %>

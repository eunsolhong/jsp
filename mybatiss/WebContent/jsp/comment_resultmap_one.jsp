<%@page import="service.CommentResultMapService"%>
<%@page import="model.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//XML만으로 매핑구문과 결과 매핑 처리
	//CommentMapperDynamicSql.xml의 selectCommentByPrimaryKey매핑구문
	Long commentNo = Long.parseLong(request.getParameter("commentNo"));
	CommentResultMapService commentResultService = new CommentResultMapService();
	Comment comment = commentResultService.selectByPrimaryKey(commentNo);
%>
댓글번호 :
<%=comment.getCommentNo()%><br>
작성자 아이디 :
<%=comment.getUserId()%><br>
작성일시 :
<%=comment.getRegDate()%><br>
댓글내용 :
<%=comment.getCommentContent()%><br>



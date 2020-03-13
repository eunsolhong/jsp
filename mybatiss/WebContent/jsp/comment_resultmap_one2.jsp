<%@page import="service.CommentMapperService"%>
<%@page import="model.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%
//XML만으로 매핑구문과 결과매핑처리
//CommentMapperResultMap.xml의 selectCommentByPrimaryKeyAssociation매핑구문
Long commentNo = 3l;
CommentMapperService commentService = new CommentMapperService();
Comment comment = commentService.selectCommentByPrimaryKey(commentNo);
%> 

댓글 번호 : <%= comment.getCommentNo() %><br>
작성자 아이디 : <%= comment.getUserId() %><br>
작성일시 : <%= comment.getRegDate() %><br>
댓글내용 : <%= comment.getCommentContent() %><br>

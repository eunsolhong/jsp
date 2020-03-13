<%@page import="model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="service.CommentDynamicSqlService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//XML만으로 매핑구문과 결과매핑처리
//CommentMapperResultMap.xml의 selectCommentByPrimaryKeyAssociation매핑구문
Long commentNo = 3l;
CommentDynamicSqlService commentService = new CommentDynamicSqlService();
List<Comment> comments = commentService.selectCommentByCondition(commentNo);
%> 

<%
for( Comment comment : comments){ %>
댓글 번호 : <%= comment.getCommentNo() %><br>
작성자 아이디 : <%= comment.getUserId() %><br>
작성일시 : <%= comment.getRegDate() %><br>
댓글내용 : <%= comment.getCommentContent() %><br>
<%}%>
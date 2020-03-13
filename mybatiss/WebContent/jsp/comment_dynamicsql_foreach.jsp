<%@page import="model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="service.CommentDynamicSqlService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String[] commentNos = {"2", "3", "4"};
CommentDynamicSqlService commentService = new CommentDynamicSqlService();
List<Comment> comments = commentService.selectCommentForeach(commentNos);
%> 

<%
for( Comment comment : comments){ %>
댓글 번호 : <%= comment.getCommentNo() %><br>
작성자 아이디 : <%= comment.getUserId() %><br>
작성일시 : <%= comment.getRegDate() %><br>
댓글내용 : <%= comment.getCommentContent() %><br>
<%}%>
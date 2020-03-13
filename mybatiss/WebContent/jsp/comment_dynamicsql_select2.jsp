<%@page import="model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="service.CommentDynamicSqlService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//XML만으로 매핑구문과 결과매핑처리
//CommentMapperResultMap.xml의 selectCommentByPrimaryKeyAssociation매핑구문
Long commentNo = null;
String userId = "user2";
CommentDynamicSqlService commentService = new CommentDynamicSqlService();
/* List<Comment> comments = commentService.selectCommentByConditionIf(commentNo, userId); */
List<Comment> comments = commentService.selectCommentByConditionChoose(commentNo, userId); 

%> 

<%
for( Comment comment : comments){ %>
댓글 번호 : <%= comment.getCommentNo() %><br>
작성자 아이디 : <%= comment.getUserId() %><br>
작성일시 : <%= comment.getRegDate() %><br>
댓글내용 : <%= comment.getCommentContent() %><br>
<%}%>
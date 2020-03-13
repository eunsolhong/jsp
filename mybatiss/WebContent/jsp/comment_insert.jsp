<%@page import="java.util.Calendar"%>
<%@page import="service.CommentMapperService"%>
<%@page import="model.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%
//XML만으로 매핑구문과 결과매핑처리
//CommentMapperResultMap.xml의 selectCommentByPrimaryKeyAssociation매핑구문
Comment comment = new Comment();
comment.setCommentNo(9l);
comment.setUserId("fromm0");
comment.setCommentContent("test");
comment.setRegDate(Calendar.getInstance().getTime());

CommentMapperService commentMapperService = new CommentMapperService();
Integer result = commentMapperService.insertComment(comment);

%> 

<%= result %>

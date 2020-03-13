<%@page import="java.util.Calendar"%>
<%@page import="service.CommentMapperService"%>
<%@page import="model.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%
//XML만으로 매핑구문과 결과매핑처리
//CommentMapperResultMap.xml의 selectCommentByPrimaryKeyAssociation매핑구문

Long commentNo = 9l;
CommentMapperService commentMapperService = new CommentMapperService();
Integer result = commentMapperService.deleteComment(commentNo);

%> 

<%= result %>

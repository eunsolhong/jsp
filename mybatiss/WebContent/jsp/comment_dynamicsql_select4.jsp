<%@page import="model.User"%>
<%@page import="model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="service.CommentDynamicSqlService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//XML만으로 매핑구문과 결과매핑처리
//CommentMapperResultMap.xml의 selectCommentByPrimaryKeyAssociation매핑구문

String userId = "user2";
CommentDynamicSqlService commentService = new CommentDynamicSqlService();
/* List<Comment> comments = commentService.selectOgnlStaticMethod(); */
 List<User> users = commentService.selectOgnlStaticMethod2(userId); 
%> 

<%
for( User user : users){ %> <br>
	회원id : <%= user.getUserId() %> <br>
	회원이름 : <%= user.getUserName() %> <br>

<%}%>



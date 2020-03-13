<%@page import="model.Comment"%>
<%@page import="service.CommentResultMapService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//XML만으로 매핑구문과 결과매핑처리
//CommentMapperResultMap.xml의 selectCommentByPrimaryKeyAssociation매핑구문
Long commentNo = 3l;
CommentResultMapService commentService = new CommentResultMapService();
Comment comment = commentService.selectCommentByPrimaryKeyAssociation2(commentNo);

%> 
댓글 번호 : <%= comment.getCommentNo() %><br>
댓글작성자 : <%= comment.getUserId() %><br>
댓글작성일시 : <%= comment.getRegDate() %><br>
댓글내용 : <%= comment.getCommentContent() %> <br>
댓글작성자명 : <%= comment.getUserName() %><br>
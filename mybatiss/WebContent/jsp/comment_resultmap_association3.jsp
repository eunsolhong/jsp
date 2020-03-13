<%@page import="service.CommentMapperService"%>
<%@page import="model.CommentUser"%>
<%@page import="service.CommentResultMapService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//XML만으로 매핑구문과 결과매핑처리
//CommentMapperResultMap.xml의 selectCommentByPrimaryKeyAssociation매핑구문
Long commentNo = 2l;
CommentMapperService commentService = new CommentMapperService();
CommentUser commentUser = commentService.selectCommentByPrimaryKeyAssociation3(commentNo);

%> 
댓글 번호 : <%= commentUser.getComment().getCommentNo() %><br>
작성자 아이디 : <%= commentUser.getComment().getUserId() %><br>
작성일시 : <%= commentUser.getComment().getRegDate() %><br>
댓글내용 : <%= commentUser.getComment().getCommentContent() %><br>

댓글작성자 : <%= commentUser.getUser().getUserId() %><br>
댓글작성자명 : <%= commentUser.getUser().getUserName() %><br>
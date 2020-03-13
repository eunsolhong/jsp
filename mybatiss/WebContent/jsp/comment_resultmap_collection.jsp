<%@page import="model.Reply"%>
<%@page import="model.Comment"%>
<%@page import="service.CommentResultMapService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//XML만으로 매핑구문과 결과매핑처리
//CommentMapperResultMap.xml의 selectCommentByPrimaryKeyAssociation매핑구문
Long commentNo = 3l;
CommentResultMapService commentService = new CommentResultMapService();
Comment comment = commentService.selectCommentByPrimaryKeyCollection(commentNo);

%> 
댓글 번호 : <%= comment.getCommentNo() %><br>
댓글작성자 : <%= comment.getUserId() %><br>
댓글작성일시 : <%= comment.getRegDate() %><br>
댓글내용 : <%= comment.getCommentContent() %> <br>

~ ~ 여 기 서 부 터 답 글 목 록 ~ ~
<%for(Reply reply : comment.getReplies()) { %>
답글번호 : <%= reply.getReplyNo() %><br>
답글작성자 : <%= reply.getUserId() %><br>
답글작성일시 : <%= reply.getRegDate()%><br>
답글내용 : <%= reply.getReplyContent() %><br>
<% } %>
<!-- 데이터를 보여주는 역할 -->

<%@page import="board.BoardDataBean"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>content</title>

<style>

.w3-table-all {
margin : 0 auto;
width: 500px;
}

table td:nth-child(1){
width : 90px;
}


table td:nth-child(2){
width : 80px;
}

</style>
</head>

<body>
<%

int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
if(pageNum == null || pageNum.equals("")) { pageNum = "1";}

BoardDao dbPro = BoardDao.getInstance();
BoardDataBean article = dbPro.getArticle(num);
%>


<div class="w3-container">
<br>
  <table class="w3-table-all" >
   
    <tr>
      <td  >번호</td> <td ><%=article.getNum() %></td>
      <td>작성일</td> <td><%=article.getReg_date() %></td>
       <td >조회</td><td><%=article.getReadcount() %></td>
     </tr>
     
     <tr>
      
       <td >이메일</td> <td><%=article.getEmail() %></td>
      <td>Ip </td> <td><%=article.getIp() %></td>
       <td>비밀번호</td><td><%=article.getPasswd() %></td>
    </tr>
    
        <tr>
       <td >작성자</td> <td><%=article.getWriter() %></td>
     </tr>


     
      <tr>
      <td> >>제목 </td>
      <td colspan="5"><%=article.getSubject() %></td>
          </tr>
          
          <tr>     
      <td>내용</td>
      <td colspan="5"><%=article.getContent() %></td>
</tr>
     
     <tr>     
      <td> >이미지 </td>
      <td colspan="5">
      <img src= "<%=request.getContextPath()%>/uploadFile/<%=article.getFilename() %>" width="400px"/></td>
          </tr>
          
     
 
 
</div>
</table>

<br>
<br>

<div class="button">
<center><input type="button" value="글수정" onclick="document.location.href='updateForm.jsp?num=<%=article.getNum()%>&pageNum<%=pageNum%>'">&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="글삭제" onclick="document.location.href='deleteForm.jsp?num=<%=article.getNum()%>'">&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="답글쓰기" onclick="document.location.href='writeForm.jsp?num=<%=article.getNum()%>&ref=<%=article.getRef()%>&re_level=<%=article.getRe_level()%>&re_step=<%=article.getRe_step()%>'">&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="글목록" onclick="document.location.href='list.jsp'">&nbsp;&nbsp;&nbsp;&nbsp;</center>
</div>
</body>
</html>
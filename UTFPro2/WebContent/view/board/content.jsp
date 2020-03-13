<!-- 데이터를 보여주는 역할 -->

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

<div class="w3-container">
<br>
  <table class="w3-table-all" >
   
    <tr>
      <td  >번호</td> <td >${article.num}</td>
      <td>작성일</td> <td>${article.reg_date}</td>
       <td >조회</td><td>${article.readcount} </td>
     </tr>
     
     <tr>
      
       <td >이메일</td> <td>${article.email}</td>
      <td>Ip </td> <td>${article.ip}</td>
       <td>비밀번호</td><td>${article.passwd}</td>
    </tr>
    
        <tr>
       <td >작성자</td> <td>${article.writer}</td>
     </tr>


     
      <tr>
      <td> >>제목 </td>
      <td colspan="5">${article.subject}</td>
          </tr>
          
          <tr>     
      <td>내용</td>
      <td colspan="5">${article.content}</td>
</tr>
     
     <tr>     
      <td> >이미지 </td>
      <td colspan="5">
      <img src= "<%=request.getContextPath()%>/uploadFile/${article.filename}" width="400px"/></td>
          </tr>
          
     
 
 
</div>
</table>

<br>
<br>

<div class="button">
<center><input type="button" value="글수정" onclick="document.location.href='updateForm?num=${article.num}'">&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="글삭제" onclick="document.location.href='deleteForm?num=${article.num}'">&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="답글쓰기" onclick="document.location.href='writeForm?num=${article.num}&ref=${article.ref}&re_level=${article.re_level}&re_step=${article.re_step}'">&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="글목록" onclick="document.location.href='list'">&nbsp;&nbsp;&nbsp;&nbsp;</center>
</div>
</body>
</html>
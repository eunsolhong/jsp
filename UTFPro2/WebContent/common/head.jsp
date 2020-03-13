<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>게시판 테스트</title>
<head>
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap&subset=korean" rel="stylesheet">
</head>
<style>
.w3-bar-item-w3-button{text-decoration:none;}
*{font-family: 'Poor Story', cursive;}

</style>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<script>
window.onload = function () {
	var atag = document.getElementsByTagName("a");
	var url = document.location.href.split("/");
	alert(url[url.length-1]);
	
	if(url[url.length-1]=="main.jsp"){
		atag[0].className += " w3-black";
	}
	else if(url[url.length-1]=="inputForm"){
		atag[4].className +=" w3-black";
	}
	else if(url[url.length-1]=="loginForm"){
		atag[7].className += " w3-black";
	}
	else if(url[url.length-1]=="loginout"){
		atag[8].className += " w3-black";
	}
	else if(url[url.length-1]=="bar_sample"){
		atag[5].className += " w3-black";
	}	
	else if(url[url.length-1]=="awesomeCloud"){
		atag[6].className += " w3-black";
	}	
	
	else if(1==<%=session.getAttribute("boardid")%>){
		atag[1].className += " w3-black ";
	}
	else if(2==<%=session.getAttribute("boardid")%>){
		atag[2].className += " w3-black ";
	}
	else if(3==<%=session.getAttribute("boardid")%>){
		atag[3].className += " w3-black ";
	}
	
}

</script>

<body>

<div class="w3-bar w3-yellow w3-large">
<a href = "<%=request.getContextPath()%>/member/main"
class="w3-bar-item w3-button">HOME</a>
<a href = "<%=request.getContextPath()%>/board/list?boardid=1" class="w3-bar-item w3-button" > Notice</a>
<a href = "<%=request.getContextPath()%>/board/list?boardid=2" class="w3-bar-item w3-button"> Board </a>
<a href = "<%=request.getContextPath()%>/board/list?boardid=3"  class="w3-bar-item w3-button"> Q&A </a>
<a href = "<%=request.getContextPath()%>/member/inputForm"
class = "w3-bar-item w3-button">Join</a>  
<a href = "<%=request.getContextPath()%>/chart/bar_sample2" class="w3-bar-item w3-button"> BarGraph</a>
<a href = "<%=request.getContextPath()%>/chart/awesomeCloud" class="w3-bar-item w3-button"> WordCloud</a>

<% if(session.getAttribute("memID")==null) { %>
<a href = "<%=request.getContextPath()%>/member/loginForm"
class = "w3-bar-item w3-button">Login</a>

<% } else { %>
<span class = "w3-bar-item w3-button"> <%=session.getAttribute("memID") %></span>
<a href = "<%=request.getContextPath()%>/member/loginout"
class="w3-bar-item w3-button"><%= session.getAttribute("memID") %> : Logout</a>
<% } %>
<span> <%=session.getAttribute("boardid") %></span>

</div>
</body>
</html>
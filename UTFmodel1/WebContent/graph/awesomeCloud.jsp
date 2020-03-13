<%@page import="java.util.Set"%>
<%@page import="visualChart.WordCloud"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"><![endif]-->
<!--[if IE 7]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if IE 8]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<title>jQuery awesomeCloud Plug in Demos</title>
<meta name="description" content="A Word Cloud plugin for jQuery.">
<meta name="viewport" content="width=device-width,initial-scale=1">



<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery.awesomeCloud-0.2.js"></script>
<style type="text/css">
.wordcloud {
	border: 1px solid #036;
	height: 7in;
	margin: 0.5in auto;
	padding: 0;
	page-break-after: always;
	page-break-inside: avoid;
	width: 7in;
}
</style>
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css">
</head>
<%
String url = null;
String selector = null;
int size = 5;

if(request.getParameter("url")!= null && !request.getParameter("url").equals("")){
	url = request.getParameter("url");
	selector = request.getParameter("selector");
	size = Integer.parseInt(request.getParameter("size"));
}

if(url == null) url="https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=105&oid=032&aid=0002987102";
if(selector == null) selector = "div[class~=_article_body_contents]";

Map<String, Long> map = WordCloud.map(url, selector, 30);
Set<Map.Entry<String, Long>> set = map.entrySet();
int cnt = set.size();

%>

<body>

<div class = "w3-cotainer">
<form name = "f" class="w3-container w3-border" style="width:70%;" method="post">
<label> URL : 분석하고 싶은 URL을 입력하세요</label>
<input class= "w3-input" type="text" name = "url" 
value="<%=url %>">
<label>선택자 : 아래 내용은 naver의 선택내용입니다.</label><br>

<input class="w3-input w3-brown" type="text" name="selector" 
value="<%=selector%>">
<label>사이즈</label>
<input class="w3-input w3-brown" type="text" name="size"
value="<%=size%>">
<input class="w3-input" type="submit" value="wordcloud 출력하기">

</form></div>
	<div role="main">
<div id = "wordcloud2" class = "wordcloud">


<%


for(Map.Entry<String, Long> m :set){

if(m.getValue()>=1){
%>	
<span      data-weight="<%=m.getValue()*size%>"><%=m.getKey() %></span>

	
<%}}%>


</div>

	<script>
			$("#wordcloud2").awesomeCloud({
				"size" : {
					"grid" : 9,
					"factor" : 1
				},
				"options" : {
					"color" : "random-dark",
					"rotationRatio" : 0.35
				},
				"font" : "'Times New Roman', Times, serif",
				"shape" : "circle"
			});
			
	
	</script>
	<!--[if lt IE 7 ]>
		<script src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js"></script>
		<script>window.attachEvent('onload',function(){CFInstall.check({mode:'overlay'})})</script>
		<![endif]-->

	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-36251023-1' ]);
		_gaq.push([ '_setDomainName', 'jqueryscript.net' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
</body>
</html>

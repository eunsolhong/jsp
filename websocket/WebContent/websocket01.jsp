<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <link rel="stylesheet" href="http://www.w3schools.com/w3css/4/w3.css">
<%
   String name = request.getParameter("name");
   if(name == null)
      name = "무명";
   //두개의 스레드가 공유하지 못하게 하는 것: synchronized
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Testing websockets</title>
</head>
<style>
#me{
   position: relative;
   left:100px;
}
#you{
   position: relative;
   left: 10px;
}
</style>
<body>
   <fieldset>
      <div class="w3-pale-blue" id="messageWindow" style="width: 500px; height: 300px; border: 1px solid grey; overflow: auto;">
      </div>
      <br/>
      <input type="text" id="inputMessage" /> <input type="submit" value="send" onclick="send()" />
   </fieldset>
</body>

<script type="text/javascript">
   var textarea = document.getElementById("messageWindow");
   var webSocket = new WebSocket('ws://211.63.89.29:8080<%=request.getContextPath()%>/weball');
   var inputMessage = document.getElementById('inputMessage');
   
   //webSocket오픈-> 무엇을 처리할지 직접 지정해주는게 아니라 webSocket 상태에 따라 알아서-> 연결안되면 onerror, 연결되면 onopen. 
   //누군가 메세지를 전송-> onmessage.
   //비동기식 처리.
   webSocket.onerror = function(event) {
      onError(event)
   };
   webSocket.onopen = function(event) {
      onOpen(event)
   };
   webSocket.onmessage = function(event) {
      onMessage(event)
   };
   
   function onMessage(event) {
      textarea.innerHTML += "<div id='you' class='w3-white w3-border w3-round-large w3-padding-small' style='width:"+(event.data.length*12)+"px;'>"+event.data + "</div><br>";
      textarea.scrollTop = textarea.srollHeight;
   }
   function onOpen(event){
      textarea.innerHTML += "연결 성공<br>";
      webSocket.send("<%=name%>:입장 하였습니다.");
   }
   function onError(event){
      alert(event.data);
   }
   function send() {
      textarea.innerHTML += "<div class='w3-yellow w3-border w3-round-large w3-padding-small' "
      + " id='me' style='width:"+((inputMessage.value.length*12)+45)+"px;'>나: "
      + inputMessage.value + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br>";
      
      textarea.scrollTop = textarea.scrollHeight;
      webSocket.send("<%=name%>:" + inputMessage.value);
      inputMessage.value="";
   }
</script>

</html>
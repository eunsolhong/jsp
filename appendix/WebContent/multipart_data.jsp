<%@page import="java.io.InputStream"%>
<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page trimDirectiveWhitespaces="true" %>
<%
InputStream is = null;

out.print("[");
out.print(request.getContentType());
out.println("]");
try{
	is = request.getInputStream();
	int data = -1;
	
	while((data = is.read()) != -1){
		out.print((char)data);
	}
} finally {
	if( is!=null) try{is.close();} catch(IOException ex) {}
}

%>
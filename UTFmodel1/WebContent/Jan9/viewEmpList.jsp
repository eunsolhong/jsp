<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>EMP목록</title>
</head>
<body>

EMP테이블의 내용
<table width="50%" border="1">
<tr>
<td>EMPNO</td><td>ENAME</td><td>JOB</td>
</tr>
<%
//1.JDBC 드라이버 로딩
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try{
	String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:orcl";
	String dbUser = "scott";
	String dbPass ="1111";
	String query = "select empno, ename, job from emp";
	
	//2. 데이터베이스 커넥션 생성
	conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
	
	//3. Statement  생성
	stmt = conn.createStatement();
	
	//4. 쿼리 실행
	rs = stmt.executeQuery(query);

	//5. 쿼리 실행결과 출력
	while(rs.next()){
		%>
	<tr>
	<td><%= rs.getInt("empno") %></td> 
	<td><%= rs.getString("ename") %></td>
	<td><%= rs.getString("job") %></td>
	</tr>
	<%
	}
}catch(SQLException ex){
	out.println(ex.getMessage());
	ex.printStackTrace();
} finally{
	//6. 사용한 Statement 종료
	if(rs!=null) try{ rs.close(); } catch(SQLException ex){}
	if(stmt!=null) try{ stmt.close(); } catch(SQLException ex){}
	
	//7.커넥션 종료
	if(conn != null) try{conn.close();} catch(SQLException ex){}
}
	%>
</table>
</body>
</html>
package member;

import java.sql.*;


//모든 컨넥션 관련 쿼리를 모아놓은거(DB관련) 

public class LogonDao {

		//싱글톤 : 접근못함(메모리관리조음) static잡아놓고 다오에서 한번만만들어서 가져다쓰지만 하나만가져다쓰는거.
	private static LogonDao instance = new LogonDao();

	public static LogonDao getInstance() {
		return instance;
	}

	private LogonDao() {
	}

	private Connection getConnection() throws Exception {
		// DB를 연결해서 가져오는거
		Connection conn = null;
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbId = "scott";
		String dbPass = "1111";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		return conn;
	}

	public void insertMember(LogonDataBean member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null; //보안 하오.

		try {

			conn = getConnection();

			pstmt = conn.prepareStatement("insert into MEMBER values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getJumin1());
			pstmt.setString(5, member.getJumin2());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getBlog());
			pstmt.setTimestamp(8, member.getReg_date());
			pstmt.executeUpdate(); //몇개가 실행되었는지 숫자를 리턴해서 보여줌
			
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	public int confirmId(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;  //쿼리로 인한 결과값 리턴값 (그릇)
		int x = -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select id from MEMBER where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				x = 1;
			else
				x = -1;

		} catch (Exception ex) {
			ex.printStackTrace();
			// TODO: handle exception
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	public int userCheck(String id, String passwd) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		int x = -1;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select PWD from MEMBER where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbpasswd = rs.getString("pwd");
				if (dbpasswd.equals(passwd))
					x = 1;
				else
					x = 0;
			} else
				x = -1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)	try {rs.close(); } catch (SQLException ex) {}
			if (pstmt != null)	try {pstmt.close();	} catch (SQLException ex) {	}
			if (conn != null)	try {conn.close();} catch (SQLException ex) {}
		}
		return x;
	}
	
	public LogonDataBean getMember(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean member = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from MEMBER where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = new LogonDataBean();
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("pwd"));
				member.setName(rs.getString("name"));
				member.setJumin1(rs.getString("jumin1"));
				member.setJumin2(rs.getString("jumin2"));
				member.setEmail(rs.getString("email"));
				member.setBlog(rs.getString("blog"));
				member.setReg_date(rs.getTimestamp("reg_date"));
			}
		} catch (Exception ex) { ex.printStackTrace(); 
		} finally {
			if (rs != null) try {rs.close(); } catch (SQLException ex) {}
			if (pstmt != null)	try {pstmt.close();	} catch (SQLException ex) {	}
			if (conn != null) try {conn.close();} catch (SQLException ex) {}
		}
		return member;
	}
	
	public void updateMember(LogonDataBean member){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConnection();

			pstmt = conn.prepareStatement("update MEMBER set name = ? , email = ?, blog = ? where id = ?");
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getBlog());
			pstmt.setString(4, member.getId());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
		}
	}
	
	public int deleteMember(String id, String passwd){
		
		//리턴값으로 delete된 걸 알려줘야함 
		//executeUpdate의 실행값을 받아서  잘되면 1..?안되면..-1...?훔
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		int x = -1;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select PWD from MEMBER where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbpasswd = rs.getString("pwd");
				if (dbpasswd.equals(passwd)){
					pstmt = conn.prepareStatement("delect from MEMBER where id=?");
					pstmt.setString(1, id);
					x= pstmt.executeUpdate();
					
				}
				else
					x = 0; 
			} else
				x = -1; 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)	try {rs.close(); } catch (SQLException ex) {}
			if (pstmt != null)	try {pstmt.close();	} catch (SQLException ex) {	}
			if (conn != null)	try {conn.close();} catch (SQLException ex) {}
		}
		return x;
	}
	
	

	}
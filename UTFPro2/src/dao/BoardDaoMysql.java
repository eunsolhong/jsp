package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.BoardDataBean;


public class BoardDaoMysql {

	//싱글톤 생성 
		private static BoardDaoMysql instance = new BoardDaoMysql();

		public static BoardDaoMysql getInstance() {
			return instance;
		}

		public static Connection getConnection() throws Exception {
			// DB연결해서 가져오는 거 
			Connection con = null;
			
			try{
				String jdbcUrl = "jdbc:mysql://localhost:3306/mysql";
				String dbId = "scott";
				String dbPass = "1111";
				Class.forName("com.mysql.jdbc.Driver");
				
				con= DriverManager.getConnection(jdbcUrl, dbId, dbPass);
				
			}catch (Exception e) { e.printStackTrace(); }
			return con;
		}

public void insertArticle(BoardDataBean article){
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	int number = 0;
	int num = article.getNum(); //num=null이면 새, num!= 답글 
	int ref = article.getRef(); 
	int re_step = article.getRe_step();
	int re_level = article.getRe_level();
		

	try {
		
		conn = getConnection();
		
		String sql = "select max(num)+1 from board";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			number =rs.getInt(1); //boardser.nextval
		}
		
		if(num!=0){
			
			sql = "update board set re_step = re_step+1 where ref = ? and re_step > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_step);
			pstmt.executeUpdate();

			re_step=re_step+1;
			re_level=re_level+1;
		} else{ ref=number;} //boardser.currval답글 ->ref
		
		
		sql = "insert into board values(?," + "?,?,?,?,?,now(),0,?,?,?,?,?,?, ?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, number);
		pstmt.setString(2, article.getBoardid());
		pstmt.setString(3, article.getWriter());
		pstmt.setString(4, article.getEmail());
		pstmt.setString(5, article.getSubject());
		pstmt.setString(6, article.getPasswd());
		pstmt.setInt(7, ref);
		pstmt.setInt(8, re_step);
		pstmt.setInt(9, re_level);
		pstmt.setString(10, article.getContent());
		pstmt.setString(11, article.getIp());
		pstmt.setString(12, article.getFilename());
		pstmt.setInt(13, article.getFilesize());
		pstmt.executeUpdate();
		
		
	} catch (Exception ex) { ex.printStackTrace(); 
			} finally {
				if (rs != null) try {rs.close(); } catch (SQLException ex) {}
				if (pstmt != null)	try {pstmt.close();	} catch (SQLException ex) {	}
				if (conn != null) try {conn.close();} catch (SQLException ex) {}
			}
	}//class end


public int getArticleCount(String boardid){
	
	int count =0;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		
		conn = getConnection();
		
		String sql = "select ifnull(count(*),0) from board where boardid = ? ";
		
		pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1, boardid);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			count=rs.getInt(1);}
		
		
		
	} catch (Exception ex) {
		ex.printStackTrace(); 
			} finally {
				if (rs != null) try {rs.close(); } catch (SQLException ex) {}
				if (pstmt != null)	try {pstmt.close();	} catch (SQLException ex) {	}
				if (conn != null) try {conn.close();} catch (SQLException ex) {}
			}
	return count;
}


	public List<BoardDataBean> getArticles(int startRow, int endRow, String boardid){
		
		List<BoardDataBean> articleList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		//limit 시작번호, 가져올 수 limit 가져올 수
		// 시작번호는 0부터임 
		startRow = startRow -1;
		endRow = endRow - startRow;
		System.out.println("dao===" + startRow + ":" + endRow + ":" + boardid);
		
		
		try {
			
			conn = getConnection();
			
			String sql = " select * from board where boardid = ? "
					+ " order by ref desc, re_step limit ? , ?";
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, boardid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
		
			
			
			if(rs.next()) {
			
				articleList = new ArrayList<BoardDataBean>();
				
				do{
					BoardDataBean article = new BoardDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					
					article.setFilename(rs.getString("filename"));
					article.setFilesize(rs.getInt("filesize"));
					
					articleList.add(article);					
				}while(rs.next());
				
			
			}
					
			
		} catch (Exception ex) {
			ex.printStackTrace(); 
				} finally {
					if (rs != null) try {rs.close(); } catch (SQLException ex) {}
					if (pstmt != null)	try {pstmt.close();	} catch (SQLException ex) {	}
					if (conn != null) try {conn.close();} catch (SQLException ex) {}
				}
		return articleList;
	}
	
	public BoardDataBean getArticle(int num){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		
		try {
			conn = getConnection();
			
			
			pstmt = conn.prepareStatement(" update board set readcount=readcount +1 where num = ? "); 
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			String sql = " select * from board where num = ? ";
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					article = new BoardDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setIp(rs.getString("ip"));
					article.setFilename(rs.getString("filename"));
				
				}			
		} catch (Exception ex) {
			ex.printStackTrace(); 
				} finally {
					if (rs != null) try {rs.close(); } catch (SQLException ex) {}
					if (pstmt != null)	try {pstmt.close();	} catch (SQLException ex) {	}
					if (conn != null) try {conn.close();} catch (SQLException ex) {}
				}
		return article;
	}
	
	public BoardDataBean getUpdateArticle(int num){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		
		try {
			conn = getConnection();
		
			String sql = " select * from board where num = ? ";
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					 article = new BoardDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
				}			
		} catch (Exception ex) {
			ex.printStackTrace(); 
				} finally {
					if (rs != null) try {rs.close(); } catch (SQLException ex) {}
					if (pstmt != null)	try {pstmt.close();	} catch (SQLException ex) {	}
					if (conn != null) try {conn.close();} catch (SQLException ex) {}
				}
		return article;
		
	}
	
	//게시판 글 수정 메소드 
	// 순서 : updateForm -> updatePro -> BoardDao 수정 

	public int updateArticle(BoardDataBean article) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		String sql = "";
		int x = -1;

		try {

			//연결 
			conn = getConnection();
			
			
			//패스워드 체크체크 
			pstmt = conn.prepareStatement(" select passwd from board where num = ?");
			
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				dbpasswd = rs.getString("passwd");
				
			if(dbpasswd.equals(article.getPasswd())){
			
			//글 수정 쿼리 
			sql =" update board set writer = ?, email = ?, subject = ?, passwd = ?";
					sql += ",content = ? where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPasswd());
			pstmt.setString(5, article.getContent());
			pstmt.setInt(6, article.getNum());
			pstmt.executeUpdate();
			x=1;}
			else {
				x=0;
			}
		}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
		}
return x;
	}
	
	
	
	public int deleteArticle(int num, String passwd) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		String sql = "";
		int x = -1;

		try {

			//연
			conn = getConnection();
			
			
			//패스워드 체크체
			pstmt = conn.prepareStatement(" select passwd from board where num = ?");
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				dbpasswd = rs.getString("passwd");
				
			if(dbpasswd.equals(passwd)){
	
			sql =" delete from board where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			x=1;}
			else {
				x=0;
			}
		}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
		}
return x;
	}
}//class end
package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDao;
import exception.InvalidPasswordException;
import exception.MemberNotFoundException;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil2;
import model.Member;

public class ChangePasswordService {

	private MemberDao memberDao = new MemberDao();
	
	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectById(conn, userId);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			if(!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			member.changePassword(newPwd);
			memberDao.update(conn, member);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil2.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil2.close(conn);
		}
	}
}

package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MessageDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import model.Message;

public class DeleteMessageService {

	private static DeleteMessageService instance
	= new DeleteMessageService();
	
	public static DeleteMessageService getInstance(){
		return instance;
	}
	
	private DeleteMessageService(){}
	
	public void deleteMessage(int messageId, String password){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			MessageDao messageDao = MessageDao.getInstance();
			Message message = messageDao.select(conn, messageId);
			if(message == null){
				throw new MessageNotFoundException("MessageNotFoundException");
			}
			if(!message.matchPassword(password)){
				throw new InvalidPasswrdException("bad password");
			}
			messageDao.delete(conn, messageId);
			conn.commit();
		} catch (SQLException ex) {
			JdbcUtil.rollback(conn);
			throw new ServiceException("ServiceException" + ex.getMessage(), ex);
		} catch (InvalidPasswrdException | MessageNotFoundException ex) {
			JdbcUtil.rollback(conn); 
			throw ex;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ArticleContentDao;
import dao.ArticleDao;
import exception.ArticleNotFoundException;
import exception.PermissionDeniedException;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil2;
import model.Article;
import model.User;

public class DeleteArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public void delete(User authUser, int no) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			Article article = articleDao.selectById(conn, no);
			if(article == null) {
				throw new ArticleNotFoundException();
			} 
			if(!canModify(authUser.getId(), article)) {
				throw new PermissionDeniedException();
			}
			articleDao.delete(conn, no);
			contentDao.delete(conn, no);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil2.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil2.rollback(conn);
			throw e;
		} finally {
			JdbcUtil2.close(conn);
		}
	}
	
	private boolean canModify(String modifyingUserId, Article article) {
		return article.getWriter().getId().equals(modifyingUserId);
	}
}

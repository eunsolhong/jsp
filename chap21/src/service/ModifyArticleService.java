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

public class ModifyArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn, modReq.getArticleNumber());
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			if(!canModify(modReq.getUserId(), article)) {
				throw new PermissionDeniedException();
			}
			
		articleDao.update(conn,modReq.getArticleNumber(), modReq.getTitle());
		contentDao.update(conn, modReq.getArticleNumber(), modReq.getContent());
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

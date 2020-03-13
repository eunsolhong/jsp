package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ArticleContentDao;
import dao.ArticleDao;
import exception.ArticleContentNotFoundException;
import exception.ArticleNotFoundException;
import jdbc.ConnectionProvider;
import model.Article;
import model.ArticleContent;

public class ReadArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public ArticleData getArticle(int articleNum, boolean increaseReadCount) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			Article article = articleDao.selectById(conn, articleNum);
			if(article == null ) {
				throw new ArticleNotFoundException();
				}
			ArticleContent content = contentDao.selectById(conn, articleNum);
			if(content == null) {
				throw new ArticleContentNotFoundException();
			}
			if(increaseReadCount) {
				articleDao.increaseReadCount(conn, articleNum);
			}
			return new ArticleData(article, content);
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}

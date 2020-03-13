package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.BoardDataBean;
import mybatis.AbstractRepository;

public class MybatisBoardDaoMysql extends AbstractRepository {

	private final String namespace = "mybatis.Board";

	private static MybatisBoardDaoMysql instance = new MybatisBoardDaoMysql();

	public static MybatisBoardDaoMysql getInstance() {
		return instance;
	}

	private MybatisBoardDaoMysql() {
	}

	public int getArticleCount(String boardid) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".getArticleCount";
			return sqlSession.selectOne(statement, boardid);
		} finally {
			sqlSession.close();
		}
	}

	public List<BoardDataBean> getArticles(int startRow, int endRow, String boardid) {

		startRow = startRow - 1;
		endRow = endRow - startRow;

		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("boardid", boardid);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".getArticles";
			return sqlSession.selectList(statement, map);
		} finally {
			sqlSession.close();
		}
	}

	public void insertArticle(BoardDataBean article) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();

		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		int number = 1;

		try {
			number = sqlSession.selectOne(namespace + ".insert_max");

			if (num != 0) {
				map.put("ref", ref);
				map.put("re_step", re_step);
				re_step = re_step + 1;
				re_level = re_level + 1;

				String statement = namespace + ".insert_update";
				sqlSession.update(statement, map);
				sqlSession.commit();

			} else {
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			article.setNum(number);
			article.setRef(ref);
			article.setRe_level(re_level);
			article.setRe_step(re_step);

			String statement = namespace + ".insert";
			sqlSession.insert(statement, article);
			sqlSession.commit();
		} finally

		{
			sqlSession.close();

		}
	}

	public BoardDataBean getArticle(int num) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		BoardDataBean article = null;

		try {
			sqlSession.update(namespace + ".content_update", num);
			sqlSession.commit();

			article = sqlSession.selectOne(namespace + ".getArticle", num);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return article;
	}

	public BoardDataBean getUpdateArticle(int num) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		BoardDataBean article = null;
		try {
			sqlSession.selectOne(namespace + ".getArticle", num);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return article;
	}

	public int updateArticle(BoardDataBean article) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String dbpasswd = "";
		String sql = "";
		int x = -1;

		try {
			dbpasswd = sqlSession.selectOne(namespace + ".getPasswd", article.getNum());
			if (dbpasswd.equals(article.getPasswd())) {
				sqlSession.update(namespace + ".update_update", article);
				sqlSession.commit();
				x = 1;
			} else {
				x = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
		System.out.println(x);
		return x;
	}

	public int deleteArticle(int num, String passwd) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String dbpasswd = null;
		int x = -1;

		try {

			dbpasswd = sqlSession.selectOne(namespace + ".getPasswd", num);

			if (dbpasswd.equals(passwd)) {
				sqlSession.delete(namespace + ".delete", num);
				sqlSession.commit();
				x = 1;
			} else {
				x = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return x;
	}

}
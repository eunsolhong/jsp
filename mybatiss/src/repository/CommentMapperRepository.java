package repository;

import org.apache.ibatis.session.SqlSession;

import model.Comment;
import model.CommentUser;
import service.AbstractRepository;

public class CommentMapperRepository extends AbstractRepository {

	private final String namespace = "mybatiss.mapper.CommentMapperResultMap";

	public Comment selectCommentByPrimaryKey(Long commentNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			String statement = namespace + ".selectCommentByPrimaryKey";
			return (Comment) sqlSession.selectOne(statement, commentNo);
		} finally {
			sqlSession.close();
		}
	}

	public Comment selectCommentByPrimaryKeyConstructor(Long commentNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			String statement = namespace + ".selectCommentByPrimaryKeyConstructor";
			return (Comment) sqlSession.selectOne(statement, commentNo);
		} finally {
			sqlSession.close();
		}
	}

	public CommentUser selectCommentByPrimaryKeyAssociation(Long commentNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			String statement = namespace + ".selectCommentByPrimaryKeyAssociation";
			return (CommentUser) sqlSession.selectOne(statement, commentNo);
		} finally {
			sqlSession.close();
		}
	}

	public Comment selectCommentByPrimaryKeyAssociation2(Long commentNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			String statement = namespace + ".selectCommentByPrimaryKeyAssociation2";
			return (Comment) sqlSession.selectOne(statement, commentNo);
		} finally {
			sqlSession.close();
		}
	}

	public Comment selectCommentByPrimaryKeyCollection(Long commentNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			String statement = namespace + ".selectCommentByPrimaryKeyCollection";
			return (Comment) sqlSession.selectOne(statement, commentNo);
		} finally {
			sqlSession.close();
		}
	}
}

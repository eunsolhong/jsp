package repository;

import org.apache.ibatis.session.SqlSession;
import mapper.CommentMapper;
import model.Comment;
import model.CommentUser;
import service.AbstractRepository;

//어노테이션으로 바꾸는
public class CommentMapperRepository_ano extends AbstractRepository {

	public Comment selectCommentByPrimaryKey(Long commentNo) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Comment result = null;

		try {
			result = sqlSession.getMapper(CommentMapper.class).selectCommentByPrimaryKey(commentNo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}

	public Integer insertComment(Comment comment) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Integer result = null;

		try {
			result = sqlSession.getMapper(CommentMapper.class).insertComment(comment);

			if(result > 0) {
				sqlSession.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}

	
	public Integer deleteComment(Long commentNo) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Integer result = null;

		try {
			result = sqlSession.getMapper(CommentMapper.class).deleteComment(commentNo);

			if(result > 0) {
				sqlSession.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	public CommentUser selectCommentByPrimaryKeyAssociation3(Long commentNo) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		CommentUser result = null;

		try {
			result = sqlSession.getMapper(CommentMapper.class).selectCommentByPrimaryKeyAssociation3(commentNo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	public Comment selectCommentByPrimaryKeyConstructor2(Long commentNo) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Comment result = null;

		try {
			result = sqlSession.getMapper(CommentMapper.class).selectCommentByPrimaryKeyConstructor2(commentNo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return result;
	}
}

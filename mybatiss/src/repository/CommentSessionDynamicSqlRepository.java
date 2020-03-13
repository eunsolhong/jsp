package repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.Comment;
import model.User;
import service.AbstractRepository;

public class CommentSessionDynamicSqlRepository  extends  AbstractRepository{

	private final String namespace = "mybatiss.mapper.CommentMapperDynamic";

	public List<Comment> selectCommentByCondition(Map<String, Object> condition){
		
			SqlSession sqlSession = getSqlSessionFactory().openSession();

			try {
				String statement = namespace + ".selectCommentByCondition";
				return sqlSession.selectList(statement, condition);
			} finally {
				sqlSession.close();
			}
		}
	
	
	public List<Comment> selectCommentByConditionIf(Map<String, Object> condition){
		
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			String statement = namespace + ".selectCommentByConditionIf";
			return sqlSession.selectList(statement, condition);
		} finally {
			sqlSession.close();
		}
	}
	
	
public List<Comment> selectCommentByConditionChoose(Map<String, Object> condition){
		
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			String statement = namespace + ".selectCommentByConditionChoose";
			return sqlSession.selectList(statement, condition);
		} finally {
			sqlSession.close();
		}
	}

public List<Comment> selectCommentByConditionTrim(Map<String, Object> condition){
	
	SqlSession sqlSession = getSqlSessionFactory().openSession();

	try {
		String statement = namespace + ".selectCommentByConditionTrim";
		return sqlSession.selectList(statement, condition);
	} finally {
		sqlSession.close();
	}
}

public List<Comment> selectCommentForeach(Map<String, Object> condition){
	
	SqlSession sqlSession = getSqlSessionFactory().openSession();

	try {
		String statement = namespace + ".selectCommentForeach";
		return sqlSession.selectList(statement, condition);
	} finally {
		sqlSession.close();
	}
}

public List<Comment> selectOgnlStaticMethod(){
	
	SqlSession sqlSession = getSqlSessionFactory().openSession();

	try {
		String statement = namespace + ".selectOgnlStaticMethod";
		return sqlSession.selectList(statement);
	} finally {
		sqlSession.close();
	}
}

public List<Comment> selectOgnlStaticField(){
	
	SqlSession sqlSession = getSqlSessionFactory().openSession();

	try {
		String statement = namespace + ".selectOgnlStaticMethod";
		return sqlSession.selectList(statement);
	} finally {
		sqlSession.close();
	}
}

public List<User> selectOgnlStaticMethod2(Map<String, Object> condition){
	
	SqlSession sqlSession = getSqlSessionFactory().openSession();

	try {
		String statement = namespace + ".selectOgnlStaticMethod2";
		return sqlSession.selectList(statement);
	} finally {
		sqlSession.close();
	}
}
	
}

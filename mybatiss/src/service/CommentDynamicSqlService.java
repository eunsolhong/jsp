package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Comment;
import model.User;
import repository.CommentSessionDynamicSqlRepository;
import repository.CommentMapperRepository;

public class CommentDynamicSqlService {

	public List<Comment> selectCommentByCondition(Long commentNo){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("commentNo", commentNo);
		
		CommentSessionDynamicSqlRepository commentRepository
		= new CommentSessionDynamicSqlRepository();
		
		return commentRepository.selectCommentByCondition(condition);
	}
	
	public List<Comment> selectCommentByConditionIf(Long commentNo, String userId){
		
		Map<String, Object> condition = new HashMap<String, Object>();
		
		
		condition.put("commentNo", commentNo);
		
		User user = new User();
		user.setUserId(userId);
		condition.put("user", user);
		
		CommentSessionDynamicSqlRepository commentRepository
		= new CommentSessionDynamicSqlRepository();
		
		return commentRepository.selectCommentByConditionIf(condition);
	}
	
	
	public List<Comment> selectCommentByConditionChoose(Long commentNo, String userId){
		Map<String, Object> condition = new HashMap<String, Object>();
		
		
		condition.put("commentNo", commentNo);
		
		User user = new User();
		user.setUserId(userId);
		condition.put("user", user);
		
		CommentSessionDynamicSqlRepository commentRepository
		= new CommentSessionDynamicSqlRepository();
		
		return commentRepository.selectCommentByConditionChoose(condition);
	}
	
	public List<Comment> selectCommentByConditionTrim(Long commentNo, String userId){
		
		Map<String, Object> condition = new HashMap<String, Object>();
		
		
		condition.put("commentNo", commentNo);
		
		User user = new User();
		user.setUserId(userId);
		condition.put("user", user);
		
		CommentSessionDynamicSqlRepository commentRepository
		= new CommentSessionDynamicSqlRepository();
		
		return commentRepository.selectCommentByConditionTrim(condition);
	}
	
public List<Comment> selectCommentForeach(String[] commentNos){
		
		Map<String, Object> condition = new HashMap<String, Object>();
		List<Long> cs = new ArrayList<Long>();
		for(String commetnNo : commentNos) {
			cs.add(Long.parseLong(commetnNo));
		}
		
		condition.put("commentNos", cs);
		System.out.println(condition);
		
		CommentSessionDynamicSqlRepository commentRepository
		= new CommentSessionDynamicSqlRepository();
		
		return commentRepository.selectCommentForeach(condition);
	}


public List<Comment> selectOgnlStaticMethod(){
	

	CommentSessionDynamicSqlRepository commentRepository
	= new CommentSessionDynamicSqlRepository();
	
	return commentRepository.selectOgnlStaticMethod();
}

public List<Comment> selectOgnlStaticField(){
	

	CommentSessionDynamicSqlRepository commentRepository
	= new CommentSessionDynamicSqlRepository();
	
	return commentRepository.selectOgnlStaticField();
}

public List<User> selectOgnlStaticMethod2(String userId){
	
	Map<String, Object> condition = new HashMap<String, Object>();
	condition.put("userId", userId);

	CommentSessionDynamicSqlRepository commentRepository
	= new CommentSessionDynamicSqlRepository();
	
	return commentRepository.selectOgnlStaticMethod2(condition);
}
	
}


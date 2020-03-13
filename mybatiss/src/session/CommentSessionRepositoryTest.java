package session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Comment;

public class CommentSessionRepositoryTest {
	
	private final CommentSessionRepository commentSessionRepository = new CommentSessionRepository();
	
	public void testSelectCommentByPrimaryKey()
	{
		Long commentNo = 1L;
		Comment comment = commentSessionRepository.selectCommentByPrimaryKey(commentNo);
		
		System.out.println(comment);
	}
	
	public void testSelectCommentByCondition()
	{
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("commentNo",2L);
		condition.put("commentNoForeach",new ArrayList<Long>());
		List li = commentSessionRepository.selectCommentByCondition(condition);
		System.out.println(li);
	}
	
	
	public void testInsertComment() {
		Long commentNo = 8L;
		String userId = "fromm0";
		Date regDate = Calendar.getInstance().getTime();
		String commentContent = "test";
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setUserId(userId);
		comment.setCommentContent(commentContent);
		comment.setRegDate(regDate);
		
//		commentSessionRepository.deleteComment(commentNo);
		Integer result = commentSessionRepository.insertComment(comment);
		System.out.println(result);
	}
	
	public void testUpdateComment() {
		Long commentNo = 1L;
		String commentContent = "수정 test";
		
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setCommentContent(commentContent);
		Integer result = commentSessionRepository.updateComment(comment);
		
		System.out.println(result);
	}
	
	public void testDeleteComment() {
		Long commentNo = 1L;
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		
		Integer result = commentSessionRepository.deleteComment(commentNo);
		
		System.out.println(result);
	}
	
	
	public static void main(String[] args)
	{
		CommentSessionRepositoryTest test = new CommentSessionRepositoryTest();
		
		test.testSelectCommentByPrimaryKey();
		test.testSelectCommentByCondition();
		test.testInsertComment();
		test.testUpdateComment();
		test.testDeleteComment();
	}
	
}

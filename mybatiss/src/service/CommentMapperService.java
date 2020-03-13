package service;

import model.Comment;
import model.CommentUser;
import repository.CommentMapperRepository_ano;

public class CommentMapperService {

	private CommentMapperRepository_ano commentRepository = new CommentMapperRepository_ano();
	// 다오역할

	public Comment selectCommentByPrimaryKey(Long commentNo) {
		return commentRepository.selectCommentByPrimaryKey(commentNo);
	}

	public Integer insertComment(Comment comment) {
		return commentRepository.insertComment(comment);
	}
	
	public Integer deleteComment(Long commentNo) {
		return commentRepository.deleteComment(commentNo);
	}

	public CommentUser selectCommentByPrimaryKeyAssociation3(Long commentNo) {
		return commentRepository.selectCommentByPrimaryKeyAssociation3(commentNo);
	}
	
	public Comment selectCommentByPrimaryKeyConstructor2(Long commentNo) {
		return commentRepository.selectCommentByPrimaryKeyConstructor2(commentNo);
	}
}

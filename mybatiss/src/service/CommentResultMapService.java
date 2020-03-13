package service;

import model.Comment;
import model.CommentUser;
import repository.CommentMapperRepository;

public class CommentResultMapService {

	private CommentMapperRepository commentRepository = new CommentMapperRepository();
																	//다오역할
	
	public Comment selectByPrimaryKey(Long commentNo) {
		return commentRepository.selectCommentByPrimaryKey(commentNo);
	}

	public Comment selectCommentByPrimaryKeyConstructor(Long commentNo) {
		return commentRepository.selectCommentByPrimaryKeyConstructor(commentNo);
	}

	public CommentUser selectCommentByPrimaryKeyAssociation(Long commentNo) {
		return commentRepository.selectCommentByPrimaryKeyAssociation(commentNo);
	}

	public Comment selectCommentByPrimaryKeyAssociation2(Long commentNo) {
		return commentRepository.selectCommentByPrimaryKeyAssociation2(commentNo);
	}

	public Comment selectCommentByPrimaryKeyCollection(Long commentNo) {
		return commentRepository.selectCommentByPrimaryKeyCollection(commentNo);
	}

}

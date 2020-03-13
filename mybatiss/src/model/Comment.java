package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {
	
	private Long commentNo;
	private String userId;
	private Date regDate;
	private String commentContent;
	private String userName;
	
	private List<Reply> replies;
	private Reply reply;
	

	public Comment() {
	}

// 매개변수 생성자를 쓸 때 디폴트 생성자를 같이 생성하세요 
	public Comment(Long commentNo, String userId, Date regDate, String commentContent ) {
		this.commentNo = commentNo;
		this.userId = userId;
		this.regDate = regDate;
		this.commentContent = commentContent;
	}
	
	
	public Long getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(Long commentNo) {
		this.commentNo = commentNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", userId=" + userId + ", regDate=" + regDate + ", commentContent="
				+ commentContent + ", userName=" + userName + ", replies=" + replies + ", reply=" + reply + "]";
	}

	
	
	
}

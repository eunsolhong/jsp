package mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import jdk.nashorn.internal.objects.annotations.Constructor;
import model.Comment;
import model.CommentUser;

//어노테이션 : 에러메세지를 잘해줌 가독성이 좋음
public interface CommentMapper {

	@Select("SELECT comment_no, user_id, comment_content, reg_date FROM COMMENT WHERE comment_no = #{commentNo}")
	
	@Results({
		@Result(column = "comment_no", property = "commentNo", jdbcType = JdbcType.BIGINT, id=true),
		@Result(column = "user_id", property = "userId", jdbcType = JdbcType.VARCHAR),
		@Result(column = "reg_date", property = "regDate", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "comment_content", property = "commentContent", jdbcType = JdbcType.LONGVARCHAR)
		
	})
	
	Comment selectCommentByPrimaryKey(Long commentNo);
	
	@Insert("INSERT INTO COMMENT(comment_no, user_id, comment_content, reg_date)"
			+ " VALUES (#{commentNo}, #{userId}, #{commentContent}, #{regDate})")
	Integer insertComment(Comment comment);
	
	@Delete("DELETE FROM comment WHERE comment_no = #{commentNo}")
	Integer deleteComment(Long commentNo);
	
	@Select({"SELECT c.comment_no, c.user_id, c.comment_content, c.reg_date, u.user_name ",
		"FROM comment c, user u ",
		"WHERE c.user_id = u.user_id ",
		"AND c.comment_no = #{commentNo}     "})
	
	@Results({
		@Result(column = "comment_no", property = "comment.commentNo", id = true),
		@Result(column =  "user_id", property = "comment.userId"),
		@Result(column = "reg_date", property = "comment.regDate", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "comment_content", property = "comment.commentContent"),
		@Result(column = "user_id", property = "user.userId"),
		@Result(column = "user_name", property = "user.userName")
	})
	CommentUser selectCommentByPrimaryKeyAssociation3(Long commentNo);
	
	
	@Select ({"SELECT comment_no, user_id, comment_content, reg_date ",
		"FROM comment ",
		"WHERE comment_no = #{commentNo} "})
	@ConstructorArgs({@Arg(column = "comment_no",
						 javaType = Long.class, id = true),
		@Arg(column = "user_id", javaType = String.class),
		@Arg(column =  "reg_date", javaType = Date.class),
		@Arg(column = "comment_content", javaType = String.class)})
	
	Comment selectCommentByPrimaryKeyConstructor2(Long commentNo);
	
	}

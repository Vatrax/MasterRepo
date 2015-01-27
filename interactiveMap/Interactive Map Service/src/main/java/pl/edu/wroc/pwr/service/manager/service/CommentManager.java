package pl.edu.wroc.pwr.service.manager.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pl.edu.wroc.pwr.model.to.comment.CommentCreationTO;
import pl.edu.wroc.pwr.service.manager.DataManager;
import pl.edu.wroc.pwr.service.manager.service.utils.JsonObjectConverter;
import pl.edu.wroc.pwr.service.model.Comment;

import java.util.List;

/**
 * Created by krzaczek on 26.10.14.
 */
public class CommentManager extends DataManager {

	public Comment get(String commentId) {
		return mongoOperation.findById(commentId, Comment.class);
	}

	public List<Comment> getCommentsFor(String targetId) {
		Query searchUserQuery = new Query(Criteria.where("targetId").is(targetId));
		return mongoOperation.find(searchUserQuery, Comment.class);
	}

	public int remove(String commentId, Long ownerId) {
		Query searchUserQuery = new Query(
			Criteria.where("id").is(commentId).andOperator(Criteria.where("ownerId").is(ownerId)));
		WriteResult remove = mongoOperation.remove(searchUserQuery, Comment.class);
		return remove.getN();
	}

	public String createComment(CommentCreationTO commentCreationTO) {
		Comment comment = createCommentFromTO(commentCreationTO);
		mongoOperation.save(comment);
		return JsonObjectConverter.convert("id", comment.getId());
	}

	private Comment createCommentFromTO(CommentCreationTO commentCreationTO) {
		Comment comment = new Comment();
		comment.setOwnerId(commentCreationTO.getOwnerId());
		comment.setTargetId(commentCreationTO.getTargetId());
		comment.setComment(commentCreationTO.getComment());
		return comment;
	}
}

package pl.edu.wroc.pwr.service.endpoint;

import pl.edu.wroc.pwr.model.to.comment.CommentCreationTO;
import pl.edu.wroc.pwr.service.manager.service.CommentManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by krzaczek on 26.10.14.
 */
@Path("/comments/")
public class CommentService {

	private final CommentManager commentManager = new CommentManager();

	@GET
	@Path("/{id}")
	public Response getComment(@PathParam("id") String commentId) {
		return Response.status(Response.Status.OK).entity(commentManager.get(commentId))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}

	@GET
	@Path("/target/{id}")
	public Response getCommentsFor(@PathParam("id") String targetId) {
		return Response.status(Response.Status.OK).entity(commentManager.getCommentsFor(targetId))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}

	@DELETE
	@Path("/{id}/{ownerId}")
	public Response removeComment(@PathParam("id") String commentId, @PathParam("ownerId") Long ownerId) {
		return Response.status(Response.Status.OK).entity(commentManager.remove(commentId, ownerId))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}

	@POST
	@Path("/")
	public Response createComment(CommentCreationTO commentCreationTO) {
		return Response.status(Response.Status.OK).entity(commentManager.createComment(commentCreationTO))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}
}

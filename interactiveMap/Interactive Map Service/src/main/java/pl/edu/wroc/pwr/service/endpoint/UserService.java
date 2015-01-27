package pl.edu.wroc.pwr.service.endpoint;

import pl.edu.wroc.pwr.model.to.user.UserTO;
import pl.edu.wroc.pwr.service.manager.service.UserManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by krzaczek on 22.11.14.
 */
@Path("/users")
public class UserService {

	private final UserManager userManager = new UserManager();

	@GET
	@Path("/{id}")
	public Response getUser(@PathParam("id") String userId) {
		return Response.status(Response.Status.OK).entity(userManager.get(userId)).type(MediaType.APPLICATION_JSON)
			.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response createUser(UserTO userTO) {
		return Response.status(Response.Status.OK).entity(userManager.create(userTO)).type(MediaType.APPLICATION_JSON)
			.build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response updateUser(UserTO userTO) {
		return Response.status(Response.Status.OK).entity(userManager.update(userTO)).type(MediaType.APPLICATION_JSON)
			.build();
	}

}

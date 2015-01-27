package pl.edu.wroc.pwr.service.endpoint;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import pl.edu.wroc.pwr.service.manager.service.PictureManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by krzaczek on 22.11.14.
 */
@Path("/pictures")
public class PicturesService {

	private final PictureManager pictureManager = new PictureManager();

	@GET
	@Path("/{id}")
	public Response getPicture(@PathParam("id") String pictureId) {
		return Response.status(Response.Status.OK).entity(pictureManager.get(pictureId))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}

	@GET
	@Path("/target/{id}")
	public Response getPictureFor(@PathParam("id") String targetId) {
		return Response.status(Response.Status.OK).entity(pictureManager.getPicturesFor(targetId))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}

	@DELETE
	@Path("/{id}/{ownerId}")
	public Response removePictures(@PathParam("id") String pictureId, @PathParam("ownerId") Long ownerId) {
		return Response.status(Response.Status.OK).entity(pictureManager.remove(pictureId, ownerId))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}

	@POST
	@Path("/")
	@Consumes("multipart/form-data")
	public Response createPicture(@QueryParam("ownerId") String ownerId, @QueryParam("targetId") String targetId,
		MultipartFormDataInput input) {
		return Response.status(Response.Status.OK).entity(pictureManager.createPicture(ownerId, targetId, input))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}
}

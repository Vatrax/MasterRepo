package pl.edu.wroc.pwr.service.endpoint;

import pl.edu.wroc.pwr.model.to.rating.RateCreationTO;
import pl.edu.wroc.pwr.model.to.rating.RateTO;
import pl.edu.wroc.pwr.service.manager.service.RateManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by krzaczek on 26.10.14.
 */
@Path("/rates")
public class RateService {

	private final RateManager rateManager = new RateManager();

	@GET
	@Path("/{id}")
	public Response getRate(@PathParam("id") String rateId) {
		return Response.status(Response.Status.OK).entity(rateManager.get(rateId)).type(MediaType.APPLICATION_JSON)
			.build();
	}

	@GET
	@Path("/target/{id}")
	public Response getAverage(@PathParam("id") String targetId) {
		return Response.status(Response.Status.OK).entity(rateManager.getAverage(targetId))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}

	@DELETE
	@Path("/{id}/{ownerId}")
	public Response removeRate(@PathParam("id") String rateId, @PathParam("ownerId") Long ownerId) {
		return Response.status(Response.Status.OK).entity(rateManager.remove(rateId, ownerId))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}

	@POST
	@Path("/")
	public Response createRate(RateCreationTO rateCreationTO) {
		return Response.status(Response.Status.OK).entity(rateManager.createRate(rateCreationTO))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}

	@PUT
	@Path("/")
	public Response updateRate(@QueryParam("ownerId") Long ownerId, RateTO rateTO) {
		return Response.status(Response.Status.OK).entity(rateManager.update(ownerId, rateTO))
			.type(MediaType.APPLICATION_JSON)
			.build();
	}

}

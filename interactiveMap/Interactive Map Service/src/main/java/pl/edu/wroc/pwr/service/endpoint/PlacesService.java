package pl.edu.wroc.pwr.service.endpoint;

import pl.edu.wroc.pwr.model.to.place.PlaceCreationTO;
import pl.edu.wroc.pwr.model.to.place.PlaceTO;
import pl.edu.wroc.pwr.service.manager.service.PlacesManager;
import pl.edu.wroc.pwr.service.model.Place;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Created by krzaczek on 24.10.14.
 */
@Path("/places")
public class PlacesService {

	private final PlacesManager placesManager = new PlacesManager(Place.class);

	@GET
	@Path("/")
	public Response getAllPlaces() {
		return Response.status(Response.Status.OK).entity(placesManager.getAll()).type(MediaType.APPLICATION_JSON)
			.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response createPlace(PlaceCreationTO place) {
		return Response.status(Response.Status.OK).entity(placesManager.create(place)).type(
			MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/{id}")
	public Response getPlace(@PathParam("id") String id) {
		return Response.status(Response.Status.OK).entity(placesManager.get(id)).type(MediaType.APPLICATION_JSON)
			.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/filtered")
	public Response getFilteredPlaces(Set<String> tags) {
		return Response.status(Response.Status.OK).entity(placesManager.getFiltered(tags)).type(
			MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response updatePlace(@QueryParam("ownerId") Long ownerId, PlaceTO place) {
		return Response.status(Response.Status.OK).entity(placesManager.update(place, ownerId)).type(
			MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path("/{id}/{ownerId}")
	public Response removePlace(@PathParam("id") String id, @PathParam("ownerId") Long ownerId) {
		return Response.status(Response.Status.OK).entity(placesManager.remove(id, ownerId)).type(
			MediaType.APPLICATION_JSON).build();
	}

}
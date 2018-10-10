package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.datasources.TrackDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    private TrackDAO trackDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracks() {
        return Response.ok(trackDAO.all()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response track(@PathParam("id") int id) {
        if (trackDAO.find(id) != null) {
            return Response.ok(trackDAO.find(id)).build();
        }
        return Response.status(404).build();
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}

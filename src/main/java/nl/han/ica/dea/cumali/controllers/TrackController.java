package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    @Inject
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracks() {
        return Response.ok(trackService.all()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response track(@PathParam("id") int id) {
        if (trackService.find(id) != null) {
            return Response.ok(trackService.find(id)).build();
        }
        return Response.status(404).build();
    }
}
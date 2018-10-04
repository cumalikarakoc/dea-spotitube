package nl.han.ica.dea.cumali.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class HomeController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response routes(){
        String response = "<a href='/playlists'>Playlists</a><br>" +
                            "<a href ='tracks'>Tracks</a>";
        return Response.ok(response).build();
    }
}

package nl.han.ica.dea.cumali;

import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
import nl.han.ica.dea.cumali.services.TrackRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    private TrackRepository trackRepository = new TrackRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(){
        return Response.ok(trackRepository.getAll()).build();
    }
}

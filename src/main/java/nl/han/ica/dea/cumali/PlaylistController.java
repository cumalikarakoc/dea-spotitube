package nl.han.ica.dea.cumali;
import nl.han.ica.dea.cumali.dto.PlaylistDTO;
import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
import nl.han.ica.dea.cumali.services.PlaylistRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    private PlaylistRepository playlistRepository = new PlaylistRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(){
        return Response.ok(playlistRepository.getAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist(@PathParam("id") int id){
        return Response.ok(playlistRepository.find(id)).build();
    }

    @GET
    @Path("{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@PathParam("id") int id){
        PlaylistDTO playlist = playlistRepository.find(id);
        return Response.ok(new TrackCollectionDTO(playlist.getTracks())).build();
    }
}

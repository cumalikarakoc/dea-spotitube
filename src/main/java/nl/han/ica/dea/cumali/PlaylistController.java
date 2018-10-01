package nl.han.ica.dea.cumali;

import nl.han.ica.dea.cumali.dto.PlaylistDTO;
import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;
import nl.han.ica.dea.cumali.services.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @Inject
    private PlaylistService playlistService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists() {
        return Response.ok(playlistService.getAll()).build();
    }

    @GET
    @Path("{playlist}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist(@PathParam("playlist") int id) {
        if (playlistService.find(id) != null) {
            return Response.ok(playlistService.find(id)).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("{playlist}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@PathParam("playlist") int id) {
        PlaylistDTO playlist = playlistService.find(id);
        if (playlist.getTracks() != null) {
            return Response.ok(new TrackCollectionDTO(playlist.getTracks())).build();
        }
        return Response.status(404).build();
    }

    @GET
    @Path("{playlist}/tracks/{track}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("playlist") int playlist_id, @PathParam("track") int track_id) {
        PlaylistDTO playlist = playlistService.find(playlist_id);
        TrackDTO track = playlistService.findTrack(playlist, track_id);
        if(track != null) {
            return Response.ok(track).build();
        }
        return Response.status(404).build();
    }
}

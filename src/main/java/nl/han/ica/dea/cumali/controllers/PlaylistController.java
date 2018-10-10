package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.datasources.PlaylistDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    private PlaylistDAO playlistDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() {
        return Response.ok(playlistDAO.all()).build();
    }

    @GET
    @Path("{playlist}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam("playlist") int id) {
        if (playlistDAO.find(id) != null) {
            return Response.ok(playlistDAO.find(id)).build();
        }
        return Response.status(404).build();
    }
//
//    @GET
//    @Path("{playlist}/tracks")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response tracks(@PathParam("playlist") int id) {
//        PlaylistDTO playlist = playlistDAO.find(id);
//        if (playlist != null) {
//            return Response.ok(new TrackCollectionDTO(playlist.getTracks())).build();
//        }
//        return Response.status(404).build();
//    }
//
//    @GET
//    @Path("{playlist}/tracks/{track}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response track(@PathParam("playlist") int playlist_id, @PathParam("track") int track_id) {
//        PlaylistDTO playlist = playlistDAO.find(playlist_id);
//        TrackDTO track = playlistDAO.findTrack(playlist, track_id);
//        if(track != null) {
//            return Response.ok(track).build();
//        }
//        return Response.status(404).build();
//    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }
}

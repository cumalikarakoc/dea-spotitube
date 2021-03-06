package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.datasources.PlaylistDAO;
import nl.han.ica.dea.cumali.dtos.PlaylistDTO;
import nl.han.ica.dea.cumali.dtos.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dtos.TrackDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    private PlaylistDAO playlistDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(@QueryParam("token") String token) {
        return Response.ok(playlistDAO.all(token)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam("id") int id) {
        if (playlistDAO.find(id) != null) {
            return Response.ok(playlistDAO.find(id)).build();
        }
        return Response.status(404).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, PlaylistDTO playlistDTO, @QueryParam("token") String token){
        return Response.ok( playlistDAO.update(id, playlistDTO, token)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response store(PlaylistDTO playlistDTO, @QueryParam("token") String token){
        return Response.ok(playlistDAO.save(playlistDTO, token)).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response destroy(@PathParam("id") int id, @QueryParam("token") String token){
        return Response.ok(playlistDAO.delete(id, token)).build();
    }

    @GET
    @Path("{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracks(@PathParam("id") int id) {
        PlaylistDTO playlist = playlistDAO.find(id);
        if (playlist != null) {
            return Response.ok(new TrackCollectionDTO(playlist.getTracks())).build();
        }
        return Response.status(404).build();
    }

    @DELETE
    @Path("{playlist_id}/tracks/{track_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response detachTrack(@PathParam("playlist_id") int playlistId, @PathParam("track_id") int trackId){
        return Response.ok(playlistDAO.removeTrackFromPlaylist(playlistId, trackId)).build();
    }

    @POST
    @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response attachTrack(@PathParam("id") int id, TrackDTO trackDTO){
        return Response.ok(playlistDAO.addTrackToPlaylist(id, trackDTO)).build();
    }

    @Inject
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }
}

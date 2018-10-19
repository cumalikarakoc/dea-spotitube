package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.datasources.PlaylistDAO;
import nl.han.ica.dea.cumali.dtos.PlaylistCollectionDTO;
import nl.han.ica.dea.cumali.dtos.PlaylistDTO;
import nl.han.ica.dea.cumali.dtos.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dtos.TrackDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class PlaylistControllerTest {

    private PlaylistDAO playlistDAO;
    private PlaylistController playlistController;
    private PlaylistCollectionDTO playlistCollectionDTO;
    private TrackCollectionDTO trackCollectionDTO;
    private PlaylistDTO playlistDTO;

    @BeforeEach
    void setup(){
        playlistController = new PlaylistController();
        playlistDAO = Mockito.mock(PlaylistDAO.class);
        playlistController.setPlaylistDAO(playlistDAO);
        playlistCollectionDTO = Mockito.mock(PlaylistCollectionDTO.class);
        trackCollectionDTO = Mockito.mock(TrackCollectionDTO.class);
        playlistDTO = Mockito.mock(PlaylistDTO.class);
    }

    @Test
    void testShouldReturn200IfAllPlaylistsRequested(){
        Mockito.when(playlistDAO.all()).thenReturn(playlistCollectionDTO);
        Response response = playlistController.index();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn200IfGivenPlaylistExistsWithTheGivenID(){
        int id = Mockito.anyInt();
        Mockito.when(playlistDAO.find(id)).thenReturn(playlistDTO);
        Response response = playlistController.show(id);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn404IfGivenPlaylistNotExistsWithTheGivenID(){
        int id = Mockito.anyInt();
        Mockito.when(playlistDAO.find(id)).thenReturn(null);
        Response response = playlistController.show(id);

        Assertions.assertEquals(404, response.getStatus());
    }

    @Test
    void testShouldReturn200IfAllTracksOfAnExistingPlaylistRequested(){
        int id = Mockito.anyInt();
        Mockito.when(playlistDAO.find(id)).thenReturn(playlistDTO);
        Response response = playlistController.tracks(id);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn200IfGivenTrackOfPlaylistIsDeleted(){
        int playlistId = Mockito.anyInt();
        int trackId = Mockito.anyInt();
        Mockito.when(playlistDAO.removeTrackFromPlaylist(playlistId, trackId)).thenReturn(trackCollectionDTO);
        Response response = playlistController.detachTrack(playlistId, trackId);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn200IfTrackAddedToPlaylist(){
        int playlistId = Mockito.anyInt();
        TrackDTO trackDTO = Mockito.anyObject();
        Mockito.when(playlistDAO.addTrackToPlaylist(playlistId, trackDTO)).thenReturn(trackCollectionDTO);
        Response response = playlistController.attachTrack(playlistId, trackDTO);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn200IfPlaylistIsUpdated(){
        int playlistId = Mockito.anyInt();
        PlaylistDTO playlistDTO = Mockito.anyObject();
        Mockito.when(playlistDAO.update(playlistId, playlistDTO)).thenReturn(playlistCollectionDTO);
        Response response = playlistController.update(playlistId, playlistDTO);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn200IfNewPlaylistIsCreated(){
        PlaylistDTO playlistDTO = Mockito.anyObject();
        Mockito.when(playlistDAO.save(playlistDTO)).thenReturn(playlistCollectionDTO);
        Response response = playlistController.store(playlistDTO);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn200IfNewPlaylistIsDeleted(){
        int id = Mockito.anyInt();
        Mockito.when(playlistDAO.delete(id)).thenReturn(playlistCollectionDTO);
        Response response = playlistController.destroy(id);

        Assertions.assertEquals(200, response.getStatus());
    }
}

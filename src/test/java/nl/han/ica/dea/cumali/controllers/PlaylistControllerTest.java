package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.dto.PlaylistCollectionDTO;
import nl.han.ica.dea.cumali.dto.PlaylistDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;
import nl.han.ica.dea.cumali.services.PlaylistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class PlaylistControllerTest {

    private PlaylistService playlistService;
    private PlaylistController playlistController;
    private PlaylistCollectionDTO playlistCollectionDTO;
    private PlaylistDTO playlistDTO;
    private TrackDTO trackDTO;

    @BeforeEach
    void setup(){
        playlistController = new PlaylistController();
        playlistService = Mockito.mock(PlaylistService.class);
        playlistController.setPlaylistService(playlistService);
        playlistCollectionDTO = Mockito.mock(PlaylistCollectionDTO.class);
        playlistDTO = Mockito.mock(PlaylistDTO.class);
        trackDTO = Mockito.mock(TrackDTO.class);
    }

    @Test
    void testShouldReturn200IfAllPlaylistsRequested(){
        Mockito.when(playlistService.all()).thenReturn(playlistCollectionDTO);
        Response response = playlistController.playlists();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn200IfGivenPlaylistExistsWithTheGivenID(){
        int id = Mockito.anyInt();
        Mockito.when(playlistService.find(id)).thenReturn(playlistDTO);
        Response response = playlistController.playlist(id);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn404IfGivenPlaylistNotExistsWithTheGivenID(){
        int id = Mockito.anyInt();
        Mockito.when(playlistService.find(id)).thenReturn(null);
        Response response = playlistController.playlist(id);

        Assertions.assertEquals(404, response.getStatus());
    }

    @Test
    void testShouldReturn200IfAllTracksOfAnExistingPlaylistRequested(){
        int id = Mockito.anyInt();
        Mockito.when(playlistService.find(id)).thenReturn(playlistDTO);
        Response response = playlistController.tracks(id);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn200IfTheGivenPlaylistPossessesTheRequestedTrack(){
        int id = Mockito.anyInt();
        Mockito.when(playlistService.find(id)).thenReturn(playlistDTO);
        Mockito.when(playlistService.findTrack(playlistDTO, id)).thenReturn(trackDTO);

        Response response = playlistController.track(id, id);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn404IfTheGivenPlaylistNotPossessesTheRequestedTrack(){
        int id = Mockito.anyInt();
        Mockito.when(playlistService.find(id)).thenReturn(playlistDTO);
        Mockito.when(playlistService.findTrack(playlistDTO, id)).thenReturn(null);

        Response response = playlistController.track(id, id);

        Assertions.assertEquals(404, response.getStatus());
    }
}

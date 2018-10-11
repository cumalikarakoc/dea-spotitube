package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.datasources.PlaylistDAO;
import nl.han.ica.dea.cumali.dto.PlaylistCollectionDTO;
import nl.han.ica.dea.cumali.dto.PlaylistDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class PlaylistControllerTest {

    private PlaylistDAO playlistDAO;
    private PlaylistController playlistController;
    private PlaylistCollectionDTO playlistCollectionDTO;
    private PlaylistDTO playlistDTO;
    private TrackDTO trackDTO;

    @BeforeEach
    void setup(){
        playlistController = new PlaylistController();
        playlistDAO = Mockito.mock(PlaylistDAO.class);
        playlistController.setPlaylistDAO(playlistDAO);
        playlistCollectionDTO = Mockito.mock(PlaylistCollectionDTO.class);
        playlistDTO = Mockito.mock(PlaylistDTO.class);
        trackDTO = Mockito.mock(TrackDTO.class);
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
}

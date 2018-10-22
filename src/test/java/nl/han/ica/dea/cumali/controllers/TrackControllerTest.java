package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.datasources.TrackDAO;
import nl.han.ica.dea.cumali.dtos.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dtos.TrackDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class TrackControllerTest {
    private TrackController trackController;
    private TrackDAO trackDAO;
    private TrackCollectionDTO trackCollectionDTO;
    private TrackDTO trackDTO;

    @BeforeEach
    void setup(){
        trackController = new TrackController();
        trackDAO = Mockito.mock(TrackDAO.class);
        trackController.setTrackDAO(trackDAO);
        trackCollectionDTO = Mockito.mock(TrackCollectionDTO.class);
        trackDTO = Mockito.mock(TrackDTO.class);
    }

    @Test
    void testShouldReturn200IfAllTracksRequested(){
        int forPlaylist = Mockito.anyInt();
        Mockito.when(trackDAO.all(forPlaylist)).thenReturn(trackCollectionDTO);
        Response response = trackController.index(forPlaylist);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn200IfTrackExistsWithTheGivenID(){
        int id = Mockito.anyInt();
        Mockito.when(trackDAO.find(id)).thenReturn(trackDTO);
        Response response = trackController.show(id);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn404IfTrackNotExistsWithTheGivenID(){
        int id = Mockito.anyInt();
        Mockito.when(trackDAO.find(id)).thenReturn(null);
        Response response = trackController.show(id);

        Assertions.assertEquals(404, response.getStatus());
    }

}

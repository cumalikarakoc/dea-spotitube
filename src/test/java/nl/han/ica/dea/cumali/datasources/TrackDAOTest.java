package nl.han.ica.dea.cumali.datasources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrackDAOTest extends DAOTest{
    private TrackDAO trackDAO;

    @BeforeEach
    void setup(){
        trackDAO = new TrackDAO();
    }

    @Test
    void testShouldReturnTwoTracks(){
        int numberOfTracks  = trackDAO.all().getTracks().size();

        Assertions.assertEquals(2, numberOfTracks);
    }

    @Test
    void testShouldReturnOneGivenTrackIdOne(){
       int id = trackDAO.find(1).getId();

       Assertions.assertEquals(1, id);
    }

    @Test
    void testShouldReturnOneTrackForPlaylistIdTwo(){
     int numberOfTracks = trackDAO.fetchTracksByPlaylistId(2).size();

        Assertions.assertEquals(1, numberOfTracks);
    }

}

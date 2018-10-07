package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TrackServiceTest {
    private TrackService trackService;

    @BeforeEach
    void setup(){
        trackService = new TrackService();
    }

    @Test
    void testShouldReturnEmptyArrayIfThereAreNoTracks(){
        trackService.setTrackCollection(new TrackCollectionDTO(new ArrayList<>()));

        Assertions.assertEquals(0, trackService.all().getTracks().size());
    }

    @Test
    void testShouldReturnTrackIfExists(){
        TrackDTO track = trackService.find(3);
        Assertions.assertNotNull(track);
    }

    @Test
    void testShouldReturnNullIfTrackNotExists(){
        TrackDTO track = trackService.find(5445646);
        Assertions.assertNull(track);
    }

    @Test
    void testShouldReturnTrackWithTheGivenId(){
        TrackDTO track = trackService.find(3);
        Assertions.assertEquals(track.getId(), 3);
    }
}

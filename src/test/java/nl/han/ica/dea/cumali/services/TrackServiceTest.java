package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.dto.TrackDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrackServiceTest {
    private TrackService trackService;

    @BeforeEach
    void setup(){
        trackService = new TrackService();
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

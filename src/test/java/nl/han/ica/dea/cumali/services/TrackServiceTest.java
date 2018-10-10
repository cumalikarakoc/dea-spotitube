//package nl.han.ica.dea.cumali.services;
//
//import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
//import nl.han.ica.dea.cumali.dto.TrackDTO;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class TrackServiceTest {
//    private TrackService trackService;
//    private List<TrackDTO> tracks;
//
//    @BeforeEach
//    void setup(){
//        trackService = new TrackService();
//        tracks = new ArrayList<>();
//    }
//
//    @Test
//    void testShouldReturnEmptyArrayIfThereAreNoTracks(){
//        trackService.setTrackCollection(new TrackCollectionDTO(tracks));
//
//        Assertions.assertEquals(0, trackService.all().getTracks().size());
//    }
//
//    @Test
//    void testShouldReturnTrackIfExists(){
//        tracks.add(new TrackDTO(1, "title", "performer", 123, null, 0, null, null, false, 1));
//        trackService.setTrackCollection(new TrackCollectionDTO(tracks));
//
//        TrackDTO track = trackService.find(1);
//        Assertions.assertNotNull(track);
//    }
//
//    @Test
//    void testShouldReturnNullIfTrackNotExists(){
//        trackService.setTrackCollection(new TrackCollectionDTO(tracks));
//        TrackDTO track = trackService.find(2);
//        Assertions.assertNull(track);
//    }
//
//    @Test
//    void testShouldReturnTrackWithTheGivenId(){
//        tracks.add(new TrackDTO(1, "title", "performer", 123, null, 0, null, null, false, 1));
//        trackService.setTrackCollection(new TrackCollectionDTO(tracks));
//
//        TrackDTO track = trackService.find(1);
//        Assertions.assertEquals(track.getId(), 1);
//    }
//}

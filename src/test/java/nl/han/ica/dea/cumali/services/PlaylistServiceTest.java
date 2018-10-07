package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.dto.PlaylistCollectionDTO;
import nl.han.ica.dea.cumali.dto.PlaylistDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PlaylistServiceTest {
    private PlaylistService playlistService;

    @BeforeEach
    void setup(){
        playlistService = new PlaylistService();
    }

    @Test
    void testShouldReturnEmptyArrayIfThereAreNoPlaylists(){
        List<PlaylistDTO> playlists = new ArrayList<>();
        playlistService.setPlaylistCollection(new PlaylistCollectionDTO(playlists, 21312));

        Assertions.assertEquals(0, playlistService.all().getPlaylists().size());
    }

    @Test
    void testShouldReturnPlaylistIfExists(){
        PlaylistDTO playlist = playlistService.find(1);
        Assertions.assertNotNull(playlist);
    }

    @Test
    void testShouldReturnNullIfPlaylistNotExists(){
        PlaylistDTO playlist = playlistService.find(-1);
        Assertions.assertNull(playlist);
    }

    @Test
    void testShouldReturnPlaylistWithTheGivenId(){
        PlaylistDTO playlist = playlistService.find(1);
        Assertions.assertEquals(playlist.getId(), 1);
    }

    @Test
    void testShouldReturnTheTrackOfTheGivenPlaylist(){
        PlaylistDTO playlist = playlistService.find(2);
        TrackDTO track = playlistService.findTrack(playlist, 1);
        Assertions.assertNotNull(track);
    }

    @Test
    void testShouldReturnNullIfRequestedTrackOfTheGivenPlaylistNotExists(){
        PlaylistDTO playlist = playlistService.find(2);
        TrackDTO track = playlistService.findTrack(playlist, -1);
        Assertions.assertNull(track);
    }
}

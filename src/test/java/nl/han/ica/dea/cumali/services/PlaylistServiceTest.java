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
    private List<PlaylistDTO> playlists;

    @BeforeEach
    void setup(){
        playlistService = new PlaylistService();
        playlists = new ArrayList<>();
    }

    @Test
    void testShouldReturnEmptyArrayIfThereAreNoPlaylists(){
        playlistService.setPlaylistCollection(new PlaylistCollectionDTO(playlists, 21312));

        Assertions.assertEquals(0, playlistService.all().getPlaylists().size());
    }

    @Test
    void testShouldReturnPlaylistIfExists(){
        playlists.add(new PlaylistDTO(1,"test", true, null));
        playlistService.setPlaylistCollection(new PlaylistCollectionDTO(playlists, 21312));

        PlaylistDTO playlist = playlistService.find(1);
        Assertions.assertNotNull(playlist);
    }

    @Test
    void testShouldReturnNullIfPlaylistNotExists(){
        playlistService.setPlaylistCollection(new PlaylistCollectionDTO(playlists, 3213));

        PlaylistDTO playlist = playlistService.find(1);
        Assertions.assertNull(playlist);
    }

    @Test
    void testShouldReturnPlaylistWithTheGivenId(){
        playlists.add(new PlaylistDTO(1,"test", true, null));
        playlistService.setPlaylistCollection(new PlaylistCollectionDTO(playlists, 3213));

        PlaylistDTO playlist = playlistService.find(1);
        Assertions.assertEquals(playlist.getId(), 1);
    }

    @Test
    void testShouldReturnTheTrackOfTheGivenPlaylist(){
        playlists.add(new PlaylistDTO(1,"test", true, null));
        playlistService.setPlaylistCollection(new PlaylistCollectionDTO(playlists, 3213));

        PlaylistDTO playlist = playlistService.find(1);
        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(new TrackDTO(1, "title", "performer", 123, null, 0, null, null, false, 1));
        playlist.setTracks(tracks);

        TrackDTO track = playlistService.findTrack(playlist, 1);
        Assertions.assertNotNull(track);
    }

    @Test
    void testShouldReturnNullIfRequestedTrackOfTheGivenPlaylistNotExists(){
        playlists.add(new PlaylistDTO(1,"test", true, null));
        playlistService.setPlaylistCollection(new PlaylistCollectionDTO(playlists, 3213));

        List<TrackDTO> tracks = new ArrayList<>();
        PlaylistDTO playlist = playlistService.find(1);
        playlist.setTracks(tracks);

        TrackDTO track = playlistService.findTrack(playlist, 1);
        Assertions.assertNull(track);
    }
}

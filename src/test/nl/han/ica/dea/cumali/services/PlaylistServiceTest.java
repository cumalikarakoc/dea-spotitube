package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.dto.PlaylistDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaylistServiceTest {
    private PlaylistService playlistService;

    @BeforeEach
    void setup(){
        playlistService = new PlaylistService();
    }

    @Test
    void testShouldReturnPlaylistIfExists(){
        PlaylistDTO playlist = playlistService.find(1);
        Assertions.assertNotNull(playlist);
    }

    @Test
    void testShouldReturnNullIfPlaylistNotExists(){
        PlaylistDTO playlist = playlistService.find(5445646);
        Assertions.assertNull(playlist);
    }

    @Test
    void testShouldReturnPlaylistWithTheGivenId(){
        PlaylistDTO playlist = playlistService.find(1);
        Assertions.assertEquals(playlist.getId(), 1);
    }
}

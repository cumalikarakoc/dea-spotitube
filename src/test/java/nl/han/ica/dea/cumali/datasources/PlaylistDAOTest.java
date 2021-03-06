package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.dtos.PlaylistDTO;
import nl.han.ica.dea.cumali.dtos.TrackDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PlaylistDAOTest extends DAOTest{
    private PlaylistDAO playlistDAO;

    @BeforeEach
    void setup(){
        playlistDAO = new PlaylistDAO();
    }
    @Test
    void testShouldReturnThreePlaylists() {
        int numberOfPlaylists = playlistDAO.all("token").getPlaylists().size();

        Assertions.assertEquals(3, numberOfPlaylists);
    }

    @Test
    void testShouldReturnOneGivenPlaylistIdOne() {
        PlaylistDTO playlistDTO = playlistDAO.find(1);

        Assertions.assertEquals(1, playlistDTO.getId());
    }

    @Test
    void testShouldReturnEditedNameIfPlaylistNameEdited() {
        PlaylistDTO playlistDTO = playlistDAO.find(1);
        playlistDTO.setName("edited");
        playlistDAO.update(1, playlistDTO, "token");
        String playlistName = playlistDAO.find(1).getName();

        Assertions.assertEquals("edited",playlistName);
    }

    @Test
    void testShouldReturnOneMorePlaylistIfPlaylistCreated() {
        int numberOfPlaylistsBeforeInserting = playlistDAO.all("token").getPlaylists().size();
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("new playlist");
        playlistDAO.save(playlistDTO, "token");
        int numberOfPlaylistsAfterInserting = playlistDAO.all("token").getPlaylists().size();

        Assertions.assertEquals(numberOfPlaylistsBeforeInserting+1, numberOfPlaylistsAfterInserting);
    }

    @Test
    void testShouldReturnOneLessPlaylistIfPlaylistDeleted() {
        int numberOfPlaylistsBeforeDeleting = playlistDAO.all("token").getPlaylists().size();
        playlistDAO.delete(3, "token");
        int numberOfPlaylistsAfterDeleting = playlistDAO.all("token").getPlaylists().size();

        Assertions.assertEquals(numberOfPlaylistsBeforeDeleting-1, numberOfPlaylistsAfterDeleting);
    }

    @Test
    void testShouldReturnOneMoreTrackIfTrackAddedToPlaylist() {
        PlaylistDTO playlistDTO = playlistDAO.find(1);
        int numberOfTracksBeforeAttaching = playlistDTO.getTracks().size();

        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(1);
        int numberOfTracksAfterAttaching = playlistDAO.addTrackToPlaylist(playlistDTO.getId(), trackDTO).getTracks().size();

        Assertions.assertEquals(numberOfTracksBeforeAttaching+1, numberOfTracksAfterAttaching);
    }

    @Test
    void testShouldReturnOneLessTrackIfTrackRemovedFromPlaylist() {
        PlaylistDTO playlistDTO = playlistDAO.find(2);
        int numberOfTracksBeforeDetaching = playlistDTO.getTracks().size();

        int numberOfTracksAfterDetaching = playlistDAO.removeTrackFromPlaylist(playlistDTO.getId(), 2).getTracks().size();

        Assertions.assertEquals(numberOfTracksBeforeDetaching-1, numberOfTracksAfterDetaching);

    }

}

package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.dtos.PlaylistCollectionDTO;
import nl.han.ica.dea.cumali.dtos.PlaylistDTO;
import nl.han.ica.dea.cumali.dtos.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dtos.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlaylistDAO extends DAO<PlaylistDTO> {


    private static final int LENGTH = 11111;

    public PlaylistCollectionDTO all(String token) {
        Object[] values = new Object[0];
        List<PlaylistDTO> playlists = runQueryToFetchDTOList("SELECT * FROM playlists", values);
        handleOwner(playlists, token);
        return new PlaylistCollectionDTO(playlists, LENGTH);
    }

    private void handleOwner(List<PlaylistDTO> playlists, String token) {
        Object[] values = {token};
        List<PlaylistDTO> ownedPlaylists = runQueryToFetchDTOList("SELECT * FROM playlists WHERE owner_id IN " +
                "(SELECT id FROM users WHERE token LIKE ?)", values);

       for (PlaylistDTO playlist : playlists) {
            for (PlaylistDTO ownedPlaylist: ownedPlaylists) {
                if (ownedPlaylist.getId() == playlist.getId()) {
                    playlist.setOwner(true);
                }
            }
        }
    }

    public PlaylistDTO find(int id) {
        Object[] values = {id};
        List<PlaylistDTO> playlists = runQueryToFetchDTOList("SELECT * FROM playlists WHERE id = ?", values);
        return playlists.isEmpty() ? null : playlists.get(0);
    }

    public PlaylistCollectionDTO update(int id, PlaylistDTO playlistDTO, String token) {
        Object[] values = {playlistDTO.getName(), id};
        runQuery("UPDATE playlists SET name = ? WHERE id = ?", values);
        return all(token);
    }

    public PlaylistCollectionDTO save(PlaylistDTO playlistDTO, String token) {
        UserDAO userDAO = new UserDAO();
        Object[] values = {playlistDTO.getName(), userDAO.getUserDTOByToken(token).getId()};
        runQuery("INSERT INTO playlists(name, owner_id) VALUES(?, ?)", values);
        return all(token);
    }

    public PlaylistCollectionDTO delete(int id, String token) {
        Object[] values = {id};
        runQuery("DELETE FROM playlists where id = ?", values);
        return all(token);
    }

    public TrackCollectionDTO removeTrackFromPlaylist(int playlistId, int trackId) {
        Object[] columnValues = {playlistId, trackId};
        runQuery("DELETE FROM playlist_track WHERE playlist_id = ? AND track_id = ?", columnValues);
        return new TrackCollectionDTO(find(playlistId).getTracks());
    }

    public TrackCollectionDTO addTrackToPlaylist(int playlistId, TrackDTO trackDTO) {
        Object[] columnValues = {playlistId, trackDTO.getId()};
        runQuery("INSERT INTO playlist_track(playlist_id, track_id) VALUES(?, ?)", columnValues);
        return new TrackCollectionDTO(find(playlistId).getTracks());
    }

    @Override
    public PlaylistDTO getAsDTO(ResultSet resultSet) throws SQLException {
        PlaylistDTO playlistDTO = new PlaylistDTO();

        playlistDTO.setId(resultSet.getInt("id"));
        playlistDTO.setName(resultSet.getString("name"));
        playlistDTO.setOwner(false);
        playlistDTO.setTracks(tracksBelongToPlaylist(playlistDTO.getId()));

        return playlistDTO;
    }

    private List<TrackDTO> tracksBelongToPlaylist(int id) {
        return new TrackDAO().fetchTracksByPlaylistId(id);
    }

}
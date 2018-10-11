package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.dto.PlaylistCollectionDTO;
import nl.han.ica.dea.cumali.dto.PlaylistDTO;
import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlaylistDAO extends DAO<PlaylistDTO> {


    public static final int LENGTH = 11111;

    public PlaylistCollectionDTO all() {
        Object[] values = new Object[0];
        List<PlaylistDTO> playlists = runQueryToFetchDTOList("SELECT * FROM playlists", values);
        return new PlaylistCollectionDTO(playlists, LENGTH);
    }

    public PlaylistDTO find(int id) {
        Object[] values = {id};
        List<PlaylistDTO> playlists = runQueryToFetchDTOList("SELECT * FROM playlists WHERE id = ?", values);
        return playlists.isEmpty() ? null : playlists.get(0);
    }

    public PlaylistCollectionDTO update(int id, PlaylistDTO playlistDTO) {
        Object[] values = {playlistDTO.getName(), playlistDTO.getOwner(), id};
        runQuery("UPDATE playlists SET name = ?, owner = ? WHERE id = ?", values);
        return all();
    }

    public PlaylistCollectionDTO save(PlaylistDTO playlistDTO) {
        Object[] values = {playlistDTO.getName(), true};
        runQuery("INSERT INTO playlists(name, owner) VALUES(?, ?)", values);
        return all();
    }

    public PlaylistCollectionDTO delete(int id) {
        Object[] values = {id};
        runQuery("DELETE FROM playlists where id = ?", values);
        return all();
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
        PlaylistDTO playlistDTO = new PlaylistDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getBoolean("owner"), null);
        playlistDTO.setTracks(tracksBelongToPlaylist(playlistDTO.getId()));
        return playlistDTO;
    }

    private List<TrackDTO> tracksBelongToPlaylist(int id) {
        return new TrackDAO().fetchTracksByPlaylistId(id);
    }

}
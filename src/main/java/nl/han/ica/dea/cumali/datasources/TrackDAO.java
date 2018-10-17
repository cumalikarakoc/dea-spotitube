package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;

import java.sql.*;
import java.util.List;

public class TrackDAO extends DAO<TrackDTO> {


    public TrackCollectionDTO all() {
        Object[] values = new Object[0];
        List<TrackDTO> tracks = runQueryToFetchDTOList("SELECT * FROM tracks", values);
        return new TrackCollectionDTO(tracks);
    }

    public TrackDTO find(int id) {
        Object[] values = {id};
        List<TrackDTO> tracks = runQueryToFetchDTOList("SELECT * FROM tracks WHERE id = ?", values);
        return tracks.isEmpty() ? null : tracks.get(0);
    }

    public List<TrackDTO> fetchTracksByPlaylistId(int playlistId) {
        Object[] values = {playlistId};
        return runQueryToFetchDTOList("SELECT * FROM tracks LEFT JOIN playlist_track ON tracks.id = track_id WHERE playlist_id = ?", values);
    }


    @Override
    protected TrackDTO getAsDTO(ResultSet resultSet) throws SQLException {
        TrackDTO trackDTO = new TrackDTO();

        trackDTO.setId(resultSet.getInt("id"));
        trackDTO.setTitle(resultSet.getString("title"));
        trackDTO.setPerformer(resultSet.getString("performer"));
        trackDTO.setDuration(resultSet.getInt("duration"));
        trackDTO.setAlbum(resultSet.getString("album"));
        trackDTO.setPlaycount(resultSet.getInt("playcount"));
        trackDTO.setPublicationDate(resultSet.getString("publicationDate"));
        trackDTO.setDescription(resultSet.getString("description"));
        trackDTO.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));

        return trackDTO;
    }
}

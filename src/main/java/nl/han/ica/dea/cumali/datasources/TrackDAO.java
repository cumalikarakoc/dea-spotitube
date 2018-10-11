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
        return new TrackDTO(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("performer"), resultSet.getInt("duration"),
                resultSet.getString("album"), resultSet.getInt("playcount"), resultSet.getString("publicationDate"),
                resultSet.getString("description"), resultSet.getBoolean("offlineAvailable"));
    }


    public TrackCollectionDTO save(TrackDTO trackDTO) {
        Object[] values = {trackDTO.getTitle(), trackDTO.getPerformer(), trackDTO.getDuration(), trackDTO.getAlbum(), trackDTO.getPlaycount(),
                trackDTO.getPublicationDate(), trackDTO.getDescription(), trackDTO.getOfflineAvailable()};
        runQuery("INSERT INTO tracks(title, performer, duration, album, playcount, publicationDate, description, offlineAvailable) VALUES(?,?,?,?,?,?,?,?)", values);
        return all();
    }
}

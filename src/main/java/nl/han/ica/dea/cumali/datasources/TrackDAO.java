package nl.han.ica.dea.cumali.datasources;
import nl.han.ica.dea.cumali.dto.TrackCollectionDTO;
import nl.han.ica.dea.cumali.dto.TrackDTO;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class TrackDAO extends DAO<TrackDTO>{


    public TrackCollectionDTO all(){
        List<TrackDTO> tracks = fetchAllDTOs(new TrackDTO());
        return  new TrackCollectionDTO(tracks);
    }

    public TrackDTO find(int id){
        return fetchDTOById(new TrackDTO(), id);
    }

    @Override
    protected TrackDTO getAsDTO(ResultSet resultSet) throws SQLException {
        return new TrackDTO(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("performer"), resultSet.getInt("duration"),
                resultSet.getString("album"), resultSet.getInt("playcount"), resultSet.getString("publicationDate"),
                resultSet.getString("description"), resultSet.getBoolean("offlineAvailable") ,resultSet.getInt("playlist_id"));
    }

    @Override
    protected Map setDTOInstanceProperties() {
        return null;
    }

}

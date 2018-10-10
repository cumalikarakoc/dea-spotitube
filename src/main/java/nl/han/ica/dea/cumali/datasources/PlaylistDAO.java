package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.dto.PlaylistCollectionDTO;
import nl.han.ica.dea.cumali.dto.PlaylistDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistDAO extends DAO<PlaylistDTO> {

    private static final String TABLE_NAME = "playlists";

    public PlaylistCollectionDTO all() {
        List<PlaylistDTO> playlists = fetchAllDTOs(new PlaylistDTO());
        return new PlaylistCollectionDTO(playlists, 321321);
    }

    public PlaylistDTO find(int id){
        return fetchDTOById(new PlaylistDTO(), id);
    }

    public void store(){
        queryToInsertDTO(TABLE_NAME);

        //update the front-end
        all();
    }

    @Override
    public PlaylistDTO getAsDTO(ResultSet resultSet) throws SQLException {
        return new PlaylistDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getBoolean("owner"), null);
    }

    @Override
    protected Map setDTOInstanceProperties() {
        PlaylistDTO playlist = new PlaylistDTO(10, "MyPlaylist",true, null);
        Map<String, String> properties = new HashMap<>();
        properties.put("id", String.valueOf(playlist.getId()));
        properties.put("name", playlist.getName());
        properties.put("owner", String.valueOf(playlist.getOwner()));
        properties.put("tracks", null);

        return properties;
    }
}
package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.datasources.util.DatabaseProperties;
import nl.han.ica.dea.cumali.dto.TrackDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrackDAO {
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseProperties databaseProperties;

    public TrackDAO()
    {
        databaseProperties = new DatabaseProperties();
        tryLoadJdbcDriver(databaseProperties);
    }

    public List<TrackDTO> findAll() {
        List<TrackDTO> tracks = new ArrayList<>();
        tryFindAll(tracks);
        return tracks;
    }

    public List<TrackDTO> findTracks(int playlist_id){
        List<TrackDTO> tracks = new ArrayList<>();
        tryFindTracks(tracks, playlist_id);
        return tracks;
    }

    private void tryFindTracks(List<TrackDTO> tracks, int playlist_id) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tracks WHERE playlist_id = ?");
            statement.setInt(1, playlist_id);
            addNewItemsFromDatabase(tracks, statement);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    private void tryLoadJdbcDriver(DatabaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.driver());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't load JDBC Driver " + databaseProperties.driver(), e);
        }
    }

    private void tryFindAll(List<TrackDTO> tracks) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tracks");
            addNewItemsFromDatabase(tracks, statement);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    private void addNewItemsFromDatabase(List<TrackDTO> tracks, PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            addNewItemFromResultSet(tracks, resultSet);
        }
    }

    private void addNewItemFromResultSet(List<TrackDTO> tracks, ResultSet resultSet) throws SQLException {
        TrackDTO item = new TrackDTO(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("performer"), resultSet.getInt("duration"),
                                    resultSet.getString("album"), resultSet.getInt("playcount"), resultSet.getString("publicationDate"),
                                    resultSet.getString("description"), resultSet.getBoolean("offlineAvailable") ,resultSet.getInt("playlist_id"));
        tracks.add(item);
    }
}

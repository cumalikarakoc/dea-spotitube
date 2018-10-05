package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.datasources.util.DatabaseProperties;
import nl.han.ica.dea.cumali.dto.PlaylistDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class PlaylistDAO {
        private Logger logger = Logger.getLogger(getClass().getName());
        private DatabaseProperties databaseProperties;

        public PlaylistDAO()
        {
            databaseProperties = new DatabaseProperties();
            tryLoadJdbcDriver(databaseProperties);
        }

        public List<PlaylistDTO> findAll() {
            List<PlaylistDTO> playlists = new ArrayList<>();
            tryFindAll(playlists);
            return playlists;
        }

        private void tryLoadJdbcDriver(DatabaseProperties databaseProperties) {
            try {
                Class.forName(databaseProperties.driver());
            } catch (ClassNotFoundException e) {
                logger.log(Level.SEVERE, "Can't load JDBC Driver " + databaseProperties.driver(), e);
            }
        }

        private void tryFindAll(List<PlaylistDTO> playlists) {
            try {
                Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlists");
                addNewItemsFromDatabase(playlists, statement);
                statement.close();
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
            }
        }

        private void addNewItemsFromDatabase(List<PlaylistDTO> playlists, PreparedStatement statement) throws SQLException {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                addNewItemFromResultSet(playlists, resultSet);
            }
        }

        private void addNewItemFromResultSet(List<PlaylistDTO> playlists, ResultSet resultSet) throws SQLException {
            PlaylistDTO item = new PlaylistDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getBoolean("owner"), null);
            playlists.add(item);
        }
}

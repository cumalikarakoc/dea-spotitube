package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.datasources.util.DatabaseProperties;
import nl.han.ica.dea.cumali.dto.UserDTO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private DatabaseProperties databaseProperties;
    private Logger logger = Logger.getLogger(getClass().getName());

    public UserDAO(){
            databaseProperties = new DatabaseProperties();
            tryLoadJdbcDriver(databaseProperties);
    }

    private void tryLoadJdbcDriver(DatabaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.driver());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't load JDBC Driver " + databaseProperties.driver(), e);
        }
    }


    public UserDTO getUser(String username) {
        UserDTO userDTO = new UserDTO();
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE name LIKE ?" );
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                userDTO.setUser(resultSet.getString("name"));
                userDTO.setPassword(resultSet.getString("password"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return  userDTO;
    }

}

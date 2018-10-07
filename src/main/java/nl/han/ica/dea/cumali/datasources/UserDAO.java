package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.datasources.util.DatabaseProperties;

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


    public boolean authenticate(String username, String password) {
        boolean userExists = false;
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE name LIKE ? AND password LIKE ?" );
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                userExists = true;
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return userExists;
    }
}

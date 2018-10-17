package nl.han.ica.dea.cumali.datasources.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class DatabaseProperties {
        private Logger logger = Logger.getLogger(getClass().getName());
        private Properties properties;
        private Connection connection;

        public DatabaseProperties() {
            properties = new Properties();
            try {
                properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Can't access property file database.properties", e);
            }
        }

        public String driver()
        {
            return properties.getProperty("driver");
        }

        public String connectionString()
        {
            return properties.getProperty("connectionString");
        }

        public Connection getConnection() {
            try {
                connection = DriverManager.getConnection(connectionString());
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error communicating with database " + connectionString(), e);
            }
            return connection;
        }
    }

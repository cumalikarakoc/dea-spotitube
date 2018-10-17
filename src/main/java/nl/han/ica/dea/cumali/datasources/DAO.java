package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.datasources.util.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO<T> {
    private static final String DATABASE_ERROR = "Error communicating with database ";
    private DatabaseProperties databaseProperties;
    private Logger logger = Logger.getLogger(getClass().getName());

    DAO() {
        databaseProperties = new DatabaseProperties();
        loadJdbcDriver(databaseProperties);
    }

    private void loadJdbcDriver(DatabaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.driver());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't load JDBC Driver " + databaseProperties.driver(), e);
        }
    }

    protected abstract T getAsDTO(ResultSet resultSet) throws SQLException;

    protected List<T> runQueryToFetchDTOList(String query, Object[] values) {
        PreparedStatement statement;
        List<T> DTOs = null;
        try {
            Connection connection = databaseProperties.getConnection();
            statement = connection.prepareStatement(query);
            bindParams(values, statement);
            DTOs = getDTOListFromResultSet(statement);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, DATABASE_ERROR + databaseProperties.connectionString(), e);
        }
        return DTOs;
    }

    protected void runQuery(String query, Object[] values) {
        PreparedStatement statement;
        try {
            Connection connection = databaseProperties.getConnection();
            statement = connection.prepareStatement(query);
            bindParams(values, statement);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, DATABASE_ERROR+ databaseProperties.connectionString(), e);
        }

    }

    private List<T> getDTOListFromResultSet(PreparedStatement statement) throws SQLException {
        List<T> objectList = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                objectList.add(getAsDTO(resultSet));
            }
        }
        return objectList;
    }


    private void bindParams(Object[] values, PreparedStatement statement) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Integer) {
                statement.setInt(i + 1, (int) values[i]);
            }
            if (values[i] instanceof String) {
                statement.setString(i + 1, String.valueOf(values[i]));
            }
            if (values[i] instanceof Boolean) {
                statement.setBoolean(i + 1, (boolean) values[i]);
            }
        }
    }

}

package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.datasources.util.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO <T>{
    private DatabaseProperties databaseProperties;
    private Connection connection;
    private Logger logger = Logger.getLogger(getClass().getName());

    DAO() {
        databaseProperties = new DatabaseProperties();
        tryLoadJdbcDriver(databaseProperties);

        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected List<T> fetchAllDTOs(T t) {
        PreparedStatement statement = null;
        List<T> DTOs = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM " + getTableName(t));
            DTOs = getDTOListFromResultSet(statement);
            statement.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return DTOs;
    }

    protected T fetchDTOById(T t, int id){
        PreparedStatement statement = null;
        T dto= null;
        try {
            statement = connection.prepareStatement("SELECT * FROM " + getTableName(t) + " WHERE id = ?");
            statement.setInt(1, id);
            dto = getSingleDTOFromResultSet(statement);
            statement.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return dto;
    }

    private String getTableName(T t) {
        String className = t.getClass().getSimpleName();
        return className.replace("DTO", "s").toLowerCase();
    }


    private T getSingleDTOFromResultSet(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return getAsDTO(resultSet);
    }

    private List<T> getDTOListFromResultSet(PreparedStatement statement) throws SQLException {
        List<T> objectList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            objectList.add(getAsDTO(resultSet));
        }
        return objectList;
    }

    protected abstract T getAsDTO(ResultSet resultSet) throws SQLException;

    protected abstract Map setDTOInstanceProperties();


    protected void queryToInsertDTO(String tableName){
        PreparedStatement statement = null;
        Map dtoProperties = setDTOInstanceProperties();

        try {
            statement = connection.prepareStatement(tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





    private void tryLoadJdbcDriver(DatabaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.driver());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't load JDBC Driver " + databaseProperties.driver(), e);
        }
    }
}

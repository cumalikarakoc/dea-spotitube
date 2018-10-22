package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.dtos.UserDTO;

import java.sql.*;
import java.util.List;

public class UserDAO extends DAO<UserDTO>{

    public UserDTO getUserDTOByUsername(String username) {
        Object[] values = {username};
        List<UserDTO> users = runQueryToFetchDTOList("SELECT * FROM users WHERE username = ?", values);
        return users.isEmpty() ? null : users.get(0);
    }

    public UserDTO getUserDTOByToken(String token) {
        Object[] values = {token};
        List<UserDTO> users = runQueryToFetchDTOList("SELECT * FROM users WHERE token = ?", values);
        return users.isEmpty() ? null : users.get(0);
    }

    public void setAuthToken(String username, String token) {
        Object[] values = {token, username};
        runQuery("UPDATE users SET token = ? WHERE username = ?", values);
    }

    @Override
    protected UserDTO getAsDTO(ResultSet resultSet) throws SQLException {
        return new UserDTO(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
    }
}

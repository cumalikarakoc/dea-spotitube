package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.dto.UserDTO;

import java.sql.*;
import java.util.List;

public class UserDAO extends DAO<UserDTO>{

    public UserDTO getUserDTO(String username) {
        Object[] values = {username};
        List<UserDTO> users = runQueryToFetchDTOList("SELECT * FROM users WHERE username = ?", values);
        return users.isEmpty() ? null : users.get(0);
    }

    private UserDTO getUserWithToken(String username){
            Object[] values = {username};
            List<UserDTO> users = runQueryToFetchDTOList("SELECT * FROM users INNER JOIN auth_tokens ON users.username = auth_tokens.username WHERE users.username = ?", values);
            return users.isEmpty() ? null : users.get(0);
    }

    public void setAuthToken(String username, String token) {
        Object[] values = {username, token};
        if (getUserWithToken(username) != null) {
            runQuery("UPDATE auth_tokens set username = ?, token = ?", values);
        } else {
            runQuery("INSERT INTO auth_tokens(username, token) VALUES(?, ?)", values);
        }
    }

    @Override
    protected UserDTO getAsDTO(ResultSet resultSet) throws SQLException {
        return new UserDTO(resultSet.getString("username"), resultSet.getString("password"));
    }
}

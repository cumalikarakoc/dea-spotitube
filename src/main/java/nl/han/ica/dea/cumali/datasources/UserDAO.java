package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.dto.UserDTO;

import java.sql.*;
import java.util.List;

public class UserDAO extends DAO<UserDTO>{

    public UserDTO getUser(String username) {
        Object[] values = {username};
        List<UserDTO> users = runQueryToFetchDTOList("SELECT * FROM users WHERE name = ?", values);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    protected UserDTO getAsDTO(ResultSet resultSet) throws SQLException {
        return new UserDTO(resultSet.getString("name"), resultSet.getString("password"));
    }
}

package nl.han.ica.dea.cumali.datasources;

import nl.han.ica.dea.cumali.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOTest extends DAOTest {
    private UserDAO userDAO;

    @BeforeEach
    void setup(){
        userDAO = new UserDAO();
    }

    @Test
    void testShouldReturnUsernameOfRequestedUser(){
         String username = userDAO.getUserDTO("test").getUser();

        Assertions.assertEquals("test", username);
    }

    @Test
    void testShouldUpdateTokenOfUser() throws SQLException {
        UserDTO user = userDAO.getUserDTO("test");
        String oldToken = getTokenOfUser(user);

        userDAO.setAuthToken(user.getUser(), "newRandomToken");
        String newToken  = getTokenOfUser(user);

        Assertions.assertNotEquals(oldToken, newToken);
    }

    @Test
    void testShouldInsertTokenFor() throws SQLException {
        UserDTO user = userDAO.getUserDTO("testUser");

        userDAO.setAuthToken(user.getUser(), "newRandomToken");
        String newToken  = getTokenOfUser(user);

        Assertions.assertEquals("newRandomToken", newToken);
    }

    private String getTokenOfUser(UserDTO user) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT token FROM auth_tokens WHERE username LIKE '" + user.getUser() + "'");
        ResultSet rs = stmt.getResultSet();
        rs.next();
        return rs.getString("token");
    }
}

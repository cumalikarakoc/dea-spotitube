package nl.han.ica.dea.cumali.services;

import nl.han.ica.dea.cumali.datasources.UserDAO;
import nl.han.ica.dea.cumali.dtos.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserServiceTest {
    private UserService userService;
    private UserDAO userDAO;
    @BeforeEach
    void setup() {
        userService = new UserService();
        userDAO = Mockito.mock(UserDAO.class);
        userService.setUserDAO(userDAO);
    }

    @Test
    void testShouldReturnTrueGivenCorrectUserCredentials() {
        Mockito.when(userDAO.getUserDTO(Mockito.anyString())).thenReturn(new UserDTO("test", "test123"));
        boolean returnValue = userService.authenticate("test", "test123");
        Assertions.assertTrue(returnValue);
    }

    @Test
    void testShouldReturnFalseGivenIncorrectUserCredentials() {
        Mockito.when(userDAO.getUserDTO(Mockito.anyString())).thenReturn(new UserDTO("test", "test123"));
        boolean returnValue = userService.authenticate("test", "123");
        Assertions.assertFalse(returnValue);
    }
}

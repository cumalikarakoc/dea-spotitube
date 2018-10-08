package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.dto.UserDTO;
import nl.han.ica.dea.cumali.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;


public class LoginControllerTest
{
    private LoginController loginController;
    private UserService userService;
    private UserDTO userDTO;
    @BeforeEach
     void setup(){
        loginController = new LoginController();
        userService = Mockito.mock(UserService.class);
        loginController.setUserService(userService);
        userDTO = Mockito.mock(UserDTO.class);

        userDTO.setUser(Mockito.anyString());
        userDTO.setPassword(Mockito.anyString());
    }

    @Test
    void testShouldReturn200IfUserAuthenticates(){
        Mockito.when(userService.authenticate(userDTO.getUser(), userDTO.getPassword())).thenReturn(true);

        Response response = loginController.login(userDTO);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn401IfUserNotAuthenticates(){
        Mockito.when(userService.authenticate(userDTO.getUser(), userDTO.getPassword())).thenReturn(false);

        Response response = loginController.login(userDTO);
        Assertions.assertEquals(401, response.getStatus());
    }
}

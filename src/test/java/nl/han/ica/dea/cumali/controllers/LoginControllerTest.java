package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.dto.LoginRequestDTO;
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
    private LoginRequestDTO loginRequestDTO;
    @BeforeEach
     void setup(){
        loginController = new LoginController();
        userService = Mockito.mock(UserService.class);
        loginController.setUserService(userService);
        loginRequestDTO = Mockito.mock(LoginRequestDTO.class);

        loginRequestDTO.setUser(Mockito.anyString());
        loginRequestDTO.setPassword(Mockito.anyString());
    }

    @Test
    void testShouldReturn200IfUserAuthenticates(){
        Mockito.when(userService.authenticate(loginRequestDTO.getUser(), loginRequestDTO.getPassword())).thenReturn(true);

        Response response = loginController.login(loginRequestDTO);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void testShouldReturn401IfUserNotAuthenticates(){
        Mockito.when(userService.authenticate(loginRequestDTO.getUser(),loginRequestDTO.getPassword())).thenReturn(false);

        Response response = loginController.login(loginRequestDTO);
        Assertions.assertEquals(401, response.getStatus());
    }
}

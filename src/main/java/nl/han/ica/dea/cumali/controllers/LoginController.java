package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.dtos.UserDTO;
import nl.han.ica.dea.cumali.dtos.LoginResponseDTO;
import nl.han.ica.dea.cumali.services.UserService;
import org.apache.commons.lang.RandomStringUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO request) {
        if (userService.authenticate(request.getUser(), request.getPassword())) {
            LoginResponseDTO response = new LoginResponseDTO();

            //generate a unique token
            response.setToken(RandomStringUtils.randomAlphanumeric(20) + userService.getUserByUsername(request.getUser()).getId());
            response.setUser(request.getUser());

            userService.persistToken(response.getUser(), response.getToken());

            return Response.ok(response).build();
        }
        return Response.status(401).build();
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

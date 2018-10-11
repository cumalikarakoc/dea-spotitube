package nl.han.ica.dea.cumali.controllers;

import nl.han.ica.dea.cumali.dto.UserDTO;
import nl.han.ica.dea.cumali.dto.LoginResponseDTO;
import nl.han.ica.dea.cumali.services.UserService;

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

            // TODO: manage tokens in the database
            response.setToken("123-456-789");

            response.setUser(request.getUser());
            return Response.ok(response).build();
        }
        return Response.status(401).build();
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

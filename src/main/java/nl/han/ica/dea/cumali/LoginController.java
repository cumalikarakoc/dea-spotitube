package nl.han.ica.dea.cumali;

import nl.han.ica.dea.cumali.dto.LoginRequestDTO;
import nl.han.ica.dea.cumali.dto.LoginResponseDTO;
import nl.han.ica.dea.cumali.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @Inject
    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO request){
        if(loginService.authenticate(request.getUser(), request.getPassword())){
            LoginResponseDTO response = new LoginResponseDTO();
            response.setToken("123-456-789");
            response.setUser(request.getUser());
            return Response.ok(response).build();
        }else{
            return Response.status(401).build();
        }

    }
}

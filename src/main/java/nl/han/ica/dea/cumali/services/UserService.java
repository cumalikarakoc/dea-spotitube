package nl.han.ica.dea.cumali.services;


import nl.han.ica.dea.cumali.datasources.UserDAO;
import nl.han.ica.dea.cumali.dtos.UserDTO;

import javax.inject.Inject;

public class UserService {
    private UserDAO userDAO;

    public boolean authenticate(String username, String password) {
        UserDTO user = userDAO.getUserDTOByUsername(username);
        if(user == null){return false;}
        return password.equals(user.getPassword());
    }

    public void persistToken(String username, String token){
        userDAO.setAuthToken(username, token);
    }

    public UserDTO getUserByUsername(String username){
        return userDAO.getUserDTOByUsername(username);
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}

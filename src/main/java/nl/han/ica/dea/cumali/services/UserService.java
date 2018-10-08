package nl.han.ica.dea.cumali.services;


import nl.han.ica.dea.cumali.datasources.UserDAO;
import nl.han.ica.dea.cumali.dto.UserDTO;

public class UserService {
    public boolean authenticate(String username, String password) {
        UserDTO user = new UserDAO().getUser(username);
        System.out.println(user);
        if(user == null){return false;}
        return user.getUser().equals(username) && user.getPassword().equals(password);
    }
}

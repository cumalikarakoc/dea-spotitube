package nl.han.ica.dea.cumali.services;


import nl.han.ica.dea.cumali.datasources.UserDAO;

public class UserService {
    public boolean authenticate(String username, String password) {
        return new UserDAO().authenticate(username, password);
    }
}

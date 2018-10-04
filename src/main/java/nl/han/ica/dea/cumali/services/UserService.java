package nl.han.ica.dea.cumali.services;


public class UserService {

    public boolean authenticate(String user, String password) {
        return "cumali".equals(user) && "admin123".equals(password);
    }
}

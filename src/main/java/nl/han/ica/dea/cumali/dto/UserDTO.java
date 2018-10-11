package nl.han.ica.dea.cumali.dto;

public class UserDTO {
    private String user;
    private String password;

    public UserDTO(){}

    public UserDTO(String name, String password) {
        user = name;
        this.password = password;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

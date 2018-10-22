package nl.han.ica.dea.cumali.dtos;

public class UserDTO {
    private int id;
    private String user;
    private String password;

    public UserDTO(){}

    public UserDTO(int id, String user, String password) {
        this.id = id;
        this.user = user;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

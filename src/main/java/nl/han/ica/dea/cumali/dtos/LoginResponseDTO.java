package nl.han.ica.dea.cumali.dtos;

public class LoginResponseDTO {
    private String user;
    private String token;

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

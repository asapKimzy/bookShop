package sample.pojo;

public class User {

    private int id;
    private String login;
    private String passwordd;
    private String email;

    public User( int id, String login, String passwordd, String email) {
        this.id = id;
        this.login = login;
        this.passwordd = passwordd;
        this.email = email;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return passwordd;
    }

    public void setPassword(String passwordd) {
        this.passwordd = passwordd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package Model;

import javafx.scene.Parent;

import java.math.BigDecimal;

public class User {// класс пользователя с конструкторами, геттерами и сеттерами
    private int id;
    private String login;
    private String password;
    private String gender;
    private String country;
    private String role;
    private BigDecimal money;

    public User(String login, String password, String gender, String country, String role, BigDecimal money){
        this.id=id;
        this.login=login;
        this.password=password;
        this.gender=gender;
        this.country=country;
        this.role=role;
        this.money=money;
    }
    public User(String login, String password, String gender, String country){
        this.id=id;
        this.login=login;
        this.password=password;
        this.gender=gender;
        this.country=country;
    }
    public User(){

    }
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BigDecimal getMoney(){
        return money;
    }
    public void setMoney(BigDecimal money){
        this.money=money;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString(){
        return id+" "+login+" "+password+" "+gender+" "+country+" "+money;
    }
}


package com.example.demo1;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Animation.Animation;
import DataBase.DataBase;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
    static Boolean admin=false;

    static int id=0;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField welcomePageLoginField;

    @FXML
    private PasswordField welcomePagePasswordField;

    @FXML
    private Button welcomePageSubmit;
    @FXML
    private Button welcomePageRegistrationButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        welcomePageSubmit.setOnAction(x->{//по нажатию кнопки получаем логин и пароль пользователя
          String login = welcomePageLoginField.getText();
          String password = welcomePagePasswordField.getText();

            try {
                if(DataBase.authorisation(login,password)&&DataBase.getRole(login,password).equals("user")){//если пароль и логин не админский, заходим на страницу для пользователя
                   openPage("welcomePage.fxml");
                   admin=false;
                   id=DataBase.getUserId(login,password);

                }
                else if(DataBase.authorisation(login,password)&&DataBase.getRole(login,password).equals("admin")){//если пароль и логин  админский, заходим на страницу для админа
                    openPage("bookStore.fxml");
                    admin=true;
                    id=DataBase.getUserId(login,password);

                }
                else{//если не подходит логин и пароль начинаем анимацию
                    Animation animation=new Animation(welcomePageLoginField);
                    Animation animation1=new Animation(welcomePagePasswordField);
                    animation.start();
                    animation1.start();
                }
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }

        });
        welcomePageRegistrationButton.setOnAction(x->{//по нажати кнопки открываем страницу
            try {
                openPage("registration.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        }

    public  void openPage(String str) throws IOException {
        welcomePageSubmit.getScene().getWindow().hide();
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

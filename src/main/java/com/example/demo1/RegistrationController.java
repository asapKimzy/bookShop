
    package com.example.demo1;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.DataBase;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

    public class RegistrationController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;
        @FXML
        private TextField registrationAddmoney;

        @FXML
        private PasswordField RegistrationPagePasswordRepeat;
        @FXML
        private TextField registrationPageCountry;

        @FXML
        private TextField registrationPageEmailField;

        @FXML
        private CheckBox registrationPageMaleCheckBox;

        @FXML
        private PasswordField registrationPagePasswordField;

        @FXML
        private Button registrationPageRegistrationButton;

        @FXML
        private RadioButton registrtionPageAgeRadioButton;

        @FXML
        private CheckBox registrtionPageFemaleCheckBox;
        @FXML
        private Button registrationPageReturnButton;


        @FXML
        void initialize() {
            registrationPageRegistrationButton.setOnAction(x->{//по нажатию кнопки получаем данные с полей
               String email= registrationPageEmailField.getText();
               String password= registrationPagePasswordField.getText();
               String repeatPassword=RegistrationPagePasswordRepeat.getText();
               Boolean gender=registrationPageMaleCheckBox.isSelected();
               Boolean gender2= registrtionPageFemaleCheckBox.isSelected();
               String country=  registrationPageCountry.getText();
               Boolean age= registrtionPageAgeRadioButton.isSelected();
               String money=registrationAddmoney.getText();
               double money2=Double.parseDouble(money);

                User user=new User();

                try {
                    if(age&&password.equals(repeatPassword)&&!email.equals("")&&!password.equals("")&&!country.equals("")&&DataBase.checkLogin(email)){//проверка возраста, страны, совпадения паролей и  логина
                        user.setLogin(email);

                        user.setPassword(password);

                        if(gender){//проверка пола
                            user.setGender("Male");
                        }
                        else {
                            user.setGender("Female");
                        }
                        user.setCountry(country);
                        user.setMoney(BigDecimal.valueOf(money2));
                        try {
                            DataBase.addUser(user);//добавляем пользователя
                        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    openPage("welcomePage.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }


            });
            registrationPageReturnButton.setOnAction(x-> {//по нажатию кнопки переходим на другую страницу
                try {
                    openPage("hello-view.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }


            });

        }
        public  void openPage(String str) throws IOException {
            registrationPageRegistrationButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(str));
            fxmlLoader.load();
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }


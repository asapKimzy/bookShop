package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class BookStore {
    @FXML
    private Button WelcomePageAdminButton;

    @FXML
    private Button WelcomePageCatalogue;
    @FXML
    private Button bookStoreBackButton;

    @FXML
    void initialize(){
        WelcomePageAdminButton.setOnAction(x->{//по нажатию кнопки вызывается функция открытия страницы
            try {
                WelcomePageAdminButton.getScene().getWindow().hide();
                openPage("adminPage.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        WelcomePageCatalogue.setOnAction(x->{//по нажатию кнопки вызывается функция открытия страницы
            try {
                WelcomePageCatalogue.getScene().getWindow().hide();
                openPage("ListOfAdmin.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bookStoreBackButton.setOnAction(x->{//по нажатию кнопки вызывается функция открытия страницы
            try {
                openPage("hello-view.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public  void openPage(String str) throws IOException {//функция открытия страницы
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

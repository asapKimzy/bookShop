package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomePage {
    @FXML
    private Button WelcomeGoBack;

    @FXML
    private Button WelcomeUserCatalogue;
    @FXML
    private Button welcomePagePersonalButton;
@FXML
void initialize(){
    WelcomeUserCatalogue.setOnAction(x->{//по нажатию кнопки переходим на другую страницу
        try {
            openPage("listOfBooks.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    WelcomeGoBack.setOnAction(x->{//по нажатию кнопки переходим на другую страницу
        try {
            openPage("hello-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    welcomePagePersonalButton.setOnAction(x->{//по нажатию кнопки переходим на другую страницу
        try {
            openPage("personalPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    });

}
    public  void openPage(String str) throws IOException {//функция для открытия страницы
        WelcomeUserCatalogue.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}


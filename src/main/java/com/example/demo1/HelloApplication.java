package com.example.demo1;

import DataBase.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class HelloApplication extends Application {//главный класс в котором все запускается
    static  BigDecimal booksMoney;

    static {
        try {
            booksMoney = DataBase.bookMoney();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));//получаем начальную страницу
        Scene scene = new Scene(fxmlLoader.load(), 1100, 900);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
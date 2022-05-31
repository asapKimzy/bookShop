package com.example.demo1;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataBase.DataBase;
import Model.Book;
import Model.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PersonalPage {

    private ObservableList<Book> booksData= FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Book,String > authorColumn;
    @FXML
    private TableColumn<Book,Integer> numColumn;

    @FXML
    private TableColumn<Book, Genre> genreColumn;

    @FXML
    private TableColumn<Book, Integer> idColumn;

    @FXML
    private TableColumn<Book, String> nameColumn;


    @FXML
    private Button personalPageBackButton;

    @FXML
    private TableView<Book> personalTable;

    @FXML
    private TableColumn<Book, BigDecimal> priceColumn;

    @FXML
    private TextField personalAddMoney;

    @FXML
    private Button personalAddMoneyButton;
    @FXML
    private Label personalPageBalance;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id")); // устанавливаем тип и значение которое должно хранится в колонке
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        numColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("num"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Book, Genre>("genre"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, BigDecimal>("price"));

        personalTable.setItems(booksData);// заполняем таблицу данными

        personalPageBackButton.setOnAction(x->{
            try {
                openPage("welcomePage.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        init();
        personalAddMoneyButton.setOnAction(x->{
           String mon= personalAddMoney.getText();
        double money=Double.parseDouble(mon);
        personalAddMoney.clear();
            try {
                init();
                DataBase.updateMoneyUser(String.valueOf(HelloController.id),BigDecimal.valueOf(money));
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }


        });

    }
    private void init() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
       personalPageBalance.setText("Your balance: "+(DataBase.findMoney(String.valueOf(HelloController.id))));
    }
    private void initData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        booksData.addAll(DataBase.personalBook(String.valueOf(HelloController.id)));
    }
    public  void openPage(String str) throws IOException {
        personalPageBackButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

package com.example.demo1;

import DataBase.DataBase;
import Model.Book;
import Model.Genre;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;


public class ListOfBooks {
    private ObservableList<Book> booksData = FXCollections.observableArrayList();
    User user=new User();
    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, Genre> genreColumn;

    @FXML
    private TableColumn<Book, Integer> idColumn;

    @FXML
    private TableColumn<Book, String> nameColumn;

    @FXML
    private TableColumn<Book, Integer> numColumn;

    @FXML
    private TableColumn<Book, BigDecimal> priceColumn;


    @FXML
    private Button listOfBooksGoBackButton;
    @FXML
    private Button listOfBooksPersonalButton;

    @FXML
    private TableView<Book> tableBooks;

    @FXML
    private Label listOfBooksBalance;


    public void initialize() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        updateData();

                listOfBooksGoBackButton.setOnAction(x->{//по нажатию кнопки открываем страницу
                    if(HelloController.admin){
                        try {
                            openPage("BookStore.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            openPage("welcomePage.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                listOfBooksPersonalButton.setOnAction(x->{//по нажатию кнопки открываем страницу
                    try {
                        openPage("personalPage.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                addButtonToTable();//добавляем кнопку в таблицу
            }



    private void initData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        booksData.addAll(DataBase.bookOut());
        listOfBooksBalance.setText("Your balance: "+(DataBase.findMoney(String.valueOf(HelloController.id))));
    }
    public  void openPage(String str) throws IOException {
        listOfBooksGoBackButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public  void updateData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        booksData.clear();
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        numColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("num"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, BigDecimal>("price"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Book, Genre>("genre"));

        // заполняем таблицу данными
        tableBooks.setItems(booksData);
    }
    private void addButtonToTable() {//функция добавления кнопки в таблицу
        TableColumn<Book, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<Book, Void>() {

                    private final Button btn = new Button("Buy");

                    {

                        btn.setOnAction((ActionEvent event) -> {

                            Book book = getTableView().getItems().get(getIndex());
                            try {
                                if(DataBase.checkNum(String.valueOf(book.getId()))){
                                    BigDecimal price=DataBase.checkPrice(String.valueOf(book.getId()));
                                    BigDecimal money=DataBase.checkMoney(String.valueOf(HelloController.id));
                                    if(money.doubleValue()>price.doubleValue()){
                                        DataBase.deleteBookFromUser(String.valueOf(book.getId()));
                                        DataBase.buyingBook(HelloController.id,String.valueOf(book.getId()));
                                        DataBase.removeMoney(price,String.valueOf(HelloController.id));
                                        updateData();
                                    }
                                }
                                else{
                                    btn.setDisable(true);
                                }

                            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableBooks.getColumns().add(colBtn);

    }

}


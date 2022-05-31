package com.example.demo1;

import DataBase.DataBase;
import Model.Book;
import Model.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class ListOfAdmin {
    private ObservableList<Book> booksData = FXCollections.observableArrayList();
    @FXML
    private Button listOfAdminBack;
    @FXML
    private TableColumn<Book,String > authorColumn;

    @FXML
    private TableColumn<Book, Genre > genreColumn;

    @FXML
    private TableColumn<Book, Integer> idColumn;

    @FXML
    private TableColumn<Book, String> nameColumn;

    @FXML
    private TableColumn<Book, Integer> numColumn;

    @FXML
    private TableColumn<Book, BigDecimal> priceColumn;

    @FXML
    private TableView<Book> tableAdmin;

    public void initialize() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        updateData();
        listOfAdminBack.setOnAction(x->{//по нажатию кнопки открываем страницу
            try {
                openPage("bookStore.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addButtonToTable();//добавляем кнопку в таблицу
    }
    private void initData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        booksData.addAll(DataBase.bookOut());//добаввляем данные в таблицу из базы данных
    }
    public  void openPage(String str) throws IOException {//функция для открытия страницы
        listOfAdminBack.getScene().getWindow().hide();
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    private void addButtonToTable() {//функция добавления кнопки в таблицу
        TableColumn<Book, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<Book, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Book book = getTableView().getItems().get(getIndex());
                            try {
                                DataBase.deleteBookFromAdmin(String.valueOf(book.getId()));
                                updateData();
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

        tableAdmin.getColumns().add(colBtn);

    }
    public void updateData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//обновление страницы
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
        tableAdmin.setItems(booksData);
    }
}

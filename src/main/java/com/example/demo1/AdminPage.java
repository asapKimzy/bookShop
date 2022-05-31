package com.example.demo1;


import DataBase.DataBase;
import Model.Book;
import Model.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdminPage{
    ArrayList<String> list1;
    @FXML
    private Button adminPageAdd;
    @FXML
    private TextField AdminPagePriceField;

    @FXML
    private TextField adminPageBookAuthor;

    @FXML
    private TextField adminPageBookName;
    @FXML
    private Button adminPageGoBackButton;

    @FXML
    private ComboBox<Integer> adminPageAddCertainNum;
    List<Integer> list2 = IntStream.range(1, 11).boxed().collect(Collectors.toList());//Делаем ограничение для комбобокса
    ObservableList<Integer> number1 = FXCollections.observableArrayList(list2);

    @FXML
    private TextField adminPageTextFielfForId;

    @FXML
    private TextField adminPageTextFieldForName;
    @FXML
    private Button adminPageButton;
    @FXML
    private Label adminPageMoney;



    @FXML
    private ComboBox<Genre> adminPageGenre;
    ObservableList<Genre> genreList = FXCollections.observableArrayList(Genre.values());//создаем комбобокс для выбора жанров
    @FXML
    private ComboBox<Integer> adminPageNumber;
    List<Integer> list = IntStream.range(1, 11).boxed().collect(Collectors.toList());//Создаем комбобокс для выбора количества книг
    ObservableList<Integer> number = FXCollections.observableArrayList(list);



    public void initialize() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        adminPageGenre.setItems(genreList);
        adminPageNumber.setItems(number);
        adminPageAddCertainNum.setItems(number1);
        list1 = new ArrayList<>();
        adminPageMoney.setText("Your income is: "+ HelloApplication.booksMoney.subtract(DataBase.bookMoney()));//добавляем лейблу значение полученное из функции + текст в ковычках
        adminPageAdd.setOnAction(x-> { //по нажатию кнопки добавить вызываем функцию добавляющую книгу
            try {
                addingBook();
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();//исключения
            }
        });
        adminPageGoBackButton.setOnAction(x->{//по нажатию кнопки назад вызываем функцию открывающую другую страницу
            try {
                openPage("bookStore.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        adminPageButton.setOnAction(x->{
            try {
                addToCertainBook();
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();//исключения
            }
        });
        }

public void addingBook() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//функция добавляет книгу
           String name=adminPageBookName.getText();
           String author=adminPageBookAuthor.getText();
           Genre genre=adminPageGenre.getValue();
           int num=adminPageNumber.getValue();
           String price=AdminPagePriceField.getText();
           BigDecimal bigDecimal=new BigDecimal(price);
           Book book=new Book();
           book.setName(name);
           book.setAuthor(author);
           book.setGenre(genre);
           book.setNum(num);
           book.setPrice(bigDecimal);
           DataBase.addBook(book);
}


    public  void openPage(String str) throws IOException {//функция открытия страницы
        adminPageGoBackButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root=fxmlLoader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void addToCertainBook() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//функция добавления книг к конкретной книге
        String name=adminPageTextFieldForName.getText();
        String id=adminPageTextFielfForId.getText();
        int num= adminPageAddCertainNum.getValue();
        if(!Objects.equals(name, "")){
            DataBase.addNumName(name,num);
        }
       else if(!Objects.equals(id,"")){
           DataBase.addNumId(id,num);
        }
    }
}

package DataBase;

import Model.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseBookHandler {
    public static void main(String[] args) {

    }
    public static void addBook(Book book) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//функция добавления книги
        PreparedStatement preparedStatement=DataBase.getConnection().prepareStatement("insert into books(name, num, price, author, genre) values(?, ?, ?, ?, ?)");//запрос в базу данных
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2,book.getNum()+"");
        preparedStatement.setString(3,book.getPrice()+"");
        preparedStatement.setString(4, book.getAuthor());
        preparedStatement.setString(5, book.getGenre().name());
        preparedStatement.executeUpdate();

    }
}

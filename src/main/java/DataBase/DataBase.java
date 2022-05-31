package DataBase;

import Model.Book;
import Model.Genre;
import Model.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

    }

    public static Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {//устанавливаем связь с базой данных
        String url = "jdbc:mysql://localhost/bookshop";
        Class.forName ("com.mysql.cj.jdbc.Driver").newInstance ();
        return DriverManager.getConnection (url, "root", "Rw-bmk197236");
    }
    public static void addUser(User user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//функция добавления пользователя
    Connection connection=getConnection();
    PreparedStatement preparedStatement=connection.prepareStatement("insert into users(login, password, gender, country,role, money) values(?,?,?,?,?,?)");//запрос в базу данных
    preparedStatement.setString(1, user.getLogin());
    preparedStatement.setString(2, user.getPassword());
    preparedStatement.setString(3, user.getGender());
    preparedStatement.setString(4, user.getCountry());
    preparedStatement.setString(5,"user");
    preparedStatement.setBigDecimal(6,user.getMoney());
    preparedStatement.executeUpdate();
    }
    public static Boolean checkLogin(String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//функция проверки логина
      Connection connection=getConnection();
      PreparedStatement preparedStatement=connection.prepareStatement("select * from users where login=?");//запрос в базу данных
      preparedStatement.setString(1,login);
      ResultSet resultSet=preparedStatement.executeQuery();
      int count=0;
      while (resultSet.next()){

          count++;

      }
        return count == 0;
    }
    public static Boolean authorisation(String login, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//функция авторизации
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select count(*) from users where login=? and password=?");//запрос в базу данных
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password);
        ResultSet resultSet=preparedStatement.executeQuery();
        int count=0;
        while (resultSet.next()){
            count= resultSet.getInt(1);
        }
        return count != 0;
    }
    public static void addBook(Book book) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//функция добавления книги
        Connection connection=getConnection();
      PreparedStatement preparedStatement= connection.prepareStatement("insert into books(name, num, price, author, genre) values(?,?,?,?,?)");//запрос в базу данных
      preparedStatement.setString(1, book.getName());
      preparedStatement.setInt(2, book.getNum());
      preparedStatement.setBigDecimal(3,book.getPrice());
      preparedStatement.setString(4, book.getAuthor());
      preparedStatement.setString(5,book.getGenre().name());
      preparedStatement.executeUpdate();
    }
    public static ArrayList<Book> bookOut() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//функция вывода книг в таблицу
        ArrayList<Book> bookList=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select * from books");//запрос в базу данных
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            int id=resultSet.getInt(1);
            String name=resultSet.getString(2);
            int num=resultSet.getInt(3);
            BigDecimal price=resultSet.getBigDecimal(4);
            String author=resultSet.getString(5);
            String genre=resultSet.getString(6);
           Book book=new Book();
           book.setId(id);
           book.setName(name);
           book.setNum(num);
           book.setPrice(price);
           book.setAuthor(author);
           book.setGenre(Genre.valueOf(genre));
           bookList.add(book);
        }
return bookList;

    }
    public static String getRole(String login,String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//функция, получающая роль пользователя
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select role from users where login=? and password=?");//запрос в базу данных
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password);
        ResultSet resultSet= preparedStatement.executeQuery();
        String role="";
        while (resultSet.next()) {
             role = resultSet.getString(1);
        }
        return role;
    }
    public static Boolean getId(String text) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//функция получения id пользователя
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from books where id=?");//запрос в базу данных
        preparedStatement.setString(1, text);
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id != 0;
    }
    public static void deleteBookFromUser(String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//удаление книги пользователем
        Connection connection=getConnection();

         PreparedStatement preparedStatement=getConnection().prepareStatement("update books set num=num-1 where id=?");//запрос в базу данных
         preparedStatement.setString(1,id);
         preparedStatement.executeUpdate();

    }
    public static boolean checkNum(String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//проверка количества книг по id
        Connection connection=getConnection();
        PreparedStatement preparedStatement1=getConnection().prepareStatement("select num from books where id=?");//запрос в базу данных
        preparedStatement1.setString(1,id);
        ResultSet resultSet=preparedStatement1.executeQuery();
        int num=0;
        while (resultSet.next()){
            num= resultSet.getInt(1);
        }
        return num != 0;
    }
    public static void addNumId(String id,int num) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//добавление книги к уже имеющейся по id
        Connection connection=getConnection();
        PreparedStatement preparedStatement=getConnection().prepareStatement("update books set num=num+? where id=?");//запрос в базу данных
        preparedStatement.setInt(1,num);
        preparedStatement.setString(2,id);
        preparedStatement.executeUpdate();

    }
    public static void addNumName(String name,int num) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//добавление книги к уже имеющейся по названию
        Connection connection=getConnection();
        PreparedStatement preparedStatement=getConnection().prepareStatement("update books set num=num+? where name=?");//запрос в базу данных
        preparedStatement.setInt(1,num);
        preparedStatement.setString(2,name);
        preparedStatement.executeUpdate();

    }
    public static ArrayList<Book> personalBook(String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//связь баз данных
        ArrayList<Book> perBooklist=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement=getConnection().prepareStatement("select b.id, b.name, b.author, b.genre, b.price, ub.count from books as b inner join userbooks as ub on b.id=ub.BookId inner join users as u on u.id=ub.userId where u.id=?");//запрос в базу данных
        preparedStatement.setString(1,id);
        ResultSet resultSet =preparedStatement.executeQuery();
        while (resultSet.next()){
            int bookId= resultSet.getInt(1);
            String name=resultSet.getString(2);
            String author= resultSet.getString(3);
            String genre =resultSet.getString(4);
            BigDecimal price=resultSet.getBigDecimal(5);
            int num= resultSet.getInt(6);
            Book book=new Book();
            book.setId(bookId);
            book.setName(name);
            book.setAuthor(author);
            book.setGenre(Genre.valueOf(genre));
            book.setPrice(price);
            book.setNum(num);
            perBooklist.add(book);
        }

        return perBooklist;
    }
    public static void buyingBook(int userId, String bookId) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//покупка книги
        Connection connection=getConnection();
        PreparedStatement preparedStatement1=getConnection().prepareStatement("select count from userbooks where userId=? and bookId=?");//запрос в базу данных
        preparedStatement1.setInt(1,userId);
        preparedStatement1.setString(2,bookId);
        ResultSet resultSet=preparedStatement1.executeQuery();
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);

        }
        if(count>0){
            PreparedStatement preparedStatement=getConnection().prepareStatement("update userbooks set count=? where userId=? and BookId=?");//запрос в базу данных
            preparedStatement.setInt(1,count+1);
            preparedStatement.setInt(2,userId);
            preparedStatement.setString(3,bookId);
            preparedStatement.executeUpdate();
        }
        else{
            PreparedStatement preparedStatement=getConnection().prepareStatement("insert into userbooks(userId,BookId,count)  values(?,?,?)");//запрос в базу данных
            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2,bookId);
            preparedStatement.setInt(3,1);
            preparedStatement.executeUpdate();
        }

    }
    public static int getUserId(String login, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//получение id пользователя по паролю и логину
        Connection connection=getConnection();
        PreparedStatement preparedStatement=getConnection().prepareStatement("select id from users where login=? and password=?");//запрос в базу данных
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password);
        ResultSet resultSet=preparedStatement.executeQuery();
        int id=0;
        while (resultSet.next()){
             id= resultSet.getInt(1);
        }
        return id;
    }
    public static  void deleteBookFromAdmin(String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//удаление книги со стороны админа
        Connection connection=getConnection();
        ArrayList<String> ids=new ArrayList<>();
        PreparedStatement preparedStatement=getConnection().prepareStatement("select ub.userBooksId from userbooks as ub inner join books as b on ub.BookId=b.id where b.id=?");//запрос в базу данных
        preparedStatement.setString(1,id);
        ResultSet resultSet= preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getString(1);
            ids.add(id);
        }
        for (String i :ids) {
        PreparedStatement preparedStatement1=getConnection().prepareStatement("delete from userbooks where userBooksId= ?");//запрос в базу данных
        preparedStatement1.setString(1,i);
        preparedStatement1.executeUpdate();
        }
        PreparedStatement preparedStatement2=getConnection().prepareStatement("delete from books where id=?");//запрос в базу данных
        preparedStatement2.setString(1,id);
        preparedStatement2.executeUpdate();
    }
    public static  BigDecimal checkPrice(String bookId) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//проверка стоимости книги по id
        Connection connection=getConnection();
        PreparedStatement preparedStatement=getConnection().prepareStatement("select price from books where id=?");//запрос в базу данных
        preparedStatement.setString(1,bookId);
        ResultSet resultSet= preparedStatement.executeQuery();
        BigDecimal price=new BigDecimal(0);
        while (resultSet.next()){
            price=resultSet.getBigDecimal(1);
        }
        return price;
    }
    public static BigDecimal checkMoney(String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//проверка денег у пользователя по id
        Connection connection=getConnection();
        PreparedStatement preparedStatement=getConnection().prepareStatement("select money from users where id=?");//запрос в базу данных
        preparedStatement.setString(1,id);
        ResultSet resultSet= preparedStatement.executeQuery();
        BigDecimal money=new BigDecimal(0);
        while (resultSet.next()){
             money=resultSet.getBigDecimal(1);
            System.out.println(money);
        }
        return money;
    }
    public static void removeMoney(BigDecimal price, String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//убираем деньги у пользователя при покупке
        Connection connection=getConnection();
        PreparedStatement preparedStatement=getConnection().prepareStatement("update users set money=money-? where id=?");//запрос в базу данных
        preparedStatement.setBigDecimal(1, price);
        preparedStatement.setString(2,id);
        preparedStatement.executeUpdate();
    }
    public static void updateMoneyUser(String id, BigDecimal money) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=getConnection().prepareStatement("update users set money=money+? where id=?");//запрос в базу данных
        preparedStatement.setBigDecimal(1, money);
        preparedStatement.setString(2,id);
        preparedStatement.executeUpdate();
    }
    public static BigDecimal findMoney(String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//поиск денег пользователя
        Connection connection=getConnection();
        PreparedStatement preparedStatement=getConnection().prepareStatement("select money from users where id=?");//запрос в базу данных
        preparedStatement.setString(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        BigDecimal money= new BigDecimal(0);
        while (resultSet.next()){
             money=resultSet.getBigDecimal(1);
        }
        return money;
    }
    public static BigDecimal bookMoney() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {//получение стоимости всех книг
        Connection connection=getConnection();
        PreparedStatement preparedStatement=getConnection().prepareStatement("select price, num from books");//запрос в базу данных
        ResultSet resultSet=preparedStatement.executeQuery();
        BigDecimal price =new BigDecimal(0);
        BigDecimal booksMoney=new BigDecimal(0);
        int num=0;
while (resultSet.next()){
   price=resultSet.getBigDecimal(1);
   num=resultSet.getInt(2);
   booksMoney=booksMoney.add(price.multiply(BigDecimal.valueOf(num)));
}

        System.out.println(booksMoney);
return booksMoney;
    }

}
